package data.dataProvider;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
This data provider takes data from the resources/userData/ directory from the file that corresponds to test description.
 */

public class UserDataProvider {
    private static final String TEST_DATA_FILE_PATH = System.getProperty("user.dir") + "/src/test/resources/userData/%s";

    @DataProvider
    public static Object[][] userData(Method method) {
        String testDescription = method.getAnnotation(Test.class).description();

        return buildATwoDimensionalArray(readTxt(testDescription));
    }

    private static Object[][] buildATwoDimensionalArray(Object[] array) {
        int rows = array.length;
        Object[][] newArray = new Object[rows][1];

        for (int i = 0; i < rows; i++) {
            newArray[i][0] = array[i];
        }
        return newArray;
    }

    private static String[] readTxt(String testName) {
        String[] stringArray = null;
        try {
            stringArray = Files.lines(Paths.get(String.format(TEST_DATA_FILE_PATH, testName))).toArray(String[]::new);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringArray;
    }
}
