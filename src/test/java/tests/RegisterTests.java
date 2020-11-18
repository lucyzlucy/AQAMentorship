package tests;

import business_objects.entities.User;
import business_objects.factory.UserFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegisterPage;
import pages.SignInPage;
import utils.CustomAssert;

import static data.TestData.*;

public class RegisterTests extends BaseTest {

    @Test
    public void verifyCanRegister() {
        User user = UserFactory.getNewUser();

        RegisterPage page = new SignInPage()
                .submitEmailForRegistration(user.getEmail())
                .submitValidRegistrationInfo(user);

        CustomAssert customAssert = new CustomAssert();
        customAssert.assertTrue(page.getUrl().contains(MY_ACCOUNT_PAGE));
        customAssert.assertTrue(page.unloggedUserHeaderIsShown(), "Account and signout buttons are shown - the user is logged");
        customAssert.assertAll();
    }

    @Test
    public void verifyCannotRegisterWithEmptyEmail() {
        SignInPage page = new SignInPage()
                .submitEmptyEmailForRegistration();

        CustomAssert customAssert = new CustomAssert();
        customAssert.assertEquals(page.getUrl(), SIGNIN_PAGE_URL, "The user stays on login page");
        customAssert.assertTrue(page.unloggedUserHeaderIsShown(), "No account and signout buttons are shown - the user is unlogged");
        customAssert.assertTrue(page.errorBlockIsVisible(), "An error message is shown");
        customAssert.assertTrue(page.getErrorMessage().contains(environment.getEnvProperty("errorInvalidEmail")), "The message about empty email is correct");
        customAssert.assertAll();

    }

    @Test
    public void verifyCannotRegisterWithExistingEmail() {
        User user = UserFactory.getExistingUser();

        SignInPage page = new SignInPage()
                .submitExistingEmailForRegistration(user.getEmail());

        CustomAssert customAssert = new CustomAssert();
        customAssert.assertEquals(page.getUrl(), SIGNIN_PAGE_URL, "The user stays on login page");
        customAssert.assertTrue(page.unloggedUserHeaderIsShown(), "No account and signout buttons are shown - the user is unlogged");
        customAssert.assertTrue(page.errorBlockIsVisible(), "An error message is shown");
        customAssert.assertTrue(page.getErrorMessage().contains(environment.getEnvProperty("errorExistingEmail")), "The message about empty email is correct");
        customAssert.assertAll();
    }

    @Test
    public void verifyCannotRegisterWithEmptyInfo() {
        User user = UserFactory.getNewUser();

        RegisterPage page = new SignInPage()
                .submitEmailForRegistration(user.getEmail())
                .submitEmptyRegistrationInfo();

        CustomAssert customAssert = new CustomAssert();
        customAssert.assertEquals(page.getUrl(), SIGNIN_PAGE_URL, "The user stays on login page");
        customAssert.assertTrue(page.unloggedUserHeaderIsShown(), "No account and signout buttons are shown - the user is unlogged");
        customAssert.assertTrue(page.errorBlockIsVisible(), "An error message is shown");
        customAssert.assertAll();

    }
}
