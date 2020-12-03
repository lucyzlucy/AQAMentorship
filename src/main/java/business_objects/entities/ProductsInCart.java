package business_objects.entities;

import java.util.ArrayList;
import java.util.List;

public class ProductsInCart {
    List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        for (Product p : products) {
            if (p.getName().equals(product.getName())&&p.getPrice()==(product.getPrice())) {
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
        return Math.round(sum* 100.0) / 100.0;
    }

    public int getProductsNumber() {
        return products.size();
    }

    public boolean isProductInList(Product product) {
        return products.contains(product);
    }

    public void printProductsInCart() {
        for (Product product : products) {
            System.out.println(product.toString());
        }
    }

}
