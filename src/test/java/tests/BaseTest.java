package tests;

import driver.DriverWrapper;
import org.testng.annotations.*;

public class BaseTest {

    @AfterMethod
    public void tearDown() {
        DriverWrapper.clearBrowserCookies();
    }


    @AfterClass
    public void close() {
        DriverWrapper.killDriverInstance();
    }
}
