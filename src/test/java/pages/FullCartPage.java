package pages;

import driver.DriverWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FullCartPage extends CartPage {
    @FindBy(className = "cart_quantity_delete")
    protected WebElement deleteButton;

    @FindBy(xpath = "//span[@class=\"price\"]/span")
    protected WebElement priceInCart;

    @FindBy(xpath = "//p[@class=\"product-name\"][0]")
    protected WebElement nameInCart;

    public FullCartPage() {
        waitForPageToLoad(deleteButton);
    }

    public String getCartProductName() {
        List<WebElement> list = DriverWrapper.getDriver().findElements(By.xpath("//p[@class=\"product-name\"]"));
//        waitForElementPresent(getVisibleElement(list));
        return getVisibleElement(list).getText();
    }

    public String getCartProductPrice() {
//        waitForElementPresent(priceInCart);
        return priceInCart.getText();
    }

    public EmptyCartPage deleteProduct() {
//        waitForElementPresent(deleteButton);
        deleteButton.click();
        return new EmptyCartPage();
    }
}
