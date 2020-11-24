package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static data.TestData.SIGNIN_PAGE_URL;
import static driver.DriverWrapper.navigateToPage;

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
        navigateToPage(SIGNIN_PAGE_URL);
        waitForPageToLoad(submitSignInButton);
    }

    public SignInPage submitCredentials(String email, String password) {
        emailInput.sendKeys(email);
        passInput.sendKeys(password);
        submitSignInButton.click();
        return this;
    }

    public SignInPage submitEmptyCredentials() {
        submitSignInButton.click();
        return this;
    }

    public RegisterPage submitEmailForRegistration(String email) {
        emailRegisterInput.sendKeys(email);
        submitCreateButton.click();
        return new RegisterPage();
    }

    public SignInPage submitEmptyEmailForRegistration() {
        submitCreateButton.click();
        return this;
    }

    public SignInPage submitExistingEmailForRegistration(String email) {
        emailRegisterInput.sendKeys(email);
        submitCreateButton.click();
        return this;
    }

}
