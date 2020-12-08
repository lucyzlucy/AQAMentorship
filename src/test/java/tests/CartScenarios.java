package tests;

import business_objects.entities.Product;
import business_objects.entities.ProductsInCart;
import environment.Environment;
import org.testng.Assert;
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
        Product addedProduct = addSingleProductToCart(false);

        verifyProductAddedPopupIsShownWithProductDetails(addedProduct);
        verifyProductDetailsInCart(new ProductsInCart().addProduct(addedProduct));
    }

    @Test
    public void verifyAddingMultipleArticlesToCart() {
        Product addedProduct = addMultipleArticlesOfTheSameProduct();

        verifyProductAddedPopupIsShownWithProductDetails(addedProduct);
        verifyProductDetailsInCart(new ProductsInCart().addProduct(addedProduct));
    }

    @Test
    public void verifyAddingDiscountedProductToCart() {
        Product addedProduct = addSingleProductToCart(true);

        verifyProductAddedPopupIsShownWithProductDetails(addedProduct);
        verifyProductDetailsInCart(new ProductsInCart().addProduct(addedProduct));
    }

    @Test
    public void verifyAddingTwoDifferentProductsToCart() {
        ProductsInCart addedProducts = new ProductsInCart();
        Product discountedProduct = addSingleProductToCart(true);
        waitImplicitly(1000);
        Product regularProduct = addSingleProductToCart(false);
        waitImplicitly(1000);

        addedProducts.addProduct(discountedProduct);
        addedProducts.addProduct(regularProduct);

        verifyProductDetailsInCart(addedProducts);
    }


    private Product addSingleProductToCart(boolean discounted) {
        ProductPage page;
        if (discounted) {
            page = toDiscountedProductPage();
        } else {
            page = toRegularProductPage();
        }
        Product product = page.getProduct();
        page.addProductToCart();
        return product;
    }

    private Product addMultipleArticlesOfTheSameProduct() {
        ProductPage page = toMainPage().clickOnRandomCatalogueSection().chooseRandomProduct().clickOnProductName();
        int productQty = getRandomInt(Integer.parseInt(Environment.getEnvProperty("maxProductsInCart")));
        page.setProductQty(productQty);
        Product product = page.getProduct();
        page.addProductToCart();
        return product;
    }

    private void verifyProductAddedPopupIsShownWithProductDetails(Product product) {
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

//    private void verifyProductDetailsInCart(Product product) {
//        waitImplicitly(1000);
//        FullCartPage cartPage = toFullCartPage();
//        SoftAssert softAssert = new SoftAssert();
//        softAssert.assertEquals(cartPage.getCartProductName(), product.getName(), "Correct name is shown in cart ");
//        softAssert.assertEquals(cartPage.getCartProductPrice(), product.getPrice(), "Correct price is shown in cart");
//        softAssert.assertEquals(cartPage.getProductQuantity(1), product.getQuantity(), "Correct quantity is shown in cart");
//        softAssert.assertEquals(cartPage.getProductTotalPrice(1), product.getTotalPrice(), "Correct product total is shown in cart");
//        softAssert.assertEquals(cartPage.getCartTotalPrice(), product.getTotalPrice(), "Correct cart total is shown in cart");
//
//        softAssert.assertAll();
//    }

    private void verifyProductDetailsInCart(ProductsInCart addedProducts) {
        waitImplicitly(1000);
        FullCartPage cartPage = toFullCartPage();

        int numberOfProductsInCart = cartPage.getProductsInCartNumber();

        Assert.assertEquals(numberOfProductsInCart, addedProducts.getProductsNumber(),
                "Correct number of products in cart. Actual products in cart are: \n" + cartPage.getProductsInCart() + "Expected products in cart are:" + addedProducts.getPrintableProductsInCart());

        for (int i = 1; i <= numberOfProductsInCart; i++) {
            Product product = cartPage.getCartProduct(i);
            Assert.assertTrue(addedProducts.isProductInList(product), "Product in cart" + product.toString() +" corresponds to added product");
        }


        Assert.assertEquals(cartPage.getCartTotalPrice(), addedProducts.getTotalPrice(), "The total price is correct");


    }

}
