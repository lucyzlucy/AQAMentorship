package navigationUtil;

import driver.DriverWrapperThreadSafe;
import pages2.pages.*;

import static environment.Environment.getEnvProperty;

public class PageNavigationUtil2 {
    public static CataloguePage toMainPage() {
        DriverWrapperThreadSafe.navigateToPage(getEnvProperty("cataloguePageUrl"));
        return new CataloguePage();
    }

    public static SignInPage toSignInPage() {
        DriverWrapperThreadSafe.navigateToPage(getEnvProperty("signInPageUrl"));
        return new SignInPage();
    }

    public static EmptyCartPage toEmptyCartPage() {
        DriverWrapperThreadSafe.navigateToPage(getEnvProperty("cartPageUrl"));
        return new EmptyCartPage();

    }

    public static FullCartPage toFullCartPage() {
        DriverWrapperThreadSafe.navigateToPage(getEnvProperty("cartPageUrl"));
        return new FullCartPage();
    }

    public static ProductPage toDiscountedProductPage() {
        DriverWrapperThreadSafe.navigateToPage(getEnvProperty("discountedProductPageUrl"));
        return new ProductPage();
    }

    public static ProductPage toRegularProductPage() {
        DriverWrapperThreadSafe.navigateToPage(getEnvProperty("regularProductPageUrl"));
        return new ProductPage();
    }

    public static ProductPage toRegularProductPage2() {
        DriverWrapperThreadSafe.navigateToPage(getEnvProperty("regularProductPageUrl2"));
        return new ProductPage();
    }
}
