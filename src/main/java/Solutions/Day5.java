package Solutions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Day5 {

    public static long part1(String filePath) throws IOException {
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] fileContentString = fileContent.split("\\r?\\n");
        List<Range> ranges = new ArrayList<>();
        int iter = 0;
        String current = fileContentString[iter];
        while (!current.isEmpty()) {
            ranges.add(new Range(Long.parseLong(current.split("-")[0]), Long.parseLong(current.split("-")[1])));
            current = fileContentString[++iter];
        }
        int sum = 0;
        List<Long> ingredientId = new ArrayList<>();
        for (int i = ++iter; i < fileContentString.length; i++) {
            ingredientId.add(Long.parseLong(fileContentString[i]));
        }
        ranges.sort(Comparator.comparingLong(Range::x));
        Collections.sort(ingredientId);
        for (int i = 0; i < ingredientId.size(); ) {
            for (int j = 0; j < ranges.size(); ) {
                Long currId = ingredientId.get(i);
                Long currX = ranges.get(j).x();
                Long currY = ranges.get(j).y();
                if (currId < currX) {
                    i++;
                    j = (j - 1 < 0) ? j : j - 1; //if next falls to a skipped range
                    continue;
                }
                if (currId > currY) {
                    j++;
                    if (j >= ranges.size()) i++;
                    continue;
                }

                i++;
                sum++;
            }
        }

        return sum;
    }

    public static long part2(String filePath) throws IOException {
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] fileContentString = fileContent.split("\\r?\\n");
        List<Range> ranges = new ArrayList<>();
        int iter = 0;
        String current = fileContentString[iter];
        while (!current.isEmpty()) {
            ranges.add(new Range(Long.parseLong(current.split("-")[0]), Long.parseLong(current.split("-")[1])));
            current = fileContentString[++iter];
        }

        long sum = 0L;

        ranges.sort(Comparator.comparingLong(Range::x));
        long previousY = 0L;
        for (Range range : ranges) {
            if (previousY >= range.y()) {
                continue; //skip if range withing range
            }
            sum += range.y() - (range.x() - 1L);
            if (previousY >= range.x())
                sum -= (previousY - range.x() + 1L);
            previousY = range.y();
        }

        return sum;
    }
}