package data;

import business_objects.builders.UserBuilder;
import business_objects.entities.User;
import environment.Environment;
import utils.JsonParsingUtil;
import utils.StringGeneratorUtils;

import static data.CryptedPass.getDecodedPass;
import static utils.StringGeneratorUtils.getRandomPassword;

public class TestData {
    private static final String TEST_DATA_JSON_PATH = "src/test/resources/test_data.json";

    public static User getNewUser() {
        return new UserBuilder().setEmail(StringGeneratorUtils.getRandomEmail())
                .setPassword(getRandomPassword())
                .setFirstName(StringGeneratorUtils.getRandomName())
                .setLastName(StringGeneratorUtils.getRandomName())
                .setAddress(StringGeneratorUtils.getRandomName())
                .setCity(StringGeneratorUtils.getRandomName())
                .setCountry("United States")
                .setState("Alabama")
                .setZip(StringGeneratorUtils.getRandomNumber())
                .setPhone(StringGeneratorUtils.getRandomNumber())
                .make();
    }

    public static User getExistingUser() {
        return new UserBuilder().setEmail(Environment.getEnvProperty("userEmail")).setPassword(getDecodedPass()).make();
    }

    public static User[] getUsersWithInvalidEmails() {
        String validPassword = JsonParsingUtil.getValue(TEST_DATA_JSON_PATH, "valid_pass");

        String[] invalidEmails = JsonParsingUtil.getArray(TEST_DATA_JSON_PATH, "invalid_emails");
        User[] usersWithInvalidEmails = new User[invalidEmails.length];
        for (int i = 0; i < usersWithInvalidEmails.length; i++) {
            usersWithInvalidEmails[i] = new UserBuilder().setEmail(invalidEmails[i]).setPassword(validPassword).make();
        }
        return usersWithInvalidEmails;
    }

    public static User[] getUsersWithInvalidPasswords() {
        String[] invalidPasswords = JsonParsingUtil.getArray(TEST_DATA_JSON_PATH, "invalid_passwords");
        String validEmail = StringGeneratorUtils.getRandomEmail();
        User[] usersWithInvalidPasswords = new User[invalidPasswords.length];
        for (int i = 0; i < usersWithInvalidPasswords.length; i++) {
            usersWithInvalidPasswords[i] = new UserBuilder().setEmail(validEmail).setPassword(invalidPasswords[i]).make();
        }
        return usersWithInvalidPasswords;
    }


}
