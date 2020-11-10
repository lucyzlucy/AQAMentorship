package environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Environment {
    static final String environmentPath = "src/test/resources/environment.properties";
    private Properties applicationProps;
    static Environment environment;

    private Environment(){
        applicationProps = new Properties();
        FileInputStream in;
        try {
            in = new FileInputStream(new File(environmentPath));
            applicationProps.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        if (environment == null) {
            environment = new Environment();
        }
        return environment.applicationProps.getProperty(key);
    }
}
