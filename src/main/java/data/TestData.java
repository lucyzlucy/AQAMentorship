package data;

import business_objects.builders.UserBuilder;
import business_objects.entities.User;
import utils.JsonParsingUtil;
import utils.Randomizer;
import utils.StringGeneratorUtils;

public class TestData {
    private static final String TEST_DATA_JSON_PATH = "src/test/resources/test_data.json";

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
