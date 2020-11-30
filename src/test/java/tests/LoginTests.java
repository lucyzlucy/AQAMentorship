package tests;

import business_objects.entities.User;
import data.TestData;
import data.dataProvider.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.CataloguePage;
import pages.SignInPage;
import utils.CustomAssert;

import static environment.Environment.getEnvProperty;
import static navigationUtil.PageNavigationUtil.toMainPage;
import static navigationUtil.PageNavigationUtil.toSignInPage;

public class LoginTests extends BaseTest {

    @Test
    public void verifyCanNavigateToSignInPageFromMain() {
        SignInPage page = toMainPage()
                .navigateToSignInPage();

        CustomAssert custAssert = new CustomAssert();
        custAssert.assertTrue(page.getUrl().contains(getEnvProperty("signInPageUrl")), "SIGNIN_PAGE_URL is opened"+page.getUrl());
        custAssert.assertAll();
    }

    @Test
    public void verifyCanSignInWithValidCredentials() {
        User user = TestData.getExistingUser();

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

    @Test(dataProviderClass = TestDataProvider.class, dataProvider = "invalidEmails")
    public void verifyCannotLoginWithInvalidEmail(User invalidEmailUser) {

        SignInPage page =  toSignInPage()
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
        User user = TestData.getExistingUser();

        BasePage page = toSignInPage()
                .submitCredentials(user)
                .clickOnSignOut();

        Assert.assertTrue(page.unloggedUserHeaderIsShown(), "No account and signout buttons are shown");

    }
}
