package tests;

import business_objects.entities.Product;
import business_objects.entities.ProductsInCart;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;

import static driver.DriverWrapper.waitImplicitly;
import static environment.Environment.getEnvProperty;
import static navigationUtil.PageNavigationUtil.*;


public class CartTests extends BaseTest {
    Product product;


    @BeforeMethod
    public void addSingleProductToCart() {
        ProductCell page = toMainPage().clickOnRandomCatalogueSection().chooseRandomProduct();
        product = page.getProduct();
        page.addProductToCart();
    }

    @Test
    public void verifyProductAddedPopupIsShownWithProductDetails() {
        ProductAddedPopup popup = new ProductAddedPopup();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(popup.getMessage().contains(getEnvProperty("successAddedToCartMessage")), "Confirmation message is shown");
        softAssert.assertEquals(popup.getProductName(), product.getName(), "Correct name is shown");
        softAssert.assertEquals(popup.getProductPrice(), product.getPrice(), "Correct price is shown");
        softAssert.assertAll();
    }

    @Test
    public void verifyProductDetailsInCart() {
        waitImplicitly(1000);
        FullCartPage cartPage = toFullCartPage();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(cartPage.getCartProductName(), product.getName(), "Correct name is shown");
        softAssert.assertEquals(cartPage.getCartProductPrice(), product.getPrice(), "Correct price is shown");
        softAssert.assertAll();
    }


    @Test
    public void verifyDeletingProductsFromCart() {
        waitImplicitly(1000);
        FullCartPage cartPage = toFullCartPage();
        cartPage.deleteProduct(1);

        Assert.assertTrue(cartPage.getErrorMessage().contains(getEnvProperty("emptyCartMessage")), "Correct message about empty cart is shown");
    }
}
