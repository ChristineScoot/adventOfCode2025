import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

public class TestDay9 {
    int day = 9;

    @Test
    public void part1() throws Exception {
        Method method = Class.forName("Solutions.Day" + day).getMethod("part1", String.class);
        assertEquals(50L, method.invoke(null, String.format("src/main/resources/input%d_1eg.txt", day)));
    }

    @Test
    public void part2() throws Exception {
        Method method = Class.forName("Solutions.Day" + day).getMethod("part2", String.class);
        assertEquals(24L, method.invoke(null, String.format("src/main/resources/input%d_1eg.txt", day)));
    }

    @Test
    public void part2b() throws Exception {
        Method method = Class.forName("Solutions.Day" + day).getMethod("part2", String.class);
        assertEquals(15L, method.invoke(null, String.format("src/main/resources/input%d_2eg.txt", day)));
    }
}
