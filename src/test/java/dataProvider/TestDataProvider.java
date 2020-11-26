package dataProvider;

import data.TestData;
import org.testng.annotations.DataProvider;
import utils.ArrayUtil;

public class TestDataProvider {

    @DataProvider
    public static Object[][] invalidEmails() {
        return ArrayUtil.buildATwoDimensionalArray(TestData.getUsersWithInvalidEmails());
    }

    @DataProvider
    public static Object[][] invalidPasswords() {
        return ArrayUtil.buildATwoDimensionalArray(TestData.getUsersWithInvalidPasswords());
    }
}