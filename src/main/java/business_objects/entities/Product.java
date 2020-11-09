package business_objects.entities;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebElement;

@Getter
@Setter
public class Product {
    private String name;
    private String price;
    private int quantity = 1;
    private WebElement productElement;
}
