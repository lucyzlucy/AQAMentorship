package tests.cartTests;

import business_objects.entities.Product;
import business_objects.entities.ProductsInCart;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FullCartPage;

import static driver.DriverWrapper.waitImplicitly;
import static environment.Environment.getEnvProperty;
import static navigationUtil.PageNavigationUtil.toFullCartPage;

public class RemovingFromCartTests extends AddingToCartPreconditions {

    @Test(groups = "deleting")
    public void verifyTheOnlyProductCanBeRemoved() {
        waitImplicitly(1500);
        FullCartPage cartPage = toFullCartPage();
        cartPage.deleteProduct(1);

        Assert.assertTrue(cartPage.getErrorMessage().contains(getEnvProperty("emptyCartMessage")), "Correct message about empty cart is shown");
    }

    @Test(groups = "deleting")
    public void verifyOneProductOfMultipleCanBeRemoved() {
        waitImplicitly(1500);
        FullCartPage cartPage = toFullCartPage();

        ProductsInCart productsInCart = new ProductsInCart();
        for (int i = 1; i <= cartPage.getProductsInCartNumber(); i++) {
            productsInCart.addProduct(cartPage.getCartProduct(i));
        }

        Product productToDelete = cartPage.getCartProduct(1);
        productsInCart.deleteProductInList(productToDelete);
        cartPage.deleteProduct(1);
        waitImplicitly(1500);


        Assert.assertEquals(cartPage.getProductsInCartNumber(), productsInCart.getProductsNumber(),
                "Correct number of products in cart. Actual products in cart are: \n" + cartPage.getProductsInCart() + "Expected products in cart are:" + productsInCart.getPrintableProductsInCart());

        for (int i = 1; i <= cartPage.getProductsInCartNumber(); i++) {
            Product product = cartPage.getCartProduct(i);
            Assert.assertTrue(productsInCart.isProductInList(product), "Product in cart" + product.toString() + " corresponds to added product" +
                    "\nActual products in cart are: \n" + cartPage.getProductsInCart() + "\nExpected products in cart are: \n" + productsInCart.getPrintableProductsInCart());
        }


        Assert.assertEquals(cartPage.getCartTotalPrice(), productsInCart.getTotalPrice(), "The total price is correct");
    }

    @Test(groups = "deleting")
    public void verifyAllMultipleProductsCanBeRemoved() {
        waitImplicitly(1500);
        FullCartPage cartPage = toFullCartPage();

        int numberOfProductsInCart = cartPage.getProductsInCartNumber();

        for (int i = 1; i <= numberOfProductsInCart; i++) {
            cartPage.deleteProduct(1);
            waitImplicitly(1000);
        }

        Assert.assertTrue(cartPage.getErrorMessage().contains(getEnvProperty("emptyCartMessage")), "Correct message about empty cart is shown");
    }
}
