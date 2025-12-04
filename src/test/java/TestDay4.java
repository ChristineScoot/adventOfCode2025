import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

public class TestDay4 {
    int day = 4;

    @Test
    public void part1() throws Exception {
        Method method = Class.forName("Solutions.Day" + day).getMethod("part1", String.class);
        assertEquals(13, method.invoke(null, String.format("src/main/resources/input%d_1eg.txt", day)));
    }

    @Test
    public void part2() throws Exception {
        Method method = Class.forName("Solutions.Day" + day).getMethod("part2", String.class);
        assertEquals(43, method.invoke(null, String.format("src/main/resources/input%d_1eg.txt", day)));
    }
}
