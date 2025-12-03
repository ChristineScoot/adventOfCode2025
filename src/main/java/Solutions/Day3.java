package Solutions;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day3 {
    public static long part1(String filePath) throws IOException {
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] fileContentString = fileContent.split("\\r?\\n");

        int sum = 0;
        for (String s : fileContentString) {
            List<Integer> batteriesLine = new ArrayList<>();
            for (char character : s.toCharArray()) {
                batteriesLine.add(Integer.parseInt(String.valueOf(character)));
            }
            int maxNumber = 0;
            for (int i = 0; i < batteriesLine.size() - 1; i++) {
                for (int j = i + 1; j < batteriesLine.size(); j++) {
                    int tmp = batteriesLine.get(i) * 10 + batteriesLine.get(j);
                    if (tmp > maxNumber) {
                        maxNumber = tmp;
                    }
                }
            }
            sum += maxNumber;
        }

        return sum;
    }

    public static BigInteger part2(String filePath) throws IOException {
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] fileContentString = fileContent.split("\\r?\\n");

        BigInteger sum = BigInteger.ZERO;
        for (String s : fileContentString) {
            sum = sum.add(new BigInteger(maxSubsequenceOfLengthK(s, 12)));
        }
        return sum;
    }

    static String maxSubsequenceOfLengthK(String line, int n) {
        StringBuilder maxNumber = new StringBuilder(n);
        int start = 0;
        for (int pos = 0; pos < n; pos++) {
            int end = line.length() - (n - pos);
            int bestIdx = start;
            char bestChar = line.charAt(start);
            for (int i = start + 1; i <= end; i++) {
                char nextChar = line.charAt(i);
                if (nextChar > bestChar) {
                    bestChar = nextChar;
                    bestIdx = i;
                    if (bestChar == '9') break;
                }
            }
            maxNumber.append(bestChar);
            start = bestIdx + 1;
        }
        return maxNumber.toString();
    }
}