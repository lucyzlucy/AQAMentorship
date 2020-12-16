package tests.cartTests.cartFactory;

import business_objects.entities.Product;
import business_objects.entities.ProductsInCart;
import environment.Environment;
import pages.ProductPage;
import tests.BaseTest;
import tests.cartTests.ProductEnum;

import static navigationUtil.PageNavigationUtil.*;
import static utils.Randomizer.getRandomInt;

public class AddingToCartFactoryPreconditions extends BaseTest {
    Product addedProduct;
    ProductsInCart productsInCart;
    ProductEnum product;

    public AddingToCartFactoryPreconditions(ProductEnum product) {
        this.product = product;
    }

    public void addProductToCart(ProductEnum product) {
        productsInCart = new ProductsInCart();
        System.out.println(product);
        switch (product) {
            case singleProduct: {
                addedProduct = addSingleProductToCart(false);
                productsInCart.addProduct(addedProduct);
            }
            break;
            case discountedProduct: {
                addedProduct = addSingleProductToCart(true);
                productsInCart.addProduct(addedProduct);
            }
            break;
//            case multipleArticlesProduct: {
//                addedProduct = addMultipleArticlesOfTheSameProduct();
//                productsInCart.addProduct(addedProduct);
//            }
//            break;
//            case threeDifferentProducts: {
//                addedProduct = addMultipleArticlesOfTheSameProduct();
//                productsInCart.addProduct(addedProduct);
//                waitImplicitly(1000);
//
//                addedProduct = addSingleProductToCart(true);
//                productsInCart.addProduct(addedProduct);
//                waitImplicitly(1000);
//
//                addedProduct = addSingleProductToCart(false);
//                productsInCart.addProduct(addedProduct);
//                waitImplicitly(1000);
//            }
        }
    }

    protected Product addSingleProductToCart(boolean discounted) {
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

    protected Product addMultipleArticlesOfTheSameProduct() {
        ProductPage page = toRegularProductPage2();
        int productQty = getRandomInt(Integer.parseInt(Environment.getEnvProperty("maxProductsInCart")));
        page.setProductQty(productQty);
        Product product = page.getProduct();
        page.addProductToCart();
        return product;
    }
}
