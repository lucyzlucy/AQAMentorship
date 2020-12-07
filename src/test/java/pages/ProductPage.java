package pages;

import business_objects.entities.Product;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static utils.StripNonDigitsUtil.stripNonDigits;

public class ProductPage extends BasePage{

    @FindBy(xpath = "//*[@itemprop='name']")
    protected WebElement productName;

    @FindBy(id = "our_price_display")
    protected WebElement productPrice;

    @FindBy(id = "quantity_wanted")
    protected WebElement quantity;

    public String getProductName() {
        return productName.getText();
    }

    public double getProductPrice() {
        return Double.parseDouble(stripNonDigits(productPrice.getText()));
    }


}
