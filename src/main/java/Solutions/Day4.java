package Solutions;

import Helper.Coord;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day4 {
    private static final Character roll = '@';
    static Set<Coord> queue;

    public static int part1(String filePath) throws IOException {
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] fileContentString = fileContent.split("\\r?\\n");
        Character[][] grid = new Character[fileContentString.length][];
        int sum = 0;
        for (int i = 0; i < fileContentString.length; i++) {
            grid[i] = fileContentString[i].chars().mapToObj(c -> (char) c).toArray(Character[]::new);
        }
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] != roll) continue;
                if (canAccess(calculateNeighbours(grid, row, col))) {
                    sum++;
                }
            }
        }
        return sum;
    }

    private static boolean canAccess(int neighbours) {
        return neighbours < 4;
    }

    private static int calculateNeighbours(Character[][] grid, int row, int col) {
        List<Integer> dir = List.of(-1, 0, 1);
        int sum = 0;
        for (Integer x : dir) {
            for (Integer y : dir) {
                if (x == 0 & y == 0) continue;
                try {
                    if (grid[row + y][col + x] == roll) sum++;
                } catch (ArrayIndexOutOfBoundsException e) {
                    //out of bounds - skip
                }
            }
        }

        return sum;
    }

    public static int part2(String filePath) throws IOException {
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] fileContentString = fileContent.split("\\r?\\n");
        Character[][] grid = new Character[fileContentString.length][];
        int sum = 0;
        for (int i = 0; i < fileContentString.length; i++) {
            grid[i] = fileContentString[i].chars().mapToObj(c -> (char) c).toArray(Character[]::new);
        }

        do {
            queue = new HashSet<>();
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[row].length; col++) {
                    if (grid[row][col] != roll) continue;
                    if (canAccess(calculateNeighbours(grid, row, col))) {
                        queue.add(new Coord(col, row));
                    }
                }
            }
            queue.forEach(coord -> grid[coord.getY()][coord.getX()] = 'x');
            sum += queue.size();
        } while (!queue.isEmpty());
        return sum;
    }
}