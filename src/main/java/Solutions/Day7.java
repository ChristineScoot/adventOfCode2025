package Solutions;

import Helper.Coord;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day7 {

    public static long part1(String filePath) throws IOException {
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] fileContentString = fileContent.split("\\r?\\n");
        Character[][] grid = new Character[fileContentString.length][];
        for (int i = 0; i < fileContentString.length; i++) {
            grid[i] = fileContentString[i].chars().mapToObj(c -> (char) c).toArray(Character[]::new);
        }
        Coord start = new Coord(grid[0].length / 2, 0);
        Queue<Coord> queue = new LinkedList<>();
        Set<Coord> splitters = new HashSet<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Coord next = queue.poll();
            while (!next.outOfBounds(grid[0].length, grid.length) && grid[next.getY()][next.getX()] != '^') {
                next = next.add(new Coord(0, 1));
            }
            if (next.outOfBounds(grid[0].length, grid.length)) continue;
            splitters.add(next);
            Coord left = next.add(new Coord(-1, 0));
            Coord right = next.add(new Coord(1, 0));
            if (!queue.contains(left))
                queue.add(left);
            if (!queue.contains(right))
                queue.add(right);
        }
        return splitters.size();
    }

    public static long part2(String filePath) throws IOException {
        timelines = new HashMap<>();
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] fileContentString = fileContent.split("\\r?\\n");
        Character[][] grid = new Character[fileContentString.length][];
        for (int i = 0; i < fileContentString.length; i++) {
            grid[i] = fileContentString[i].chars().mapToObj(c -> (char) c).toArray(Character[]::new);
        }
        Coord start = new Coord(grid[0].length / 2, 0);
        return calc(start, grid);
    }

    private static Map<Coord, Long> timelines; //cache

    /**
     * returns number of timelines for that position
     */
    private static long calc(Coord next, Character[][] grid) {
        if (timelines.containsKey(next))
            return timelines.get(next);
        if (next.outOfBounds(grid[0].length, grid.length)) {
            timelines.put(next, 1L);
            return 1;
        }
        if (grid[next.getY()][next.getX()] == '.' || grid[next.getY()][next.getX()] == 'S') {
            long tmp = calc(next.add(new Coord(0, 1)), grid);
            timelines.put(next, tmp);
            return tmp;
        } else {
            Coord left = next.add(new Coord(-1, 0));
            Coord right = next.add(new Coord(1, 0));
            long both = calc(left, grid) + calc(right, grid);
            timelines.put(next, both);
            return both;
        }
    }
}