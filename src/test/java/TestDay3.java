import Solutions.Day3;
import org.junit.Test;

import java.io.IOException;
import java.math.BigInteger;

import static org.junit.Assert.assertEquals;


public class TestDay3 {

    @Test
    public void part1() throws IOException {
        assertEquals(357, Day3.part1("src/main/resources/input3_1eg.txt"));
    }

    @Test
    public void part2() throws IOException {
        assertEquals(new BigInteger("3121910778619"), Day3.part2("src/main/resources/input3_1eg.txt"));
    }
}
