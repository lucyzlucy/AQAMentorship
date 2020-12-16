package tests.cartTests.cartFactoryMultithreaded;

import org.testng.annotations.Factory;
import tests.cartTests.ProductEnum;

public class AddingToCartFactoryMult {
    @Factory()
    public Object[] factoryMethod() {
        return new Object[]{
                new AddingToCartFactoryTestsMult(ProductEnum.singleProduct),
                new AddingToCartFactoryTestsMult(ProductEnum.discountedProduct)
//                new AddingToCartFactoryTests(ProductEnum.multipleArticlesProduct),
//                new AddingToCartFactoryTests(ProductEnum.threeDifferentProducts)
        };
    }
}
