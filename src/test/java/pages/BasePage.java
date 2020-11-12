package pages;

import driver.DriverWrapper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

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

    public BasePage() {
        PageFactory.initElements(DriverWrapper.getDriver(), this);
    }

    protected void waitForPageToLoad(WebElement keyElement) {
        DriverWrapper.isElementLoaded(keyElement);
    }

    public String getUrl() {
        return DriverWrapper.getDriver().getCurrentUrl();
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
//        }
    }

    protected WebElement getVisibleElement(List<WebElement> elements) {
        for (WebElement element : elements) {
            if (element.isDisplayed()) {
                return element;
            }
        }
        return null;
    }

    public boolean loggedUserHeaderIsShown() {
        return isElementLoaded(accountNavigationButton) & isElementLoaded(signOutNavigationButton);
    }

    public boolean unloggedUserHeaderIsShown() {
        return isElementLoaded(signinNavigationButton);
    }

}

