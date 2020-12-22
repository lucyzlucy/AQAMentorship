package pages;

import business_objects.builders.ProductBuilder;
import business_objects.entities.Product;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static driver.DriverWrapper.isElementLoaded;
import static utils.StripNonDigitsUtil.stripNonDigits;

@Log4j2

public class ProductAddedPopup extends BasePage {
    @FindBy(id = "layer_cart_product_title")
    protected WebElement productName;

    @FindBy(id = "layer_cart_product_price")
    protected WebElement productPrice;

    @FindBy(id = "layer_cart_product_quantity")
    protected WebElement productQty;

    @FindBy(className = "ajax_block_products_total")
    protected WebElement cartProductTotalPrice;

    @FindBy(className = "ajax_cart_quantity")
    protected WebElement cartQty;
    @FindBy(xpath = "//h2[child::i]")
    protected WebElement successMessage;

    public ProductAddedPopup() {
        isElementLoaded(productName);

        log.info("The product is:\n" + getProduct().toString());
        log.info("Cart price is:" + getCartProductTotalPrice());
        log.info("Cart QTY is:" + getCartQty());

    }

    public String getMessage() {
        isElementLoaded(successMessage);
        return successMessage.getText();
    }

    public String getProductName() {
        return productName.getText();
    }

    public double getProductPrice() {
        return Double.parseDouble(stripNonDigits(productPrice.getText()));
    }

    public double getCartProductTotalPrice() {
        return Double.parseDouble(stripNonDigits(cartProductTotalPrice.getText()));
    }

    public int getProductQty() {
        return Integer.parseInt(productQty.getText());
    }

    public int getCartQty() {
        return Integer.parseInt(cartQty.getText());
    }

    public Product getProduct() {
        return new ProductBuilder().setName(getProductName()).setPrice(getProductPrice()).setQuantity(getProductQty()).make();
    }
}
