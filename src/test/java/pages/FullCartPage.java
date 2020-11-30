package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class FullCartPage extends BasePage {
    @FindBy(className = "cart_quantity_delete")
    protected WebElement deleteButton;

    @FindBy(xpath = "//span[@class=\"price\"]/span")
    protected WebElement priceInCart;

    @FindBy(xpath = "//tr[1]//p[@class=\"product-name\"]")
    protected WebElement nameInCart;

    public FullCartPage() {
        waitForPageToLoad(deleteButton);
    }

    public String getCartProductName() {
        return nameInCart.getText();
    }

    public String getCartProductPrice() {
        return priceInCart.getText();
    }

    public EmptyCartPage deleteProduct() {
        deleteButton.click();
        return new EmptyCartPage();
    }
}
