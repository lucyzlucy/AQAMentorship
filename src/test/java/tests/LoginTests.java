package tests;

import business_objects.entities.User;
import business_objects.factory.UserFactory;
import dataProvider.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.CataloguePage;
import pages.SignInPage;
import utils.CustomAssert;

import static environment.Environment.getEnvProperty;

public class LoginTests extends BaseTest {

    @Test
    public void verifyCanNavigateToSignInPageFromMain() {
        SignInPage page = new CataloguePage()
                .navigateToSignInPage();

        CustomAssert custAssert = new CustomAssert();
        custAssert.assertTrue(page.getUrl().contains(getEnvProperty("signInPageUrl")), "SIGNIN_PAGE_URL is opened");
        custAssert.assertAll();
    }

    @Test
    public void verifyCanSignInWithValidCredentials() {
        User user = UserFactory.getExistingUser();

        SignInPage page = new SignInPage()
                .submitCredentials(user);

        CustomAssert custAssert = new CustomAssert();
        custAssert.assertEquals(page.getUrl(), getEnvProperty("myAccountInPageUrl"), "The user is redirected to account page");
        custAssert.assertTrue(page.loggedUserHeaderIsShown(), "Account and signout buttons are shown");
        custAssert.assertAll();
    }

    @Test
    public void verifyCannotSubmitEmptyFields() {
        SignInPage page = new SignInPage()
                .submitEmptyCredentials();

        CustomAssert customAssert = new CustomAssert();
        customAssert.assertEquals(page.getUrl(), getEnvProperty("signInPageUrl"), "The user stays on login page");
        customAssert.assertTrue(page.unloggedUserHeaderIsShown(), "No account and signout buttons are shown");
        customAssert.assertTrue(page.errorBlockIsVisible(), "An error message is shown");
        customAssert.assertTrue(page.getErrorMessage().contains(getEnvProperty("errorEmptyEmail")), "The message about empty email is correct");
        customAssert.assertAll();
    }

    @Test(dataProviderClass = TestDataProvider.class, dataProvider = "invalidEmails")
    public void verifyCannotLoginWithInvalidEmail(User invalidEmailUser) {

        SignInPage page = new SignInPage()
                .submitCredentials(invalidEmailUser);

        CustomAssert customAssert = new CustomAssert();
        customAssert.assertEquals(page.getUrl(), getEnvProperty("signInPageUrl"), "The user stays on login page");
        customAssert.assertTrue(page.unloggedUserHeaderIsShown(), "No account and signout buttons are shown");
        customAssert.assertTrue(page.errorBlockIsVisible(), "An error message is shown");
        customAssert.assertTrue(page.getErrorMessage().contains(getEnvProperty("errorInvalidEmail")), "The message about invalid email is correct");
        customAssert.assertAll();
    }

    @Test(dataProviderClass = TestDataProvider.class, dataProvider = "invalidPasswords")
    public void verifyCannotLoginWithInvalidPassword(User invalidPassUser) {
        SignInPage page = new SignInPage()
                .submitCredentials(invalidPassUser);

        CustomAssert customAssert = new CustomAssert();
        customAssert.assertEquals(page.getUrl(), getEnvProperty("signInPageUrl"), "The user stays on login page");
        customAssert.assertTrue(page.unloggedUserHeaderIsShown(), "No account and signout buttons are shown");
        customAssert.assertTrue(page.errorBlockIsVisible(), "An error message is shown");
        customAssert.assertTrue(page.getErrorMessage().contains(getEnvProperty("errorInvalidPass")), "The message about invalid password is correct");
        customAssert.assertAll();
    }

    @Test
    public void verifyCanLogout() {
        User user = UserFactory.getExistingUser();

        BasePage page = new SignInPage()
                .submitCredentials(user)
                .clickOnSignOut();

        Assert.assertTrue(page.unloggedUserHeaderIsShown(), "No account and signout buttons are shown");

    }
}
