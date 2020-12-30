package tests;

import business_objects.entities.User;
import data.UserFactory;
import data.dataProvider.UserDataProvider;
import data.dataProvider.UserDataProvider3;
import org.testng.Assert;

import org.testng.annotations.Test;
import pages.BasePage;
import pages.SignInPage;
import utils.CustomAssert;

import static data.UserFactory.getUserWithGivenEmail;
import static data.UserFactory.getUserWithGivenPass;
import static environment.Environment.getEnvProperty;
import static navigationUtil.PageNavigationUtil.toMainPage;
import static navigationUtil.PageNavigationUtil.toSignInPage;

public class LoginTests extends BaseTest {

    @Test
    public void verifyCanNavigateToSignInPageFromMain() {
        SignInPage page = toMainPage()
                .navigateToSignInPage();

        CustomAssert custAssert = new CustomAssert();
        custAssert.assertTrue(page.getUrl().contains(getEnvProperty("signInPageUrl")), "SIGNIN_PAGE_URL is opened" + page.getUrl());
        custAssert.assertAll();
    }

    @Test
    public void verifyCanSignInWithValidCredentials() {
        User user = UserFactory.getExistingUser();

        SignInPage page = toSignInPage()
                .submitCredentials(user);

        CustomAssert custAssert = new CustomAssert();
        custAssert.assertEquals(page.getUrl(), getEnvProperty("myAccountInPageUrl"), "The user is redirected to account page");
        custAssert.assertTrue(page.loggedUserHeaderIsShown(), "Account and signout buttons are shown");
        custAssert.assertAll();
    }

    @Test
    public void verifyCannotSubmitEmptyFields() {
        SignInPage page = toSignInPage()
                .submitEmptyCredentials();

        CustomAssert customAssert = new CustomAssert();
        customAssert.assertEquals(page.getUrl(), getEnvProperty("signInPageUrl"), "The user stays on login page");
        customAssert.assertTrue(page.unloggedUserHeaderIsShown(), "No account and signout buttons are shown");
        customAssert.assertTrue(page.errorBlockIsVisible(), "An error message is shown");
        customAssert.assertTrue(page.getErrorMessage().contains(getEnvProperty("errorEmptyEmail")), "The message about empty email is correct");
        customAssert.assertAll();
    }

    @Test(dataProviderClass = UserDataProvider3.class, dataProvider = "invalidEmails", testName = "invalidEmail")
    public void verifyCannotLoginWithInvalidEmail(String invalidEmail) {
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

    @Test(dataProviderClass = UserDataProvider.class, dataProvider = "userData", description = "invalidPassword")
    public void verifyCannotLoginWithInvalidPassword(String invalidPass) {
        User invalidPassUser = getUserWithGivenPass(invalidPass);

        SignInPage page = toSignInPage()
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

        BasePage page = toSignInPage()
                .submitCredentials(user)
                .clickOnSignOut();

        Assert.assertTrue(page.unloggedUserHeaderIsShown(), "No account and signout buttons are shown");

    }
}
