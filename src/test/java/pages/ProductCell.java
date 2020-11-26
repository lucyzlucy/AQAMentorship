package pages;

import business_objects.builders.ProductBuilder;
import business_objects.entities.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductCell extends BasePage {
    WebElement productElement;
    WebElement addToCartButton;

    public ProductCell(WebElement productElement) {
        this.productElement = productElement;
        addToCartButton = productElement.findElement(By.className("ajax_add_to_cart_button"));
//        waitForPageToLoad(productElement);
    }

    public ProductCell addProductToCart() {
        productElement.click();
        addToCartButton.click();
        return this;
    }

    public Product getProduct() {

        String productName = productElement.findElement(By.className("product-name")).getText();
        String productPrice = getVisibleElement(productElement.findElements(By.className("price"))).getText();

        return new ProductBuilder().setName(productName).setPrice(productPrice).make();
    }
}
