package driver;

import environment.Environment;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.Console;
import java.util.logging.Level;

public class DriverFactory {
    static WebDriver getDriver() {
        String type = Environment.getEnvProperty("browser");

        switch (type) {
            case "Chrome":
                LoggingPreferences logs = new LoggingPreferences();
                logs.enable(LogType.BROWSER, Level.ALL);
//                logs.enable(LogType.CLIENT, Level.ALL);
                logs.enable(LogType.DRIVER, Level.INFO);
//                logs.enable(LogType.PERFORMANCE, Level.ALL);
//                logs.enable(LogType.PROFILER, Level.ALL);
//                logs.enable(LogType.SERVER, Level.ALL);

                DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                desiredCapabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
                return new ChromeDriver(desiredCapabilities);
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

    static WebDriver getFiringDriver() {
        EventFiringWebDriver firingDriver = new EventFiringWebDriver(getDriver());
        firingDriver.register(new EventHandler());
        return firingDriver;
    }


}
