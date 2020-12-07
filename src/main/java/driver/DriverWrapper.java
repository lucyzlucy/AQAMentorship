package driver;

import driver.elements.FieldDecorator;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class DriverWrapper {
    protected static final int WAIT_ELEMENT_TIMEOUT = 10;
    private static WebDriver driver;

    static {
        driver = DriverFactory.getDriver();
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
        PageFactory.initElements(new FieldDecorator(driver), page);
    }

    public static void refresh() {
        driver.navigate().refresh();
    }


}
