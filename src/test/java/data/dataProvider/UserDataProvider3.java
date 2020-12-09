package data.dataProvider;

import org.testng.annotations.DataProvider;

import utils.JsonParsingUtil;

/*
These data providers take data from the resources/ directory from the user_test_data json file, one provider is for emails, another for passwords.
They take values from specified keys with the help of
 */

public class UserDataProvider3 {
    private static final String TEST_DATA_FILE_PATH = System.getProperty("user.dir") + "/src/test/resources/user_test_data.json";

    @DataProvider
    public static Object[][] invalidPasswords() {
        return buildATwoDimensionalArray(JsonParsingUtil.getArray(TEST_DATA_FILE_PATH, "invalid_passwords"));
    }

    @DataProvider
    public static Object[][] invalidEmails() {
        return buildATwoDimensionalArray(JsonParsingUtil.getArray(TEST_DATA_FILE_PATH, "invalid_emails"));
    }


    private static Object[][] buildATwoDimensionalArray(Object[] array) {
        int rows = array.length;
        Object[][] newArray = new Object[rows][1];

        for (int i = 0; i < rows; i++) {
            newArray[i][0] = array[i];
        }
        return newArray;
    }


}
