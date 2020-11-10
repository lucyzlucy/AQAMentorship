package tests;

import business_objects.entities.Product;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CartPage;
import pages.CataloguePage;

import static data.TestData.*;


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
        softAssert.assertTrue(page.getMessage().contains(SUCCESS_ADDED_TO_CART_MESSAGE), "Confirmation message is shown");
        softAssert.assertEquals(page.getProductName(), product.getName(), "Correct name is shown");
        softAssert.assertEquals(page.getProductPrice(), product.getPrice(), "Correct price is shown");
        softAssert.assertAll();
    }

    @Test
    public void verifyProductDetailsInCart() {
        page.closePopup();
        CartPage cartPage = page.clickOnCart();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(cartPage.getCartProductName(), product.getName(), "Correct name is shown");
        softAssert.assertEquals(cartPage.getCartProductPrice(), product.getPrice(), "Correct price is shown");
        softAssert.assertAll();
    }

    @Test
    public void verifyDeletingProductsFromCart() {
        page.closePopup();
        CartPage cartPage = page.clickOnCart().deleteProduct();

        Assert.assertTrue(cartPage.compareErrorMessage(EMPTY_CART_MESSAGE), "Correct message about empty cart is shown");
    }
}
