package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected static final int WAIT_ELEMENT_TIMEOUT = 5;
    protected WebDriver driver;
    @FindBy(className = "login")
    protected WebElement signinNavigationButton;

    @FindBy(className = "logout")
    protected WebElement signOutNavigationButton;

    @FindBy(className = "account")
    protected WebElement accountNavigationButton;

    @FindBy(className = "alert")
    protected WebElement alertBlock;


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
        return new BasePage(driver);
    }

    public boolean signOutButtonIsVisible() {
        try {
            return signOutNavigationButton.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean signInButtonIsVisible() {
        try {
            return signinNavigationButton.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean accountButtonIsVisible() {
        try {
            return accountNavigationButton.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean errorBlockIsVisible() {
        try {
            waitForElementPresent(alertBlock);
            return alertBlock.isDisplayed();
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
}

