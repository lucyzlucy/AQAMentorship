package tests;

import business_objects.entities.Product;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CataloguePage;
import pages.FullCartPage;

import static environment.Environment.getEnvProperty;
import static navigationUtil.PageNavigationUtil.toMainPage;

public class CartTests2 extends BaseTest {
    CataloguePage page;
    Product product;
    FullCartPage cartPage;

    @Test
    public void verifyProductCanBeAddedToCart() {
        page = toMainPage();
        product = page.clickOnRandomCatalogueSection().chooseRandomProduct().addProductToCart().getProduct();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(page.getMessage().contains(getEnvProperty("successAddedToCartMessage")), "Confirmation message is shown");
        softAssert.assertEquals(page.getProductName(), product.getName(), "Correct name is shown");
        softAssert.assertEquals(page.getProductPrice(), product.getPrice(), "Correct price is shown");
        softAssert.assertAll();
    }


    @Test(dependsOnMethods = "verifyProductCanBeAddedToCart")
    public void verifyProductDetailsInCart() {
        page.closePopup();
        cartPage = page.clickOnCart();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(cartPage.getCartProductName(), product.getName(), "Correct name is shown");
        softAssert.assertEquals(cartPage.getCartProductPrice(), product.getPrice(), "Correct price is shown");
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = "verifyProductDetailsInCart")
    public void verifyDeletingProductsFromCart() {
        cartPage.deleteProduct();
        Assert.assertTrue(cartPage.getErrorMessage().contains(getEnvProperty("emptyCartMessage")), "Correct message about empty cart is shown");
    }
}
