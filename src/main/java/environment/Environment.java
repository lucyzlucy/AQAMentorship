package environment;

import exceptions.NoEnvValueException;
import lombok.SneakyThrows;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class Environment {
    private static String environmentPath = "src/test/resources/environment.properties";
    private static PropertiesConfiguration applicationProps;

    static {
        try {
            applicationProps = new PropertiesConfiguration(environmentPath);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    public static String getEnvProperty(String key) {
        if (applicationProps.getString(key) == null) {
            throw new NoEnvValueException(key);
        }
        else {
            return applicationProps.getString(key);
        }
    }

    public static void setEnvProperty(String key, String value) {
        applicationProps.setProperty(key, value);
        try {
            applicationProps.save();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    public static PropertiesConfiguration getEnv() {
        return applicationProps;
    }
}
