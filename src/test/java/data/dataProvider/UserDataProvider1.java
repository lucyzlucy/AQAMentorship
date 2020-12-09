package data.dataProvider;

import data.UserFactory;
import org.testng.annotations.DataProvider;
import utils.ArrayUtil;


/*
This data provider takes data from the UserFactory class and formats it with a help of ArrayUtil.buildATwoDimensionalArray util
 */

public class UserDataProvider1 {

    @DataProvider
    public static Object[][] invalidEmails() {
        return ArrayUtil.buildATwoDimensionalArray(UserFactory.getUsersWithInvalidEmails());
    }

    @DataProvider
    public static Object[][] invalidPasswords() {
        return ArrayUtil.buildATwoDimensionalArray(UserFactory.getUsersWithInvalidPasswords());
    }

}