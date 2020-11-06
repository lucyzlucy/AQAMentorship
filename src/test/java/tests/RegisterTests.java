package tests;

import business_objects.entities.User;
import business_objects.factory.UserFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.RegisterPage;
import pages.SignInPage;

import static data.TestData.*;

public class RegisterTests extends BaseTest {

    @Test
    public void verifyCanRegister() {
        User user = UserFactory.getNewUser();

        RegisterPage page = new SignInPage(driver)
                .submitEmailForRegistration(user.getEmail())
                .submitValidRegistrationInfo(user);

        Assert.assertTrue(page.getUrl().contains(MY_ACCOUNT_PAGE));
    }

    @Test
    public void verifyCannotRegisterWithEmptyEmail() {
        SignInPage page = new SignInPage(driver)
                .submitEmptyEmailForRegistration();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(page.getUrl(), SIGNIN_PAGE_URL, "The user stays on login page");
        softAssert.assertTrue(page.errorBlockIsVisible(), "An error message is shown");
        softAssert.assertTrue(page.compareErrorMessage(ERROR_INVALID_EMAIL), "The message about empty email is correct");
        softAssert.assertAll();
    }

    @Test
    public void verifyCannotRegisterWithExistingEmail() {
        User user = UserFactory.getExistingUser();

        SignInPage page = new SignInPage(driver)
                .submitExistingEmailForRegistration(user.getEmail());

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(page.getUrl(), SIGNIN_PAGE_URL, "The user stays on login page");
        softAssert.assertTrue(page.errorBlockIsVisible(), "An error message is shown");
        softAssert.assertTrue(page.compareErrorMessage(ERROR_EXISTING_EMAIL), "The message about empty email is correct");
        softAssert.assertAll();
    }

    @Test
    public void verifyCannotRegisterWithEmptyInfo() {
        User user = UserFactory.getNewUser();

        RegisterPage page = new SignInPage(driver)
                .submitEmailForRegistration(user.getEmail())
                .submitEmptyRegistrationInfo();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(page.getUrl(), SIGNIN_PAGE_URL, "The user stays on login page");
        softAssert.assertTrue(page.errorBlockIsVisible(), "An error message is shown");
        softAssert.assertAll();
    }
}
