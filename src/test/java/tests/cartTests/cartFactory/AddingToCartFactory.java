package tests.cartTests.cartFactory;

import org.testng.annotations.Factory;
import tests.cartTests.ProductEnum;

public class AddingToCartFactory {
    @Factory()
    public Object[] factoryMethod() {
        return new Object[]{
                new AddingToCartFactoryTests(ProductEnum.singleProduct),
                new AddingToCartFactoryTests(ProductEnum.discountedProduct)
//                new AddingToCartFactoryTests(ProductEnum.multipleArticlesProduct),
//                new AddingToCartFactoryTests(ProductEnum.threeDifferentProducts)
        };
    }
}
