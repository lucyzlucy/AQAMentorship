package pages;

import driver.DriverWrapper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;


import static driver.DriverWrapper.getCurrentUrl;
import static driver.DriverWrapper.isElementLoaded;

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
    }

    protected void waitForPageToLoad(WebElement keyElement) {
        isElementLoaded(keyElement);
    }

    public String getUrl() {
        return getCurrentUrl();
    }

    public SignInPage navigateToSignInPage() {
        signinNavigationButton.click();
        return new SignInPage();
    }

    public BasePage clickOnSignOut() {
        signOutNavigationButton.click();
        return this;
    }

    public BasePage closePopup() {
        isElementLoaded(closeButton);
        closeButton.click();
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
        return new FullCartPage();
    }


}

