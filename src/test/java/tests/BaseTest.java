package tests;

import driver.DriverWrapper;
import environment.Environment;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.Logs;
import org.testng.annotations.*;
import testng.listeners.TestListener;
import utils.Log;
import utils.ReportingHelper;

@Log4j2
@Listeners({TestListener.class})
public class BaseTest {

    @BeforeSuite
    public void setUp() {
        DriverWrapper.initDriver();

        Log.log("Setup");
    }

    @AfterMethod
    public void tearDown() {
        DriverWrapper.clearBrowserCookies();

        Log.log("Cleared browser cookies");
    }

    @AfterSuite
    public void close() {
        DriverWrapper.showLogs();

        DriverWrapper.killDriverInstance();
        Log.log("Killed driver");

        ReportingHelper.attachEnvironmentInfo();

    }


}
