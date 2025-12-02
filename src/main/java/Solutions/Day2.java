package Solutions;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Day2 {
    public static long part1(String filePath) throws IOException {
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] fileContentString = fileContent.split(",");
        List<Range> list = Arrays.stream(fileContentString)
                .map(s -> {
                    String[] range = s.split("-");
                    return new Range(Long.parseLong(range[0].trim()), Long.parseLong(range[1].trim()));
                })
                .toList();
        long sum = 0;
        for (Range range : list) {
            for (long i = range.x(); i <= range.y(); i++) {
                String string = String.valueOf(i);
                if (string.length() % 2 != 0)
                    continue;
                if (string.substring(0, string.length() / 2).equals(string.substring((string.length() / 2))))
                    sum += i;
            }
        }

        return sum;
    }

    public static BigInteger part2(String filePath) throws IOException {
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] fileContentString = fileContent.split(",");
        List<Range> list = Arrays.stream(fileContentString)
                .map(s -> {
                    String[] range = s.split("-");
                    return new Range(Long.parseLong(range[0].trim()), Long.parseLong(range[1].trim()));
                })
                .toList();
        long sum = 0;
        for (Range range : list) {
            for (long i = range.x(); i <= range.y(); i++) {
                String string = String.valueOf(i);
                for (int j = 1; j < string.length(); j++) {
                    String subNumber = string.substring(0, j);
                    if (string.matches("(" + subNumber + "){2,}")) {
                        sum += i;
                        break;
                    }
                }
            }
        }

        return new BigInteger(String.valueOf(sum));
    }
}

record Range(long x, long y) {
}