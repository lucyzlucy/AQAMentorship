package pages;

import business_objects.builders.ProductBuilder;
import business_objects.entities.Product;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static utils.StripNonDigitsUtil.stripNonDigits;

@Log4j2
public class ProductPage extends BasePage {

    @FindBy(xpath = "//*[@itemprop='name']")
    protected WebElement productName;

    @FindBy(id = "our_price_display")
    protected WebElement productPrice;

    @FindBy(id = "quantity_wanted")
    protected WebElement quantity;

    @FindBy(xpath = "//*[@id='add_to_cart']//button[@type='submit']")
    protected WebElement addToCartButton;

    public ProductPage() {
        log.info("The product is:\n" + getProduct().toString());
    }

    public String getProductName() {
        return productName.getText();
    }

    public double getProductPrice() {
        return Double.parseDouble(stripNonDigits(productPrice.getText()));
    }

    public int getProductQty() {
        return Integer.parseInt(quantity.getAttribute("value"));
    }

    public void setProductQty(int qty) {
        quantity.sendKeys(String.valueOf(qty));
    }

    public Product getProduct() {
        return new ProductBuilder().setName(getProductName()).setPrice(getProductPrice()).setQuantity(getProductQty()).make();
    }

    public void addProductToCart() {
        addToCartButton.click();

        log.info("Added product to cart by clicking on addToCartButton.");

    }
}
