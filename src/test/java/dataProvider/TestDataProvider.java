package dataProvider;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider
    public static Object[][] invalidEmails() {
        return new Object[][]{
                {"example.com"},
                {"A@b@c@domain.com"},
                {"ab(c)d,e:f;gij\\k]l@domain.com"}};
    }

    @DataProvider
    public static Object[][] invalidPasswords() {
        return new Object[][]{
                {"abcd"},
                {"1234"}
        };
    }
}