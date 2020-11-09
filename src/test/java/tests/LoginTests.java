package tests;

import business_objects.entities.User;
import business_objects.factory.UserFactory;
import dataProvider.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.BasePage;
import pages.CataloguePage;
import pages.SignInPage;
import utils.StringGeneratorUtils;

import static data.TestData.*;

public class LoginTests extends BaseTest {

    @Test
    public void verifyCanNavigateToSignInPageFromMain() {
        SignInPage page = new CataloguePage(driver)
                .navigateToSignInPage();

        Assert.assertTrue(page.getUrl().contains(SIGNIN_PAGE_URL));
    }

    @Test
    public void verifyCanSignInWithValidCredentials() {
        User user = UserFactory.getExistingUser();

        SignInPage page = new SignInPage(driver)
                .submitCredentials(user.getEmail(), user.getPassword());

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(page.getUrl(), MY_ACCOUNT_PAGE, "The user is redirected to account page");
        softAssert.assertTrue(page.signOutButtonIsVisible(), "The logout link is shown");
        softAssert.assertTrue(page.accountButtonIsVisible(), "The account link is shown");
        softAssert.assertAll();
    }

    @Test
    public void verifyCannotSubmitEmptyFields() {
        SignInPage page = new SignInPage(driver)
                .submitEmptyCredentials();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(page.getUrl(), SIGNIN_PAGE_URL, "The user stays on login page");
        softAssert.assertTrue(page.errorBlockIsVisible(), "An error message is shown");
        softAssert.assertTrue(page.compareErrorMessage(ERROR_EMPTY_EMAIL), "The message about empty email is correct");
        softAssert.assertAll();
    }

    @Test(dataProviderClass = TestDataProvider.class, dataProvider = "invalidEmails")
    public void verifyCannotLoginWithInvalidEmail(String invalidEmail) {
        SignInPage page = new SignInPage(driver)
                .submitCredentials(invalidEmail, StringGeneratorUtils.getRandomPassword());

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(page.getUrl(), SIGNIN_PAGE_URL, "The user stays on login page");
        softAssert.assertTrue(page.errorBlockIsVisible(), "An error message is shown");
        softAssert.assertTrue(page.compareErrorMessage(ERROR_INVALID_EMAIL), "The message about invalid email is correct");
        softAssert.assertAll();

    }

    @Test(dataProviderClass = TestDataProvider.class, dataProvider = "invalidPasswords")
    public void verifyCannotLoginWithInvalidPassword(String invalidPassword) {
        SignInPage page = new SignInPage(driver)
                .submitCredentials(StringGeneratorUtils.getRandomEmail(), invalidPassword);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(page.getUrl(), SIGNIN_PAGE_URL, "The user stays on login page");
        softAssert.assertTrue(page.errorBlockIsVisible(), "An error message is shown");
        softAssert.assertTrue(page.compareErrorMessage(ERROR_INVALID_PASS), "The message about invalid password is correct");
        softAssert.assertAll();
    }

    @Test
    public void verifyCanLogout() {
        User user = UserFactory.getExistingUser();

        BasePage page = new SignInPage(driver)
                .submitCredentials(user.getEmail(), user.getPassword())
                .clickOnSignOut();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(page.getUrl().contains(SIGNIN_PAGE_URL), "The user stays on login page");
        softAssert.assertTrue(page.signInButtonIsVisible(), "The login link is shown");
        softAssert.assertFalse(page.accountButtonIsVisible(), "No account link is shown");
        softAssert.assertAll();
    }
}
