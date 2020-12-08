package tests;

import business_objects.entities.Product;
import business_objects.entities.ProductsInCart;
import environment.Environment;
import lombok.SneakyThrows;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;

import static driver.DriverWrapper.waitImplicitly;
import static environment.Environment.getEnvProperty;
import static navigationUtil.PageNavigationUtil.*;
import static utils.Randomizer.getRandomInt;


public class CartScenarios extends BaseTest {

    @Test
    public void verifyAddingSingleProductToCart() {
        Product addedProduct = addSingleProductToCart();

        verifyProductAddedPopupIsShownWithProductDetails(addedProduct);
        verifyProductDetailsInCart(addedProduct);
    }

    @Test
    public void verifyAddingMultipleArticlesToCart() {
        Product addedProduct = addMultipleArticlesOfTheSameProduct();

        verifyProductAddedPopupIsShownWithProductDetails(addedProduct);
        verifyProductDetailsInCart(addedProduct);
    }

    @Test
    public void verifyAddingDiscountedProductToCart() {
        Product addedProduct = addDiscountedProductToCart();

        verifyProductAddedPopupIsShownWithProductDetails(addedProduct);
        verifyProductDetailsInCart(addedProduct);
    }


    public Product addSingleProductToCart() {
        ProductCell page = toMainPage().clickOnRandomCatalogueSection().chooseRandomProduct();
        Product product = page.getProduct();
        page.addProductToCart();
        return product;
    }

    public Product addMultipleArticlesOfTheSameProduct() {
        ProductPage page = toMainPage().clickOnRandomCatalogueSection().chooseRandomProduct().clickOnProductName();
        int productQty = getRandomInt(Integer.parseInt(Environment.getEnvProperty("maxProductsInCart")));
        page.setProductQty(productQty);
        Product product = page.getProduct();
        page.addProductToCart();
        return product;
    }

    public Product addDiscountedProductToCart() {
        ProductPage page = toDiscountedProductPage();
        Product product = page.getProduct();
        page.addProductToCart();
        return product;
    }

    public void verifyProductAddedPopupIsShownWithProductDetails(Product product) {
        ProductAddedPopup popup = new ProductAddedPopup();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(popup.getMessage().contains(getEnvProperty("successAddedToCartMessage")), "Confirmation message is shown on the popup");
        softAssert.assertEquals(popup.getProductName(), product.getName(), "Correct name is shown on the popup");
        softAssert.assertEquals(popup.getProductPrice(), product.getTotalPrice(), "Correct products price is shown on the popup");
        softAssert.assertEquals(popup.getCartProductTotalPrice(), product.getTotalPrice(), "Correct cart price is shown on the popup");
        softAssert.assertEquals(popup.getProductQty(), product.getQuantity(), "Correct products qty is shown on the popup");
        softAssert.assertEquals(popup.getCartQty(), product.getQuantity(), "Correct cart qty is shown on the popup");

        softAssert.assertAll();
    }

    public void verifyProductDetailsInCart(Product product) {
        waitImplicitly(1000);
        FullCartPage cartPage = toFullCartPage();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(cartPage.getCartProductName(), product.getName(), "Correct name is shown in cart ");
        softAssert.assertEquals(cartPage.getCartProductPrice(), product.getPrice(), "Correct price is shown in cart");
        softAssert.assertEquals(cartPage.getProductQuantity(1), product.getQuantity(), "Correct quantity is shown in cart");
        softAssert.assertEquals(cartPage.getProductTotalPrice(1), product.getTotalPrice(), "Correct product total is shown in cart");
        softAssert.assertEquals(cartPage.getCartTotalPrice(), product.getTotalPrice(), "Correct cart total is shown in cart");

        softAssert.assertAll();
    }

}
