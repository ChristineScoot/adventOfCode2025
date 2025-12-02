import Solutions.Day2;
import org.junit.Test;

import java.io.IOException;
import java.math.BigInteger;

import static org.junit.Assert.assertEquals;


public class TestDay2 {

    @Test
    public void part1() throws IOException {
        assertEquals(1227775554, Day2.part1("src/main/resources/input2_1eg.txt"));
    }

    @Test
    public void part2() throws IOException {
        assertEquals(new BigInteger("4174379265"), Day2.part2("src/main/resources/input2_1eg.txt"));
    }
}
