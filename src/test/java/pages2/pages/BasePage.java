package pages2.pages;

import driver.DriverWrapper;
import driver.DriverWrapperThreadSafe;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static driver.DriverWrapperThreadSafe.getCurrentUrl;
import static driver.DriverWrapperThreadSafe.isElementLoaded;

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
        DriverWrapperThreadSafe.initElements(this);
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
        DriverWrapperThreadSafe.refresh();
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

