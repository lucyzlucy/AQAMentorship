package environment;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class Environment {
    static PropertiesConfiguration applicationProps;
    private final String environmentPath = "src/test/resources/environment.properties";

    private Environment() {
        try {
            applicationProps = new PropertiesConfiguration(environmentPath);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }

    }

    public static String getEnvProperty(String key) {
        if (applicationProps == null) {
            new Environment();
        }
        return applicationProps.getProperty(key).toString();
    }
}
