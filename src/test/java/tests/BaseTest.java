package tests;

import driver.DriverWrapper;
import org.testng.annotations.*;
import testng.listeners.TestListener;

@Listeners({TestListener.class})
public class BaseTest {

    @BeforeSuite
    public void setUp() {
        DriverWrapper.initDriver();
    }

    @AfterTest
    public void tearDown() {
        DriverWrapper.clearBrowserCookies();
    }

    @AfterSuite
    public void close() {
        DriverWrapper.killDriverInstance();
    }
}
