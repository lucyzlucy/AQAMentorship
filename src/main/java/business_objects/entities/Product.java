package business_objects.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private String name;
    private String price;
    private int quantity = 1;
}
