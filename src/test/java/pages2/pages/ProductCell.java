package pages2.pages;

import business_objects.builders.ProductBuilder;
import business_objects.entities.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static driver.DriverWrapper.waitImplicitly;
import static utils.StripNonDigitsUtil.stripNonDigits;

public class ProductCell extends BasePage {
    WebElement productElement;
    WebElement addToCartButton;
    WebElement productName;
    WebElement productPrice;

    public ProductCell(WebElement productElement) {
        this.productElement = productElement;
        addToCartButton = productElement.findElement(By.className("ajax_add_to_cart_button"));
        productName = productElement.findElement(By.className("product-name"));
        productPrice = productElement.findElement(By.xpath(".//div[2]//*[@class='content_price'][1]/span[1]"));
    }

    public ProductAddedPopup addProductToCart() {
        addToCartButton.click();
        waitImplicitly(1000);
        return new ProductAddedPopup();
    }

    public Product getProduct() {
        return new ProductBuilder().setName(getProductName()).setPrice(getProductPrice()).make();
    }

    public double getProductPrice() {
        return Double.parseDouble(stripNonDigits(productPrice.getText()));
    }

    public String getProductName() {
        return productName.getText();
    }

    public ProductPage clickOnProductName() {
        productName.click();
        return new ProductPage();
    }
}
