package Solutions;

import Helper.Coord;
import Model.Rectangle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day9 {

    public static long part1(String filePath) throws IOException {
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] fileContentString = fileContent.split("\\r?\\n");
        List<Coord> allCoords = new ArrayList<>();
        for (String line : fileContentString) {
            String[] splitted = line.split(",");
            Coord coord = new Coord(Integer.parseInt(splitted[0]), Integer.parseInt(splitted[1]));
            allCoords.add(coord);
        }

        PriorityQueue<Rectangle> rectangles = new PriorityQueue<>();
        for (int i = 0; i < allCoords.size() - 1; i++) {
            for (int j = i + 1; j < allCoords.size(); j++) {
                rectangles.add(new Rectangle(allCoords.get(i), allCoords.get(j)));
            }
        }

        return Objects.requireNonNull(rectangles.poll()).getArea();
    }

    public static long part2(String filePath) throws IOException {
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] fileContentString = fileContent.split("\\r?\\n");
        List<Coord> allCoords = new LinkedList<>();
        for (String line : fileContentString) {
            String[] splitted = line.split(",");
            int x = Integer.parseInt(splitted[0]);
            int y = Integer.parseInt(splitted[1]);
            Coord coord = new Coord(x, y);
            allCoords.add(coord);
        }

//calculating rectangles
        PriorityQueue<Rectangle> rectangles = new PriorityQueue<>();
        for (int i = 0; i < allCoords.size() - 1; i++) {
            for (int j = i; j < allCoords.size(); j++) {
                Coord a = allCoords.get(i);
                Coord b = allCoords.get(j);
                rectangles.add(new Rectangle(a, b));
            }
        }

//calculating a grid / relevant coordinates
        List<Integer> xs = new ArrayList<>(); //storing unique values
        List<Integer> ys = new ArrayList<>();
        for (Coord coord : allCoords) {
            if (!xs.contains(coord.getX())) xs.add(coord.getX());
            if (!ys.contains(coord.getY())) ys.add(coord.getY());
        }
        Collections.sort(xs);
        Collections.sort(ys);

        Map<Integer, Integer> xToGrid = new HashMap<>();
        Map<Integer, Integer> yToGrid = new HashMap<>();
        for (int i = 0; i < xs.size(); i++) {
            xToGrid.put(xs.get(i), i * 2);
        }
        for (int i = 0; i < ys.size(); i++) {
            yToGrid.put(ys.get(i), i * 2);
        }

        int W = xs.size() * 2 - 1;
        int H = ys.size() * 2 - 1;
        boolean[][] grid = new boolean[W][H];
        for (int i = 0; i < allCoords.size(); i++) {
            int next = (i + 1) != allCoords.size() ? (i + 1) : 0;
            int cx1 = xToGrid.get(allCoords.get(i).getX());
            int cy1 = yToGrid.get(allCoords.get(i).getY());
            int cx2 = xToGrid.get(allCoords.get(next).getX());
            int cy2 = yToGrid.get(allCoords.get(next).getY());
            //fill the grid
            for (int j = Math.min(cx1, cx2); j <= Math.max(cx1, cx2); j++) {
                for (int k = Math.min(cy1, cy2); k <= Math.max(cy1, cy2); k++) {
                    grid[j][k] = true;
                }
            }
        }

        //flood fill
        boolean[][] outsideArr = new boolean[W][H];
        ArrayDeque<Coord> q = new ArrayDeque<>();
        for (int x = 0; x < W; x++) {
            if (!grid[x][0]) {
                outsideArr[x][0] = true;
                q.add(new Coord(x, 0));
            }
            if (!grid[x][H - 1]) {
                outsideArr[x][H - 1] = true;
                q.add(new Coord(x, H - 1));
            }
        }
        for (int y = 0; y < H; y++) {
            if (!grid[0][y]) {
                outsideArr[0][y] = true;
                q.add(new Coord(0, y));
            }
            if (!grid[W - 1][y]) {
                outsideArr[W - 1][y] = true;
                q.add(new Coord(W - 1, y));
            }
        }

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        while (!q.isEmpty()) {
            Coord p = q.poll();
            for (int k = 0; k < 4; k++) {
                int nx = p.getX() + dx[k];
                int ny = p.getY() + dy[k];
                if (nx < 0 || ny < 0 || nx >= W || ny >= H) continue; //grid bounds
                if (outsideArr[nx][ny]) continue; //already calculated
                if (grid[nx][ny]) continue; //wall
                outsideArr[nx][ny] = true;
                q.add(new Coord(nx, ny));
            }
        }

        Rectangle rect = new Rectangle(new Coord(0, 0), new Coord(0, 0));
        while (!rectangles.isEmpty()) {
            rect = rectangles.poll();
            //cast rect coords
            int xa = xToGrid.get(rect.getA().getX());
            int ya = yToGrid.get(rect.getA().getY());
            int xb = xToGrid.get(rect.getB().getX());
            int yb = yToGrid.get(rect.getB().getY());

            if (outsideArr[xa][yb] || outsideArr[xb][ya]) continue;
            boolean isValid = true;
            for (int i = Math.min(xa, xb); i <= Math.max(xa, xb); i++) {
                for (int j = Math.min(ya, yb); j <= Math.max(ya, yb); j++) {
                    if (outsideArr[i][j]) {
                        isValid = false;
                        break;
                    }
                }
            }
            if (isValid) break;
        }
        return rect.getArea();
    }
}