package business_objects.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductsInCart {
    List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        for (Product p : products) {
            if (p.getName().equals(product.getName())) {
                p.setQuantity(p.getQuantity() + product.getQuantity());
                return;
            }
        }
        products.add(product);
    }

    public double getTotalPrice() {
        double sum = 0;
        for (Product product : products) {
            sum += product.getTotalPrice();
        }
        return sum;
    }

    public int getProductsNumber() {
        return products.size();
    }

    public boolean isProductInList(Product product) {
        return products.contains(product);
    }

}
