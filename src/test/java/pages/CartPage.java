package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class CartPage extends BasePage {
    @FindBy(className = "cart_quantity_delete")
    protected WebElement deleteButton;

    @FindBy(xpath = "//span[@class=\"price\"]/span")
    protected WebElement priceInCart;

    @FindBy(xpath = "//p[@class=\"product-name\"][0]")
    protected WebElement nameInCart;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getCartProductName() {
        List<WebElement> list = driver.findElements(By.xpath("//p[@class=\"product-name\"]"));
        waitForElementPresent(getVisibleElement(list));
        return getVisibleElement(list).getText();
    }

    public String getCartProductPrice() {
        waitForElementPresent(priceInCart);
        return priceInCart.getText();
    }

    public CartPage deleteProduct() {
        waitForElementPresent(deleteButton);
        deleteButton.click();
        return this;
    }

}
