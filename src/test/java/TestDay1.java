import Day1.Day1;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestDay1 {

    @Test
    public void part1() throws IOException {
        assertEquals(3, Day1.part1("src/main/resources/input1_1eg.txt"));
    }

    @Test
    public void part2() throws IOException {
        assertEquals(6, Day1.part2("src/main/resources/input1_1eg.txt"));
    }
}
