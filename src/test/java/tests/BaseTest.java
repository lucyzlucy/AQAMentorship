package tests;

import driver.DriverWrapper;
import environment.Environment;
import org.testng.annotations.*;

public class BaseTest {
    Environment environment;

    @BeforeSuite
    public void setUp() {
        Environment environment = new Environment("src/test/resources/environment.properties");
    }

    @AfterMethod
    public void tearDown() {
        DriverWrapper.clearBrowserCookies();
    }

    @AfterSuite
    public void close() {

        DriverWrapper.killDriverInstance();
    }

}
