package pages;

import business_objects.entities.User;
import environment.Environment;
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

    @FindBy(id = "SubmitLogin")
    private WebElement submitSignInButton;

    @FindBy(id = "email_create")
    private WebElement emailRegisterInput;

    @FindBy(id = "SubmitCreate")
    private WebElement submitCreateButton;

    public SignInPage() {
        waitForPageToLoad(submitSignInButton);
    }

    public SignInPage submitCredentials(User user) {
        emailInput.sendKeys(user.getEmail());
        passInput.sendKeys(user.getPassword());

        log.info("Filled in user info: " + user.toString());

        submitSignInButton.click();

        log.info("Submitted user info by clicking on submitSignInButton");

        return this;
    }

    public SignInPage submitEmptyCredentials() {
        submitSignInButton.click();

        log.info("Submitted empty user info by clicking on registerButton");

        return this;
    }

    public RegisterPage submitEmailForRegistration(String email) {
        emailRegisterInput.sendKeys(email);

        log.info("Filled in user email: " + email);

        submitCreateButton.click();

        log.info("Submitted user email by clicking on submitCreateButton");

        return new RegisterPage();
    }

    public SignInPage submitEmptyEmailForRegistration() {
        submitCreateButton.click();

        log.info("Submitted empty user email by clicking on submitCreateButton");

        return this;
    }

    public SignInPage submitExistingEmailForRegistration(String email) {
        emailRegisterInput.sendKeys(email);

        log.info("Filled in user email: " + email);

        submitCreateButton.click();

        log.info("Submitted existing user email by clicking on submitCreateButton");

        return this;
    }

}
