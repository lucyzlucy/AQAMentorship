package pages;

import org.openqa.selenium.WebDriver;

import static data.TestData.HOME_PAGE_URL;

public class HomePage extends BasePage {


    public HomePage(WebDriver driver) {
        super(driver);
        driver.get(HOME_PAGE_URL);
    }

}
