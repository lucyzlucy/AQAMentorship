package pages;

import business_objects.entities.User;
import driver.DriverWrapper;
import environment.Environment;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static driver.DriverWrapper.navigateToPage;

@Log4j2
public class SignInPage extends BasePage {
    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "passwd")
    private WebElement passInput;

    @FindBy(id = "SubmitLoging")
    private WebElement submitSignInButton;

    @FindBy(id = "email_create")
    private WebElement emailRegisterInput;

    @FindBy(id = "SubmitCreate")
    private WebElement submitCreateButton;

    public SignInPage() {
    }

    @Override
    protected void isLoaded() throws Error {
        waitForPageToLoad(submitSignInButton);
    }

    @Step
    public SignInPage submitCredentials(User user) {
        emailInput.sendKeys(user.getEmail());
        passInput.sendKeys(user.getPassword());

        log.info("Filled in user info: " + user.toString());

        submitSignInButton.click();

        log.info("Submitted user info by clicking on submitSignInButton");

        return this;
    }

    @Step

    public SignInPage submitEmptyCredentials() {
        submitSignInButton.click();

        log.info("Submitted empty user info by clicking on registerButton");

        return this;
    }

    @Step

    public RegisterPage submitEmailForRegistration(String email) {
        emailRegisterInput.sendKeys(email);

        log.info("Filled in user email: " + email);

        submitCreateButton.click();

        log.info("Submitted user email by clicking on submitCreateButton");

        return new RegisterPage();
    }

    @Step

    public SignInPage submitEmptyEmailForRegistration() {
        submitCreateButton.click();

        log.info("Submitted empty user email by clicking on submitCreateButton");

        return this;
    }

    @Step

    public SignInPage submitExistingEmailForRegistration(String email) {
        emailRegisterInput.sendKeys(email);

        log.info("Filled in user email: " + email);

        submitCreateButton.click();

        log.info("Submitted existing user email by clicking on submitCreateButton");

        return this;
    }

}
