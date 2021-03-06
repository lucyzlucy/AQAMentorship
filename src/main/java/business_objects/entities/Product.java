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
    private double totalPrice;

    public void setPrice(double p) {
        this.price = p;
        setTotalPrice();
    }

    private void setTotalPrice() {
        totalPrice =  Math.round((price * quantity) * 100.0) / 100.0;
    }

    public void setQuantity(int q) {
        this.quantity = q;
        setTotalPrice();
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

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
