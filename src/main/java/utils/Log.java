package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

public class Log {
    private static Logger logger = null;

    public static Logger setName(String name) {
        logger = LoggerFactory.getLogger(String.format("%s", name));
        return logger;
    }

    public static Logger getLogger() {
        if (logger != null) {
            return logger;
        } else {
            logger = setName("default test");
            return logger;
        }


    }

    public static void log(String message) {
        getLogger().info(message);
    }

    public static void log(String message, Level type) {
        switch (type) {
            case INFO:
                getLogger().info(message);
                break;
            case ERROR:
                getLogger().error(message);
                break;
            case WARN:
                getLogger().warn(message);
                break;
        }
    }
}
