package business_objects.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Product {
    private String name;
    private double price;
    private int quantity = 1;
    private double totalPrice = price * quantity;

    public void setQuantity(int q){
        this.quantity = q;
        totalPrice = price * quantity;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Product)) {
            return false;
        }
        Product product = (Product) o;
        return Objects.equals(name, product.name) &&
                price == product.price &&
                quantity == product.quantity &&
                totalPrice == product.totalPrice;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, quantity, totalPrice);
    }
}
