package pages;

import business_objects.builders.ProductBuilder;
import business_objects.entities.Product;
import business_objects.entities.ProductsInCart;
import driver.elements.CustomWebTable;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static utils.StripNonDigitsUtil.stripNonDigits;

@Log4j2
public class FullCartPage extends BasePage {
    @FindBy(className = "cart_quantity_delete")
    protected WebElement deleteButton;

    @FindBy(xpath = "//span[@class=\"price\"]/span")
    protected WebElement priceInCart;

    @FindBy(id = "total_product")
    protected WebElement totalCartPrice;

    @FindBy(xpath = "//tr[1]//p[@class=\"product-name\"]")
    protected WebElement nameInCart;

    @FindBy(id = "cart_summary")
    protected CustomWebTable cartTable;

    public FullCartPage() {
        waitForPageToLoad(totalCartPrice);
    }

    public String getCartProductName() {
        return nameInCart.getText();
    }

    public Product getCartProduct(int order) {
        String productName = getProductName(order);
        double productPrice = getProductPrice(order);
        int productQuantity = getProductQuantity(order);

        Product product = new ProductBuilder()
                .setName(productName)
                .setPrice(productPrice)
                .setQuantity(productQuantity)
                .make();

        return product;
    }

    public String getProductName(int productOrder) {
        return cartTable.getCellData(productOrder, 2, "/*[@class = \"product-name\"]");
    }

    public double getProductPrice(int productOrder) {
        return Double.parseDouble(stripNonDigits(cartTable.getCellData(productOrder, 4, "/*[@class = 'price']/*[contains (@class, 'price')][1]")));
    }

    public int getProductQuantity(int productOrder) {
        return Integer.parseInt(cartTable.getCellValue(productOrder, 5, "/*[contains(@class,'cart_quantity_input')]"));
    }

    public double getProductTotalPrice(int productOrder) {
        return Double.parseDouble(stripNonDigits(cartTable.getCellData(productOrder, 6)));
    }


    public double getCartTotalPrice() {
        return Double.parseDouble(stripNonDigits(totalCartPrice.getText()));
    }

    public double getCartProductPrice() {
        return Double.parseDouble(stripNonDigits(priceInCart.getText()));
    }

    public EmptyCartPage deleteProduct(int productOrder) {
        String productToBeDeleted = getProductName(productOrder);

        cartTable.getCellElement(productOrder, 7, "//a").click();

        log.info("Deleted product :\n" + productToBeDeleted);

        return new EmptyCartPage();
    }


    public int getProductsInCartNumber() {
        return cartTable.getRowCount();
    }

    public String getProductsInCart() {
        StringBuilder products = new StringBuilder();
        for (int i = 1; i <= getProductsInCartNumber(); i++) {
            products.append(getCartProduct(i).toString());
            products.append("\n");

        }
        return products.toString();
    }
}
