package business_objects.builders;

import business_objects.entities.Product;
import business_objects.entities.User;
import org.openqa.selenium.WebElement;

public class ProductBuilder {
    private Product product = new Product();

    public Product make() {
        return product;
    }

    public ProductBuilder setName(String name) {
        product.setName(name);
        return this;
    }

    public ProductBuilder setPrice(String price) {
        product.setPrice(price);
        return this;
    }

    public ProductBuilder setQuantity(int quantity) {
        product.setQuantity(quantity);
        return this;
    }

    public ProductBuilder setProductElement(WebElement element) {
        product.setProductElement(element);
        return this;
    }
}
