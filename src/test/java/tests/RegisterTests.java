package tests;

import business_objects.entities.User;
import business_objects.factory.UserFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.RegisterPage;
import pages.SignInPage;

import static data.TestData.*;
import static environment.Environment.getEnvProperty;

public class RegisterTests extends BaseTest {

    @Test
    public void verifyCanRegister() {
        User user = UserFactory.getNewUser();

        RegisterPage page = new SignInPage()
                .submitEmailForRegistration(user.getEmail())
                .submitValidRegistrationInfo(user);

        Assert.assertTrue(page.getUrl().contains(MY_ACCOUNT_PAGE));
    }

    @Test
    public void verifyCannotRegisterWithEmptyEmail() {
        SignInPage page = new SignInPage()
                .submitEmptyEmailForRegistration();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(page.getUrl(), SIGNIN_PAGE_URL, "The user stays on login page");
        softAssert.assertTrue(page.errorBlockIsVisible(), "An error message is shown");
        softAssert.assertTrue(page.compareErrorMessage(getEnvProperty("errorInvalidEmail")), "The message about empty email is correct");
        softAssert.assertAll();
    }

    @Test
    public void verifyCannotRegisterWithExistingEmail() {
        User user = UserFactory.getExistingUser();

        SignInPage page = new SignInPage()
                .submitExistingEmailForRegistration(user.getEmail());

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(page.getUrl(), SIGNIN_PAGE_URL, "The user stays on login page");
        softAssert.assertTrue(page.errorBlockIsVisible(), "An error message is shown");
        softAssert.assertTrue(page.compareErrorMessage(getEnvProperty("errorExistingEmail")), "The message about empty email is correct");
        softAssert.assertAll();
    }

    @Test
    public void verifyCannotRegisterWithEmptyInfo() {
        User user = UserFactory.getNewUser();

        RegisterPage page = new SignInPage()
                .submitEmailForRegistration(user.getEmail())
                .submitEmptyRegistrationInfo();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(page.getUrl(), SIGNIN_PAGE_URL, "The user stays on login page");
        softAssert.assertTrue(page.errorBlockIsVisible(), "An error message is shown");
        softAssert.assertAll();
    }
}
