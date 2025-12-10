import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

public class TestDay8 {
    int day = 8;

    @Test
    public void part1() throws Exception {
        Method method = Class.forName("Solutions.Day" + day).getMethod("part1", String.class, int.class);
        assertEquals(40L, method.invoke(null, String.format("src/main/resources/input%d_1eg.txt", day), 10));
    }

    @Test
    public void part2() throws Exception {
        Method method = Class.forName("Solutions.Day" + day).getMethod("part2", String.class);
        assertEquals(25272L, method.invoke(null, String.format("src/main/resources/input%d_1eg.txt", day)));
    }
}
