package driver;

import environment.Environment;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverWrapper {
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

}
