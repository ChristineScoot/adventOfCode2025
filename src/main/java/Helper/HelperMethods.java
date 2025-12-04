package Helper;

public class HelperMethods {
    public static <T> void print(T[][] array) {
        for (T[] col : array) {
            for (T row : col) {
                System.out.print(row);
            }
            System.out.println();
        }
    }
}
