import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

public class TestDay7 {
    int day = 7;

    @Test
    public void part1() throws Exception {
        Method method = Class.forName("Solutions.Day" + day).getMethod("part1", String.class);
        assertEquals(21L, method.invoke(null, String.format("src/main/resources/input%d_1eg.txt", day)));
    }

    @Test
    public void part2() throws Exception {
        Method method = Class.forName("Solutions.Day" + day).getMethod("part2", String.class);
        assertEquals(40L, method.invoke(null, String.format("src/main/resources/input%d_1eg.txt", day)));
    }
}
