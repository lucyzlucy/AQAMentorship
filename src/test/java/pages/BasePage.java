package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
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
            return alertBlock.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public String getErrorMessage() {
            return alertBlock.getText();
    }
}

