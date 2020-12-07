//package tests;
//
//import business_objects.entities.Product;
//import business_objects.entities.ProductsInCart;
//import org.testng.annotations.Test;
//import org.testng.asserts.SoftAssert;
//import pages.*;
//
//import static driver.DriverWrapper.waitImplicitly;
//import static environment.Environment.getEnvProperty;
//import static navigationUtil.PageNavigationUtil.*;
//
//
//public class CartScenarios extends BaseTest {
//
//    @Test
//    public void verifyAddingSingleProductToCart() {
//        ProductsInCart addedProduct = addSingleProductToCart();
//
//        verifyProductAddedPopupIsShownWithProductDetails(addedProduct);
//        verifyProductDetailsInCart(addedProduct);
//    }
//
//    @Test
//    public void verifyAddingMultipleArticlesToCart() {
//        ProductsInCart addedProduct = addMultipleArticlesOfTheSameProduct();
//
//        verifyProductAddedPopupIsShownWithProductDetails(addedProduct);
//        verifyProductDetailsInCart(addedProduct);
//    }
//
//
//    public ProductsInCart addSingleProductToCart() {
//        ProductCell page = toMainPage().clickOnRandomCatalogueSection().chooseRandomProduct();
//        ProductsInCart product = new ProductsInCart();
//        product.addProduct(page.getProduct());
//        page.addProductToCart();
//        return product;
//    }
//
//    public ProductsInCart addMultipleArticlesOfTheSameProduct() {
//        ProductCell page = toMainPage().clickOnRandomCatalogueSection().chooseRandomProduct();
//        ProductsInCart product = new ProductsInCart();
//        product.addProduct(page.getProduct());
//        //        page.clickOnProductName().addProductToCart();
//        return product;
//    }
//
//    public void verifyProductAddedPopupIsShownWithProductDetails(Product product) {
////        ProductCell page = toMainPage().clickOnRandomCatalogueSection().chooseRandomProduct();
////        Product product = page.getProduct();
////        page.addProductToCart();
//        ProductAddedPopup popup = new ProductAddedPopup();
//        SoftAssert softAssert = new SoftAssert();
//        softAssert.assertTrue(popup.getMessage().contains(getEnvProperty("successAddedToCartMessage")), "Confirmation message is shown");
//        softAssert.assertEquals(popup.getProductName(), product.getName(), "Correct name is shown");
//        softAssert.assertEquals(popup.getProductPrice(), product.getPrice(), "Correct price is shown");
//        softAssert.assertAll();
//    }
//
//    public void verifyProductDetailsInCart(Product product) {
//        waitImplicitly(1000);
//        FullCartPage cartPage = toFullCartPage();
//        SoftAssert softAssert = new SoftAssert();
//        softAssert.assertEquals(cartPage.getCartProductName(), product.getName(), "Correct name is shown");
//        softAssert.assertEquals(cartPage.getCartProductPrice(), product.getPrice(), "Correct price is shown");
//        softAssert.assertAll();
//    }
//
////    public void verifyProductDetailsInCart() {
////        waitImplicitly(1000);
////        FullCartPage cartPage = toFullCartPage();
////        SoftAssert softAssert = new SoftAssert();
////        softAssert.assertEquals(cartPage.getCartProductName(), product.getName(), "Correct name is shown");
////        softAssert.assertEquals(cartPage.getCartProductPrice(), product.getPrice(), "Correct price is shown");
////        softAssert.assertAll();
////    }
//
////    public void verifyDeletingProductsFromCart() {
////        waitImplicitly(1000);
////        FullCartPage cartPage = toFullCartPage();
////        cartPage.deleteProduct();
////        Assert.assertTrue(cartPage.getErrorMessage().contains(getEnvProperty("emptyCartMessage")), "Correct message about empty cart is shown");
////    }
//}
