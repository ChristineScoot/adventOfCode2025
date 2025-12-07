package Solutions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day6 {

    public static long part1(String filePath) throws IOException {
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] fileContentString = fileContent.split("\\r?\\n");
        List<long[]> numbers = new ArrayList<>();
        String[] operations = new String[1];

        for (String line : fileContentString) {
            String[] subline = line.trim().split("\\s+");
            if (subline[0].equals("+") || subline[0].equals("*")) {
                operations = subline.clone();
                break;
            }
            numbers.add(Stream.of(subline).mapToLong(Long::parseLong).toArray());
        }

        long sum = 0;
        for (int i = 0; i < operations.length; i++) {
            long tmp = operations[i].equals("+") ? 0 : 1;
            for (long[] number : numbers) {
                if (operations[i].equals("+")) {
                    tmp += number[i];
                } else if (operations[i].equals("*")) {
                    tmp *= number[i];
                }
            }
            sum += tmp;
        }

        return sum;
    }

    public static long part2(String filePath) throws IOException {
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] fileContentString = fileContent.split("\\r?\\n");
        List<String> numbers = new ArrayList<>();
        char[] operations = new char[1];

        for (String line : fileContentString) {
            char[] subline = line.toCharArray();
            if (subline[0] == '+' || subline[0] == '*') {
                operations = subline.clone();
                break;
            }
            for (int i = 0; i < subline.length; i++) {
                if (numbers.size() <= i)
                    numbers.add("");
                numbers.set(i, numbers.get(i).concat(String.valueOf(subline[i])));
            }
        }

        long sum = 0;
        for (int i = 0; i < operations.length; i++) {
            char currentOperation = operations[i];
            long tmp = currentOperation == '+' ? 0 : 1;
            if (currentOperation == ' ')
                break;
            for (int j = i; j < numbers.size(); j++) {
                String number = numbers.get(j);
                if (number.trim().isEmpty()) {
                    i = j;
                    break;
                }
                long currentNumber;
                try {
                    currentNumber = Long.parseLong(number.trim());
                } catch (NumberFormatException e) {
                    //empty, skip
                    break;
                }
                if (currentOperation == '+') {
                    tmp += currentNumber;
                } else if (currentOperation == '*') {
                    tmp *= currentNumber;
                }
            }
            sum += tmp;
        }

        return sum;
    }
}