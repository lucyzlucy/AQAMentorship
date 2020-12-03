package tests;

import business_objects.entities.Product;
import business_objects.entities.ProductsInCart;
import driver.DriverWrapper;
import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CataloguePage;
import pages.EmptyCartPage;
import pages.FullCartPage;

import static environment.Environment.getEnvProperty;
import static navigationUtil.PageNavigationUtil.*;


public class CartTests extends BaseTest {
    CataloguePage page;
    Product product;


    @BeforeMethod
    public void addProductToCart() {
        page = toMainPage();
        product = page.clickOnRandomCatalogueSection().chooseRandomProduct().addProductToCart().getProduct();
    }

    @Test
    public void verifyProductCanBeAddedToCart() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(page.getMessage().contains(getEnvProperty("successAddedToCartMessage")), "Confirmation message is shown");
        softAssert.assertEquals(page.getProductName(), product.getName(), "Correct name is shown");
        softAssert.assertEquals(page.getProductPrice(), product.getPrice(), "Correct price is shown");
        softAssert.assertAll();
    }

    @Test
    public void verifyProductDetailsInCart() {
        page.closePopup();

        FullCartPage cartPage = toFullCartPage();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(cartPage.getCartProductName(), product.getName(), "Correct name is shown");
        softAssert.assertEquals(cartPage.getCartProductPrice(), product.getPrice(), "Correct price is shown");
        softAssert.assertAll();
    }

    @Test
    @SneakyThrows
    public void verifyDeletingProductsFromCart() {
//        DriverWrapper.waitImplicitly();

        page.closePopup();
//        Thread.sleep(1000);
        EmptyCartPage cartPage = toFullCartPage().deleteProduct();
        Assert.assertTrue(cartPage.getErrorMessage().contains(getEnvProperty("emptyCartMessage")), "Correct message about empty cart is shown");
    }
}
