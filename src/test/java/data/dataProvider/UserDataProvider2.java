package data.dataProvider;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
This data provider takes data from the resources/ directory from the directory and file that correspond test class and test method name respectively
 */

public class UserDataProvider2 {
    private static final String TEST_DATA_JSON_PATH = System.getProperty("user.dir") + "/src/test/resources/%s/%s";

    @DataProvider
    public static Object[][] userData(Method method) {
        return buildATwoDimensionalArray(readTxt(method.getDeclaringClass().getName(), method.getName()));
    }

    private static Object[][] buildATwoDimensionalArray(Object[] array) {
        int rows = array.length;
        Object[][] newArray = new Object[rows][1];

        for (int i = 0; i < rows; i++) {
            newArray[i][0] = array[i];
        }
        return newArray;
    }

    private static String[] readTxt(String testClass, String testName) {
        String[] stringArray = null;
        try {
            stringArray = Files.lines(Paths.get(String.format(TEST_DATA_JSON_PATH, testClass.substring(testClass.indexOf(".") + 1), testName))).toArray(String[]::new);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringArray;
    }

}
