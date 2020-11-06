package business_objects.factory;

import business_objects.builders.UserBuilder;
import business_objects.entities.User;
import utils.StringGeneratorUtils;

import static data.TestData.PASSWORD;
import static data.TestData.USERNAME;
import static utils.StringGeneratorUtils.getRandomPassword;

public class UserFactory {
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
        return new UserBuilder().setEmail(USERNAME).setPassword(PASSWORD).make();
    }
}
