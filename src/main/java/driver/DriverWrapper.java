package driver;

import environment.Environment;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverWrapper {
    protected static final int WAIT_ELEMENT_TIMEOUT = 10;
    private static WebDriver driver;

    private DriverWrapper() {
        String type = Environment.getEnvProperty("browser");

        switch (type) {
            case "Chrome":
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "Edge":
                System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\msedgedriver.exe");
                driver = new EdgeDriver();
                break;
            case "Firefox":
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\geckodriver.exe");
                driver = new FirefoxDriver();
                break;
        }
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            new DriverWrapper();
        }
        return driver;
    }

    public static void killDriverInstance() {
        driver.close();
        driver = null;
    }

    public static void clearBrowserCookies() {
        driver.manage().deleteAllCookies();
    }

    private static void waitForElementPresent(WebElement element) {
        new WebDriverWait(getDriver(), WAIT_ELEMENT_TIMEOUT).until(ExpectedConditions.visibilityOf(element));
    }
    public static boolean isElementLoaded(WebElement element) {
        try {
            waitForElementPresent(element);
            return true;
        } catch (TimeoutException e){
            return false;
        }
    }


}
