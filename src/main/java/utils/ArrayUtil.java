package utils;

public class ArrayUtil {
    public static Object[][] buildATwoDimensionalArray(Object[] array) {
        int rows = array.length;
        Object[][] newArray = new Object[rows][1];

        for (int i = 0; i < rows; i++) {
            newArray[i][0] = array[i];
        }
        return newArray;
    }
}
