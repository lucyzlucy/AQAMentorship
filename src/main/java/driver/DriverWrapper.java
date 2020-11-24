package driver;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DriverWrapper {
    protected static final int WAIT_ELEMENT_TIMEOUT = 10;
    private static WebDriver driver = DriverFactory.getDriver();

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


    public static WebElement getVisibleElement(String xpath) {

        List<WebElement> elements = driver.findElements(By.xpath("//p[@class=\"product-name\"]"));
        for (WebElement element : elements) {
            if (element.isDisplayed()) {
                return element;
            }
        }
        return null;
    }

    public static void initElements(Object page) {
        PageFactory.initElements(driver, page);
    }


}
