package tests;

import driver.DriverWrapper;
import environment.Environment;
import environment.Environment1;
import org.testng.annotations.*;

public class BaseTest {
    Environment environment;

    @BeforeSuite
    public void setUp() {
        Environment1 environment = new Environment1("src/test/resources/environment.properties");
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
