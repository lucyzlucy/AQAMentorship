package driver;

import environment.Environment;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    static WebDriver getDriver() {
        String type = Environment.getEnvProperty("browser");

        switch (type) {
            case "Chrome":
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
                return new ChromeDriver();
            case "Edge":
                System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\msedgedriver.exe");
                return new EdgeDriver();
            case "Firefox":
                System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\msedgedriver.exe");
                return new FirefoxDriver();
            default:
                System.out.println("No browser");
                System.exit(-1);
                return null;
        }
    }
}
