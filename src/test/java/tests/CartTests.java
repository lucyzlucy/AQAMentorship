package tests;

import business_objects.entities.Product;
import business_objects.entities.ProductsInCart;
import driver.DriverWrapper;
import lombok.extern.log4j.Log4j2;
import org.junit.After;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;

import static driver.DriverWrapper.waitImplicitly;
import static environment.Environment.getEnvProperty;
import static navigationUtil.PageNavigationUtil.*;

@Log4j2

public class CartTests extends BaseTest {
    Product product;
    ProductAddedPopup popup;
    FullCartPage cartPage;

    @BeforeMethod
    public void addSingleProductToCart() {
        ProductCell page = toMainPage().clickOnRandomCatalogueSection().chooseRandomProduct();
        product = page.getProduct();
        popup = page.addProductToCart();
    }

    @Test
    public void verifyProductAddedPopupIsShownWithProductDetails() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(popup.getMessage().contains(getEnvProperty("successAddedToCartMessage")), "Confirmation message is shown");
        softAssert.assertEquals(popup.getProductName(), product.getName(), "Correct name is shown");
        softAssert.assertEquals(popup.getProductPrice(), product.getPrice(), "Correct price is shown");
        softAssert.assertAll();
    }

    @Test
    public void verifyProductDetailsInCart() {
        waitImplicitly(1000);
        cartPage = toFullCartPage();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(cartPage.getCartProductName(), product.getName(), "Correct name is shown");
        softAssert.assertEquals(cartPage.getCartProductPrice(), product.getPrice(), "Correct price is shown");
        softAssert.assertAll();
    }


    @Test
    public void verifyDeletingProductsFromCart() {
        waitImplicitly(1000);
        cartPage = toFullCartPage();
        cartPage.deleteProduct(1);

        Assert.assertTrue(cartPage.getErrorMessage().contains(getEnvProperty("emptyCartMessage")), "Correct message about empty cart is shown");
    }

}
