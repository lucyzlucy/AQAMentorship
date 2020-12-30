package pages;

import business_objects.entities.User;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Log4j2
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

    public RegisterPage(){
        waitForPageToLoad(registerButton);
    }
    @Step

    public RegisterPage submitValidRegistrationInfo(User user) {

        firstNameInput.sendKeys(user.getFirstName());
        lastNameInput.sendKeys(user.getLastName());
        passwordInput.sendKeys(user.getPassword());
        addressInput.sendKeys(user.getAddress());
        cityInput.sendKeys(user.getCity());
        stateInput.sendKeys(user.getState());
        zipInput.sendKeys(user.getZip());
        countryInput.sendKeys(user.getCountry());
        phoneInput.sendKeys(user.getPhone());

        log.info("Filled in user info: " + user.toString());

        registerButton.click();

        log.info("Submitted user info by clicking on registerButton");

        return this;
    }
    @Step

    public RegisterPage submitEmptyRegistrationInfo() {
        registerButton.click();

        log.info("Submitted empty user info by clicking on registerButton");

        return this;
    }
}
