package tests;

import business_objects.entities.Product;
import business_objects.entities.ProductsInCart;
import environment.Environment;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CataloguePage;
import pages.FullCartPage;

import static navigationUtil.PageNavigationUtil.toFullCartPage;
import static navigationUtil.PageNavigationUtil.toMainPage;
import static utils.Randomizer.getRandomInt;

public class CartMultipleProductsTests extends BaseTest{
    CataloguePage page;
    Product product;
    ProductsInCart addedProducts;

    @Test
    public void verifyAddingSeveralIdenticalProducts() {

//        addedProducts = new ProductsInCart();
//        for (int i = 0; i < 2; i++) {
//            page = toMainPage();
//            product = page.clickOnRandomCatalogueSection().chooseRandomProduct().addProductToCart().getProduct();
//            addedProducts.addProduct(product);
//        }
//
//        addedProducts.printProductsInCart();
//        FullCartPage cartPage = toFullCartPage();
//
//        int numberOfProductsInCart = cartPage.getProductsInCartNumber();
//        System.out.println(numberOfProductsInCart);
//        Assert.assertEquals(numberOfProductsInCart, addedProducts.getProductsNumber(), "Correct number of products in cart");
//
//
//        for (int i = 1; i <= numberOfProductsInCart; i++) {
//            product = cartPage.getCartProduct(i);
//            Assert.assertTrue(addedProducts.isProductInList(product), "Product in cart" + product.toString() +" corresponds to added product");
//        }
//
//
//        Assert.assertEquals(cartPage.getCartTotalPrice(), addedProducts.getTotalPrice(), "The total price is correct");


    }

    @Test
    public void addProductsToCart() {
//        addedProducts = new ProductsInCart();
//        int numberOfProducts = getRandomInt(Integer.parseInt(Environment.getEnvProperty("maxProductsInCart")));
//        for (int i = 0; i < numberOfProducts; i++) {
//            page = toMainPage();
//            product = page.clickOnRandomCatalogueSection().chooseRandomProduct().addProductToCart().getProduct();
//            addedProducts.addProduct(product);
//        }
//
//        addedProducts.printProductsInCart();
//        FullCartPage cartPage = toFullCartPage();
//
//        int numberOfProductsInCart = cartPage.getProductsInCartNumber();
//        System.out.println(numberOfProductsInCart);
//        Assert.assertEquals(numberOfProductsInCart, addedProducts.getProductsNumber(), "Correct number of products in cart");
//
//
//        for (int i = 1; i <= numberOfProductsInCart; i++) {
//            product = cartPage.getCartProduct(i);
//            Assert.assertTrue(addedProducts.isProductInList(product), "Product in cart" + product.toString() +" corresponds to added product");
//        }
//
//
//        Assert.assertEquals(cartPage.getCartTotalPrice(), addedProducts.getTotalPrice(), "The total price is correct");


    }


}
