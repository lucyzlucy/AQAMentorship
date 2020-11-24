package tests;

import driver.DriverWrapper;
import org.testng.annotations.*;

public class BaseTest {

    @AfterMethod
    public void tearDown() {
        DriverWrapper.clearBrowserCookies();
    }

    @AfterSuite
    public void close() {

        DriverWrapper.killDriverInstance();
    }

}
