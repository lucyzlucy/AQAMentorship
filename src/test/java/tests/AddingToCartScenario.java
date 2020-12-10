package tests;

import business_objects.entities.Product;
import business_objects.entities.ProductsInCart;
import driver.DriverWrapper;
import environment.Environment;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.FullCartPage;
import pages.ProductAddedPopup;
import pages.ProductPage;

import static driver.DriverWrapper.waitImplicitly;
import static environment.Environment.getEnvProperty;
import static navigationUtil.PageNavigationUtil.*;
import static utils.Randomizer.getRandomInt;

public class AddingToCartScenario extends BaseTest {
    Product addedProduct;
    ProductsInCart productsInCart = new ProductsInCart();

    @Parameters({"product"})
    @BeforeClass
    public void addProductToCart(String product) {
        switch (product) {
            case "singleProduct": {
                addedProduct = addSingleProductToCart(false);
                productsInCart.addProduct(addedProduct);
            }
            break;
            case "discountedProduct": {
                addedProduct = addSingleProductToCart(true);
                productsInCart.addProduct(addedProduct);
            }
            break;
            case "multipleArticlesProduct": {
                addedProduct = addMultipleArticlesOfTheSameProduct();
                productsInCart.addProduct(addedProduct);
            }
            break;
            case "threeDifferentProducts": {
                addedProduct = addMultipleArticlesOfTheSameProduct();
                productsInCart.addProduct(addedProduct);
                waitImplicitly(1000);

                addedProduct = addSingleProductToCart(true);
                productsInCart.addProduct(addedProduct);
                waitImplicitly(1000);

                addedProduct = addSingleProductToCart(false);
                productsInCart.addProduct(addedProduct);
                waitImplicitly(1000);
            }
        }
    }

    @Test
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

//    @Test
//    public void verifyProductDetailsInCart() {
//        waitImplicitly(1000);
//        FullCartPage cartPage = toFullCartPage();
//        SoftAssert softAssert = new SoftAssert();
//        softAssert.assertEquals(cartPage.getCartProductName(), addedProduct.getName(), "Correct name is shown in cart ");
//        softAssert.assertEquals(cartPage.getCartProductPrice(), addedProduct.getPrice(), "Correct price is shown in cart");
//        softAssert.assertEquals(cartPage.getProductQuantity(1), addedProduct.getQuantity(), "Correct quantity is shown in cart");
//        softAssert.assertEquals(cartPage.getProductTotalPrice(1), addedProduct.getTotalPrice(), "Correct product total is shown in cart");
//        softAssert.assertEquals(cartPage.getCartTotalPrice(), addedProduct.getTotalPrice(), "Correct cart total is shown in cart");
//
//        softAssert.assertAll();
//    }

    @Test
    public void verifyProductDetailsInCart() {
        waitImplicitly(1000);
        FullCartPage cartPage = toFullCartPage();

        int numberOfProductsInCart = cartPage.getProductsInCartNumber();

        Assert.assertEquals(numberOfProductsInCart, productsInCart.getProductsNumber(),
                "Correct number of products in cart. Actual products in cart are: \n" + cartPage.getProductsInCart() + "Expected products in cart are:" + productsInCart.getPrintableProductsInCart());

        for (int i = 1; i <= numberOfProductsInCart; i++) {
            Product product = cartPage.getCartProduct(i);
            Assert.assertTrue(productsInCart.isProductInList(product), "Product in cart" + product.toString() +" corresponds to added product" +
                    "\nActual products in cart are: \n" + cartPage.getProductsInCart() + "\nExpected products in cart are: \n" + productsInCart.getPrintableProductsInCart());
        }


        Assert.assertEquals(cartPage.getCartTotalPrice(), productsInCart.getTotalPrice(), "The total price is correct");
    }

    @AfterClass
    @Override
    public void tearDown() {
        DriverWrapper.clearBrowserCookies();
    }

//
//    @Test
//    public void verifyAddingSingleProductToCart() {
//        Product addedProduct = addSingleProductToCart(false);
//
//        verifyProductAddedPopupIsShownWithProductDetails(addedProduct);
//        verifyProductDetailsInCart(new ProductsInCart().addProduct(addedProduct));
//    }
//
//    @Test
//    public void verifyAddingMultipleArticlesToCart() {
//        Product addedProduct = addMultipleArticlesOfTheSameProduct();
//
//        verifyProductAddedPopupIsShownWithProductDetails(addedProduct);
//        verifyProductDetailsInCart(new ProductsInCart().addProduct(addedProduct));
//    }
//
//    @Test
//    public void verifyAddingDiscountedProductToCart() {
//        Product addedProduct = addSingleProductToCart(true);
//
//        verifyProductAddedPopupIsShownWithProductDetails(addedProduct);
//        verifyProductDetailsInCart(new ProductsInCart().addProduct(addedProduct));
//    }
//
//    @Test
//    public void verifyAddingTwoDifferentProductsToCart() {
//        ProductsInCart addedProducts = new ProductsInCart();
//        Product discountedProduct = addSingleProductToCart(true);
//        waitImplicitly(1000);
//        Product regularProduct = addSingleProductToCart(false);
//        waitImplicitly(1000);
//
//        addedProducts.addProduct(discountedProduct);
//        addedProducts.addProduct(regularProduct);
//
//        verifyProductDetailsInCart(addedProducts);
//    }


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
        ProductPage page = toRegularProductPage2();
        int productQty = getRandomInt(Integer.parseInt(Environment.getEnvProperty("maxProductsInCart")));
        page.setProductQty(productQty);
        Product product = page.getProduct();
        page.addProductToCart();
        return product;
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


}
