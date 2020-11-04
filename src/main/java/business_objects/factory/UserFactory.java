package business_objects.factory;

import business_objects.builders.UserBuilder;
import business_objects.entities.User;
import utils.StringGeneratorUtils;

import static data.TestData.PASSWORD;
import static data.TestData.USERNAME;
import static utils.StringGeneratorUtils.getPassword;

public class UserFactory {
    public static User getNewUser() {
        return new UserBuilder().setEmail(StringGeneratorUtils.getEmail()).setPassword(getPassword()).make();
    }

    public static User getExistingUser() {
        return new UserBuilder().setEmail(USERNAME).setPassword(PASSWORD).make();
    }
}
