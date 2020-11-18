package environment;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class Environment {
    private String environmentPath;
    private PropertiesConfiguration applicationProps;

    public Environment(String environmentPath) {
        this.environmentPath = environmentPath;
        try {
            applicationProps = new PropertiesConfiguration(environmentPath);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public String getEnvProperty(String key) {
        return applicationProps.getProperty(key).toString();
    }

    public void setEnvProperty(String key, String value) {
        applicationProps.setProperty(key, value);
        try {
            applicationProps.save();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }
}
