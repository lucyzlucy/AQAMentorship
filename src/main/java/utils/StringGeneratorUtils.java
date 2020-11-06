package utils;

import org.apache.commons.lang.RandomStringUtils;


public class StringGeneratorUtils {
    public static String getRandomEmail() {
        StringBuilder str = new StringBuilder();
        str.append(RandomStringUtils.randomAlphabetic(5)).append("@").append(RandomStringUtils.randomAlphabetic(3)).append(".com");
        return str.toString();
    }

    public static String getRandomPassword() {
        StringBuilder str = new StringBuilder();
        str.append(RandomStringUtils.randomAlphanumeric(6));
        return str.toString();
    }

    public static String getRandomName() {
        StringBuilder str = new StringBuilder();
        str.append(RandomStringUtils.randomAlphabetic(6));
        return str.toString();
    }

    public static String getRandomNumber() {
        StringBuilder str = new StringBuilder();
        str.append(RandomStringUtils.randomNumeric(5));
        return str.toString();
    }
}
