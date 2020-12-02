package utils;

public class StripNonDigitsUtil {
    public static String stripNonDigits(String string) {
        String nonDigitString = string.replaceAll("[^0-9?!\\.]", "");
        return !nonDigitString.equals("") ? nonDigitString : "0";
    }
}
