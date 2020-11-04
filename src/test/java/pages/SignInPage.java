package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static data.TestData.SIGNIN_PAGE_URL;

public class SignInPage extends BasePage {
    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "passwd")
    private WebElement passInput;

    @FindBy(id = "SubmitLogin")
    private WebElement submitButton;

    public SignInPage(WebDriver driver) {
        super(driver);
        driver.get(SIGNIN_PAGE_URL);
    }

    public SignInPage submitCredentials(String email, String password){
        emailInput.sendKeys(email);
        passInput.sendKeys(password);
        submitButton.click();
        return this;
    }

    public SignInPage submitCredentials(){
        submitButton.click();
        return this;
    }


}
