package utils;

import driver.DriverWrapper;
import environment.Environment;
import io.qameta.allure.Allure;
import lombok.SneakyThrows;


import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

public class ReportingHelper {
    @SneakyThrows
    public static void attachEnvironmentInfo() {
        OutputStream output = new FileOutputStream("allure-results/environment.properties");
        Properties properties = new Properties();
        properties.setProperty("Browser", Environment.getEnvProperty("browser"));
        properties.store(output, "Environment info");
    }

    public static void attachScreenshot(String name) {
        byte[] screenshotByteArray = DriverWrapper.takeScreenshot();

        Log.log("attaching screenshot");

        Allure.addAttachment(name, new ByteArrayInputStream(screenshotByteArray));

        Log.log("screenshot attached");
    }
}


