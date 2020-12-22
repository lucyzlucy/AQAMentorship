package tests;

import business_objects.entities.User;
import data.UserFactory;
import data.dataProvider.UserDataProvider;
import data.dataProvider.UserDataProvider2;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.RegisterPage;
import pages.SignInPage;
import utils.CustomAssert;

import static data.UserFactory.getUserWithGivenEmail;
import static environment.Environment.getEnvProperty;
import static navigationUtil.PageNavigationUtil.toSignInPage;

public class RegisterTests extends BaseTest {

    @Test
    public void verifyCanRegister() {
        User user = UserFactory.getNewUser();

        RegisterPage page = toSignInPage()
                .submitEmailForRegistration(user.getEmail())
                .submitValidRegistrationInfo(user);

        CustomAssert customAssert = new CustomAssert();
        customAssert.assertTrue(page.getUrl().contains(getEnvProperty("myAccountInPageUrl")));
        customAssert.assertTrue(page.unloggedUserHeaderIsShown(), "Account and signout buttons are shown - the user is logged");
        customAssert.assertAll();
    }

    @Test
    public void verifyCannotRegisterWithEmptyEmail() {
        SignInPage page = toSignInPage()
                .submitEmptyEmailForRegistration();

        CustomAssert customAssert = new CustomAssert();
        customAssert.assertEquals(page.getUrl(), getEnvProperty("signInPageUrl"), "The user stays on login page");
        customAssert.assertTrue(page.unloggedUserHeaderIsShown(), "No account and signout buttons are shown - the user is unlogged");
        customAssert.assertTrue(page.errorBlockIsVisible(), "An error message is shown");
        customAssert.assertTrue(page.getErrorMessage().contains(getEnvProperty("errorInvalidEmail")), "The message about empty email is correct");
        customAssert.assertAll();

    }

    @Test
    public void verifyCannotRegisterWithExistingEmail() {
        User user = UserFactory.getExistingUser();

        SignInPage page = toSignInPage()
                .submitExistingEmailForRegistration(user.getEmail());

        CustomAssert customAssert = new CustomAssert();
        customAssert.assertEquals(page.getUrl(), getEnvProperty("signInPageUrl"), "The user stays on login page");
        customAssert.assertTrue(page.unloggedUserHeaderIsShown(), "No account and signout buttons are shown - the user is unlogged");
        customAssert.assertTrue(page.errorBlockIsVisible(), "An error message is shown");
        customAssert.assertTrue(page.getErrorMessage().contains(getEnvProperty("errorExistingEmail")), "The message about empty email is correct");
        customAssert.assertAll();
    }

    @Test
    public void verifyCannotRegisterWithEmptyInfo() {
        User user = UserFactory.getNewUser();

        RegisterPage page = toSignInPage()
                .submitEmailForRegistration(user.getEmail())
                .submitEmptyRegistrationInfo();

        CustomAssert customAssert = new CustomAssert();
        customAssert.assertEquals(page.getUrl(), getEnvProperty("signInPageUrl"), "The user stays on login page");
        customAssert.assertTrue(page.unloggedUserHeaderIsShown(), "No account and signout buttons are shown - the user is unlogged");
        customAssert.assertTrue(page.errorBlockIsVisible(), "An error message is shown");
        customAssert.assertAll();

    }

    @Test(dataProviderClass = UserDataProvider.class, dataProvider = "userData", description = "invalidEmail")
    public void verifyCannotRegisterWithInvalidEmail(String invalidEmail) {
        User invalidEmailUser = getUserWithGivenEmail(invalidEmail);

        SignInPage page = toSignInPage()
                .submitCredentials(invalidEmailUser);

        CustomAssert customAssert = new CustomAssert();
        customAssert.assertEquals(page.getUrl(), getEnvProperty("signInPageUrl"), "The user stays on login page");
        customAssert.assertTrue(page.unloggedUserHeaderIsShown(), "No account and signout buttons are shown");
        customAssert.assertTrue(page.errorBlockIsVisible(), "An error message is shown");
        customAssert.assertTrue(page.getErrorMessage().contains(getEnvProperty("errorInvalidEmail")), "The message about invalid email is correct");
        customAssert.assertAll();
    }
}
