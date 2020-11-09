package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage {
    protected static final int WAIT_ELEMENT_TIMEOUT = 10;
    protected WebDriver driver;
    @FindBy(className = "login")
    protected WebElement signinNavigationButton;

    @FindBy(className = "logout")
    protected WebElement signOutNavigationButton;

    @FindBy(className = "account")
    protected WebElement accountNavigationButton;

    @FindBy(className = "alert")
    protected WebElement alertBlock;

    //    @FindBy(xpath = "//*[@title = \"Proceed to checkout\"]")
    @FindBy(className = "cross")
    protected WebElement closeButton;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitForElementPresent(WebElement element) {
        new WebDriverWait(driver, WAIT_ELEMENT_TIMEOUT).until(ExpectedConditions.visibilityOf(element));
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public SignInPage navigateToSignInPage() {
        signinNavigationButton.click();
        return new SignInPage(driver);
    }

    public BasePage clickOnSignOut() {
        signOutNavigationButton.click();
        return this;
    }

    public BasePage closePopup() {
        waitForElementPresent(closeButton);
        closeButton.click();
        return this;
    }

    public boolean signOutButtonIsVisible() {
        return isVisible(signOutNavigationButton);
    }

    public boolean signInButtonIsVisible() {
        return isVisible(signinNavigationButton);
    }

    public boolean accountButtonIsVisible() {
        return isVisible(accountNavigationButton);
    }

    public boolean errorBlockIsVisible() {
        return isVisible(alertBlock);
    }

    protected boolean isVisible(WebElement element) {
        try {
            waitForElementPresent(element);
            return element.isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }


    public boolean compareErrorMessage(String errorMessage) {
        try {
            waitForElementPresent(alertBlock);
            return alertBlock.getText().contains(errorMessage);
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    protected WebElement getVisibleElement(List<WebElement> elements) {
        for (WebElement element : elements) {
            if (element.isDisplayed()) {
                return element;
            }
        }
        return null;
    }
}

