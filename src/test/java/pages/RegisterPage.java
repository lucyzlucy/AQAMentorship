package pages;

import business_objects.entities.User;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage extends BasePage {
    @FindBy(id = "customer_firstname")
    private WebElement firstNameInput;

    @FindBy(id = "customer_lastname")
    private WebElement lastNameInput;

    @FindBy(id = "passwd")
    private WebElement passwordInput;

    @FindBy(id = "address1")
    private WebElement addressInput;

    @FindBy(id = "city")
    private WebElement cityInput;

    @FindBy(id = "id_state")
    private WebElement stateInput;

    @FindBy(id = "postcode")
    private WebElement zipInput;

    @FindBy(id = "id_country")
    private WebElement countryInput;

    @FindBy(id = "phone_mobile")
    private WebElement phoneInput;

    @FindBy(id = "submitAccount")
    private WebElement registerButton;

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public RegisterPage submitValidRegistrationInfo(User user) {
        waitForElementPresent(firstNameInput);

        firstNameInput.sendKeys(user.getFirstName());
        lastNameInput.sendKeys(user.getLastName());
        passwordInput.sendKeys(user.getPassword());
        addressInput.sendKeys(user.getAddress());
        cityInput.sendKeys(user.getCity());
        stateInput.sendKeys(user.getState());
        zipInput.sendKeys(user.getZip());
        countryInput.sendKeys(user.getCountry());
        phoneInput.sendKeys(user.getPhone());
        registerButton.click();
        return this;
    }

    public RegisterPage submitEmptyRegistrationInfo() {
        waitForElementPresent(firstNameInput);

        registerButton.click();
        return this;
    }
}
