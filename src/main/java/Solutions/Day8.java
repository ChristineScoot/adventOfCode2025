package Solutions;

import Helper.Coord3D;
import Model.Pairs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.IntUnaryOperator;

public class Day8 {

    public static long part1(String filePath, int connections) throws IOException {
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] fileContentString = fileContent.split("\\r?\\n");
        List<Coord3D> allCoords = new ArrayList<>();
        for (String line : fileContentString) {
            String[] splitted = line.split(",");
            Coord3D coord3D = new Coord3D(Integer.parseInt(splitted[0]), Integer.parseInt(splitted[1]), Integer.parseInt(splitted[2]));
            allCoords.add(coord3D);
        }
        PriorityQueue<Pairs> pairs = new PriorityQueue<>();
        for (int i = 0; i < allCoords.size() - 1; i++) {
            for (int j = i + 1; j < allCoords.size(); j++) {
                Coord3D current = allCoords.get(i);
                Coord3D next = allCoords.get(j);
                Pairs nextPair = new Pairs(current, next);
                pairs.add(nextPair);
            }
        }
        Map<Coord3D, Integer> indexOf = new HashMap<>();
        for (int i = 0; i < allCoords.size(); i++) {
            indexOf.put(allCoords.get(i), i);
        }

        int n = allCoords.size();

// --- DSU structures ---
        int[] parent = new int[n];
        int[] size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
// find with path compression
        IntUnaryOperator root = new IntUnaryOperator() {
            @Override
            public int applyAsInt(int x) {
                // iterative path compression (to avoid recursion)
                int r = x;
                while (parent[r] != r) r = parent[r];
                // compress path
                while (parent[x] != r) {
                    int next = parent[x];
                    parent[x] = r;
                    x = next;
                }
                return r;
            }
        };

        java.util.function.IntBinaryOperator union = new java.util.function.IntBinaryOperator() {
            @Override
            public int applyAsInt(int a, int b) {
                int ra = root.applyAsInt(a);
                int rb = root.applyAsInt(b);
                if (ra == rb) return ra; // already same set
                // union by size: attach smaller under larger
                if (size[ra] < size[rb]) {
                    parent[ra] = rb;
                    size[rb] += size[ra];
                    return rb;
                } else {
                    parent[rb] = ra;
                    size[ra] += size[rb];
                    return ra;
                }
            }
        };

        int processed = 0;

        while (processed < connections && !pairs.isEmpty()) {
            Pairs p = pairs.poll(); // smallest-distance pair
            processed++;

            Coord3D c1 = p.getNode1();
            Coord3D c2 = p.getNode2();

            Integer i1 = indexOf.get(c1);
            Integer i2 = indexOf.get(c2);
            if (i1 == null || i2 == null) {
                continue;
            }

            int r1 = root.applyAsInt(i1);
            int r2 = root.applyAsInt(i2);

            if (r1 != r2) {
                union.applyAsInt(r1, r2); // merge components
            }
        }

        Map<Integer, Integer> rootToCount = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int r = root.applyAsInt(i);
            rootToCount.merge(r, 1, Integer::sum);
        }

        List<Integer> sizes = new ArrayList<>(rootToCount.values());
        sizes.sort(Comparator.reverseOrder());

        long result = 1L;
        for (int i = 0; i < Math.min(3, sizes.size()); i++) {
            result *= sizes.get(i);
        }

        return result;
    }

    public static long part2(String filePath) throws IOException {
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] fileContentString = fileContent.split("\\r?\\n");
        List<Coord3D> allCoords = new ArrayList<>();
        for (String line : fileContentString) {
            String[] splitted = line.split(",");
            Coord3D coord3D = new Coord3D(Integer.parseInt(splitted[0]), Integer.parseInt(splitted[1]), Integer.parseInt(splitted[2]));
            allCoords.add(coord3D);
        }

        PriorityQueue<Pairs> pairs = new PriorityQueue<>();
        for (int i = 0; i < allCoords.size() - 1; i++) {
            for (int j = i + 1; j < allCoords.size(); j++) {
                Coord3D current = allCoords.get(i);
                Coord3D next = allCoords.get(j);
                Pairs nextPair = new Pairs(current, next);

                pairs.add(nextPair);
            }
        }

        Map<Coord3D, Integer> indexOf = new HashMap<>();
        for (int i = 0; i < allCoords.size(); i++) {
            indexOf.put(allCoords.get(i), i);
        }

        int n = allCoords.size();

// --- DSU structures ---
        int[] parent = new int[n];
        int[] size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
// find with path compression
        IntUnaryOperator root = x -> {
            // iterative path compression (to avoid recursion)
            int r = x;
            while (parent[r] != r) r = parent[r];
            // compress path
            while (parent[x] != r) {
                int next = parent[x];
                parent[x] = r;
                x = next;
            }
            return r;
        };

        java.util.function.IntBinaryOperator union = (a, b) -> {
            int ra = root.applyAsInt(a);
            int rb = root.applyAsInt(b);
            if (ra == rb) return ra; // already same set
            // union by size: attach smaller under larger
            if (size[ra] < size[rb]) {
                parent[ra] = rb;
                size[rb] += size[ra];
                return rb;
            } else {
                parent[rb] = ra;
                size[ra] += size[rb];
                return ra;
            }
        };

        int components = n;
        long lastProduct = 0L;

        while (components > 1 && !pairs.isEmpty()) {
            Pairs p = pairs.poll();

            Coord3D c1 = p.getNode1();
            Coord3D c2 = p.getNode2();

            int i1 = indexOf.get(c1);
            int i2 = indexOf.get(c2);

            int r1 = root.applyAsInt(i1);
            int r2 = root.applyAsInt(i2);

            if (r1 != r2) {
                union.applyAsInt(r1, r2);
                components--;

                if (components == 1) {
                    lastProduct = (long) c1.getX() * c2.getX();
                    break;
                }
            }
        }

        return lastProduct;
    }
}