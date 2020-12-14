package business_objects.entities;

import java.util.ArrayList;
import java.util.List;

public class ProductsInCart {
    List<Product> products = new ArrayList<>();

    public ProductsInCart addProduct(Product product) {
        for (Product p : products) {
            if (p.getName().equals(product.getName()) && p.getPrice() == (product.getPrice())) {
                p.setQuantity(p.getQuantity() + product.getQuantity());
                return this;
            }
        }
        products.add(product);
        return this;
    }

    public double getTotalPrice() {
        double sum = 0;
        for (Product product : products) {
            sum += product.getTotalPrice();
        }
        return Math.round(sum * 100.0) / 100.0;
    }

    public int getProductsNumber() {
        return products.size();
    }

    public int getQuantity() {

        int qty = 0;
        for (Product product : products) {
            qty += product.getQuantity();
        }
        return qty;
    }


    public boolean isProductInList(Product product) {
        return products.contains(product);
    }


    public void deleteProductInList(Product product) {
         products.remove(product);
    }

    public String getPrintableProductsInCart() {
        StringBuilder productsPrintable = new StringBuilder();

        for (Product product : products) {
            productsPrintable.append(product.toString());
            productsPrintable.append("\n");
        }

        return productsPrintable.toString();
    }

}
