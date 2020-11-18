package tests;

import business_objects.entities.Product;
import environment.Environment;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CataloguePage;
import pages.EmptyCartPage;
import pages.FullCartPage;



public class CartTests extends BaseTest {
    CataloguePage page;
    Product product;

    @BeforeMethod
    public void addProductToCart() {
        page = new CataloguePage();
        product = page.clickOnRandomCatalogueSection().chooseRandomProduct();
        page.addProductToCart(product);
    }

    @Test
    public void verifyConfirmationPopupWithProductDetailsIsShown() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(page.getMessage().contains(environment.getEnvProperty("successAddedToCartMessage")), "Confirmation message is shown");
        softAssert.assertEquals(page.getProductName(), product.getName(), "Correct name is shown");
        softAssert.assertEquals(page.getProductPrice(), product.getPrice(), "Correct price is shown");
        softAssert.assertAll();
    }

    @Test
    public void verifyProductDetailsInCart() {
        page.closePopup();
        FullCartPage cartPage = page.clickOnCart();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(cartPage.getCartProductName(), product.getName(), "Correct name is shown");
        softAssert.assertEquals(cartPage.getCartProductPrice(), product.getPrice(), "Correct price is shown");
        softAssert.assertAll();
    }

    @Test
    public void verifyDeletingProductsFromCart() {
        page.closePopup();
        EmptyCartPage cartPage = page.clickOnCart().deleteProduct();
        Assert.assertTrue(cartPage.getErrorMessage().contains(environment.getEnvProperty("emptyCartMessage")), "Correct message about empty cart is shown");
    }
}
