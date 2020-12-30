package pages;

import driver.DriverWrapper;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import static driver.DriverWrapper.getCurrentUrl;
import static driver.DriverWrapper.isElementLoaded;

@Log4j2
public class BasePage {
    @FindBy(className = "login")
    protected WebElement signinNavigationButton;

    @FindBy(className = "logout")
    protected WebElement signOutNavigationButton;

    @FindBy(className = "account")
    protected WebElement accountNavigationButton;

    @FindBy(className = "alert")
    protected WebElement alertBlock;

    @FindBy(className = "cross")
    protected WebElement closeButton;

    @FindBy(xpath = "//div[@class='shopping_cart']/a")
    protected WebElement cartButton;

    public BasePage() {
        DriverWrapper.initElements(this);
        log.info(this.getClass().getSimpleName() + " is opened");
    }

    protected void waitForPageToLoad(WebElement keyElement) {
        isElementLoaded(keyElement);
    }

    public String getUrl() {
        return getCurrentUrl();
    }

    @Step
    public SignInPage navigateToSignInPage() {
        signinNavigationButton.click();

        log.info("Clicked on signinNavigationButton");

        return new SignInPage();
    }

    @Step
    public BasePage clickOnSignOut() {
        signOutNavigationButton.click();

        log.info("Signed out by clicking on signOutNavigationButton");

        return this;
    }

    @Step
    public BasePage closePopup() {
        isElementLoaded(closeButton);
        closeButton.click();

        log.info("Closed the popup by clicking on closeButton");

        return this;
    }

    public boolean errorBlockIsVisible() {
        return isElementLoaded(alertBlock);
    }

    public String getErrorMessage() {
        return alertBlock.getText();
    }

    public void refreshPage() {
        DriverWrapper.refresh();
    }

    public boolean loggedUserHeaderIsShown() {
        return isElementLoaded(accountNavigationButton) & isElementLoaded(signOutNavigationButton);
    }

    public boolean unloggedUserHeaderIsShown() {
        return isElementLoaded(signinNavigationButton);
    }

    public FullCartPage clickOnCart() {
        cartButton.click();

        log.info("Navigated to cart by clicking on cartButton on the header");

        return new FullCartPage();
    }


}

