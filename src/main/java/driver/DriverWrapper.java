package driver;

import driver.elements.FieldDecorator;
import environment.Environment;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Log4j2
public class DriverWrapper {
    protected static final int WAIT_ELEMENT_TIMEOUT = Integer.parseInt(Environment.getEnvProperty("driver.timeout"));

    private static WebDriver driver = DriverFactory.getFiringDriver();

    public static void initDriver() {
        if (driver == null) {
            driver = DriverFactory.getFiringDriver();
        }
    }

    public static void killDriverInstance() {
        driver.close();
        driver = null;
    }

    public static void clearBrowserCookies() {
        driver.manage().deleteAllCookies();
    }

    private static void waitForElementPresent(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_ELEMENT_TIMEOUT)).until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitImplicitly(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static boolean isElementLoaded(WebElement element) {
        try {
            waitForElementPresent(element);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public static String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public static void navigateToPage(String url) {
        driver.get(url);
    }

    public static void initElements(Object page) {
        PageFactory.initElements(new FieldDecorator((driver), WAIT_ELEMENT_TIMEOUT), page);
    }

    public static void refresh() {
        driver.navigate().refresh();
    }

    public static void showLogs() {
        Logs logs = driver.manage().logs();
        LogEntries logEntries = logs.get(LogType.DRIVER);

        for (LogEntry logEntry : logEntries) {
            log.info(logEntry.getMessage());
        }
    }

    public static byte[] takeScreenshot() {
       return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }


}
