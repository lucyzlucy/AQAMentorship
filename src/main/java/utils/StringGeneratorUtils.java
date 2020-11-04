package utils;

import org.apache.commons.lang.RandomStringUtils;


public class StringGeneratorUtils {
    public static String getEmail() {
        StringBuilder str = new StringBuilder();
        str.append(RandomStringUtils.randomAlphabetic(5)).append("@").append(RandomStringUtils.randomAlphabetic(3)).append(".com");
        return str.toString();
    }

    public static String getPassword() {
        StringBuilder str = new StringBuilder();
        str.append(RandomStringUtils.randomAlphabetic(6));
        return str.toString();
    }
}
