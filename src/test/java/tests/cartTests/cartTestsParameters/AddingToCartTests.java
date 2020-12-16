package tests.cartTests.cartTestsParameters;

import business_objects.entities.Product;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.FullCartPage;
import pages.ProductAddedPopup;

import static driver.DriverWrapper.waitImplicitly;
import static environment.Environment.getEnvProperty;
import static navigationUtil.PageNavigationUtil.*;

public class AddingToCartTests extends AddingToCartPreconditions {

    @Test(groups = "adding")
    public void verifyProductAddedPopupIsShownWithProductDetails() {
        ProductAddedPopup popup = new ProductAddedPopup();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(popup.getMessage().contains(getEnvProperty("successAddedToCartMessage")), "Confirmation message is shown on the popup");
        softAssert.assertEquals(popup.getProductName(), addedProduct.getName(), "Correct name is shown on the popup");
        softAssert.assertEquals(popup.getProductPrice(), addedProduct.getTotalPrice(), "Correct products price is shown on the popup");
        softAssert.assertEquals(popup.getProductQty(), addedProduct.getQuantity(), "Correct product qty is shown on the popup");

        softAssert.assertEquals(popup.getCartProductTotalPrice(), productsInCart.getTotalPrice(), "Correct cart price is shown on the popup");
        softAssert.assertEquals(popup.getCartQty(), productsInCart.getQuantity(), "Correct cart qty is shown on the popup");

        softAssert.assertAll();
    }

    @Test(groups = "adding")
    public void verifyProductDetailsInCart() {
        waitImplicitly(1000);
        FullCartPage cartPage = toFullCartPage();

        int numberOfProductsInCart = cartPage.getProductsInCartNumber();

        Assert.assertEquals(numberOfProductsInCart, productsInCart.getProductsNumber(),
                "Correct number of products in cart. Actual products in cart are: \n" + cartPage.getProductsInCart() + "Expected products in cart are:" + productsInCart.getPrintableProductsInCart());

        for (int i = 1; i <= numberOfProductsInCart; i++) {
            Product product = cartPage.getCartProduct(i);
            Assert.assertTrue(productsInCart.isProductInList(product), "Product in cart" + product.toString() + " corresponds to added product" +
                    "\nActual products in cart are: \n" + cartPage.getProductsInCart() + "\nExpected products in cart are: \n" + productsInCart.getPrintableProductsInCart());
        }


        Assert.assertEquals(cartPage.getCartTotalPrice(), productsInCart.getTotalPrice(), "The total price is correct");
    }


}



