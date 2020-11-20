package environment;

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

    public static String getEnvProperty(String key) {
        return applicationProps.getProperty(key).toString();
    }

    public static void setEnvProperty(String key, String value) {
        applicationProps.setProperty(key, value);
        try {
            applicationProps.save();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }
}
