package tests;

import driver.DriverWrapper;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.Logs;
import org.testng.annotations.*;
import testng.listeners.TestListener;

@Log4j2
@Listeners({TestListener.class})
public class BaseTest {

    @BeforeSuite
    public void setUp() {
        DriverWrapper.initDriver();
    }

    @AfterMethod
    public void tearDown() {
        DriverWrapper.clearBrowserCookies();

        log.info("Cleared browser cookies");
    }

    @AfterSuite
    public void close() {


        DriverWrapper.showLogs();

        DriverWrapper.killDriverInstance();

        log.info("Killed driver");
    }
}
