package driver;

import driver.elements.FieldDecorator;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DriverWrapperThreadSafe {
    protected static final int WAIT_ELEMENT_TIMEOUT = 10;
    private static final ThreadLocal<WebDriver> DRIVER_THREAD_LOCAL = new ThreadLocal<>();

    public static void initDriver() {
        DRIVER_THREAD_LOCAL.set(DriverFactory.getDriver());
    }

//    public static void getNewDriver() {
//        DRIVER_THREAD_LOCAL = DriverFactory.getDriver();
//
//    }

    public static void killDriverInstance() {
        DRIVER_THREAD_LOCAL.get().close();
        DRIVER_THREAD_LOCAL.get().quit();
        DRIVER_THREAD_LOCAL.remove();
    }

    public static void clearBrowserCookies() {
        DRIVER_THREAD_LOCAL.get().manage().deleteAllCookies();
    }

    private static void waitForElementPresent(WebElement element) {
        new WebDriverWait(DRIVER_THREAD_LOCAL.get(), Duration.ofSeconds(WAIT_ELEMENT_TIMEOUT)).until(ExpectedConditions.visibilityOf(element));
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
        return DRIVER_THREAD_LOCAL.get().getCurrentUrl();
    }

    public static void navigateToPage(String url) {
        DRIVER_THREAD_LOCAL.get().get(url);
    }

    public static void initElements(Object page) {
        PageFactory.initElements(new FieldDecorator(DRIVER_THREAD_LOCAL.get()), page);
    }

    public static void refresh() {
        DRIVER_THREAD_LOCAL.get().navigate().refresh();
    }


}
