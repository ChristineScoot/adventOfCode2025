package Solutions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Day1 {
    public static int part1(String filePath) throws IOException {
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] fileContentString = fileContent.split("\\r?\\n");
        List<Map.Entry<Character, Integer>> list = Arrays.stream(fileContentString)
                .map(s -> Map.entry(s.charAt(0), Integer.parseInt(s.substring(1))))
                .toList();
        int sum = 0;
        int dial = 50;

        for (Map.Entry<Character, Integer> l : list) {
            dial += (l.getKey().equals('L') ? l.getValue() * (-1) : l.getValue());
            dial = (dial % 100 + 100) % 100;
            sum += dial == 0 ? 1 : 0;
        }

        return sum;
    }

    public static int part2(String filePath) throws IOException {
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] fileContentString = fileContent.split("\\r?\\n");
        List<Map.Entry<Character, Integer>> list = Arrays.stream(fileContentString)
                .map(s -> Map.entry(s.charAt(0), Integer.parseInt(s.substring(1))))
                .toList();
        int sum = 0;
        int dial = 50;

        for (Map.Entry<Character, Integer> l : list) {
            int value = l.getValue();
            int delta = (l.getKey() == 'L') ? -value : value;

            int end = Math.floorMod(dial + delta, 100);

            int crossings = 0;
            if (delta > 0) {
                int k0 = (100 - dial) % 100;
                if (k0 == 0) k0 = 100;
                if (value >= k0) {
                    crossings = 1 + (value - k0) / 100;
                }
            } else if (delta < 0) {
                int d = -delta;
                int k0 = dial % 100;
                if (k0 == 0) k0 = 100;
                if (d >= k0) {
                    crossings = 1 + (d - k0) / 100;
                }
            }

            sum += crossings;
            dial = end;
        }

        return sum;
    }
}
