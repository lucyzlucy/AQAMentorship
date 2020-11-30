package navigationUtil;

import driver.DriverWrapper;
import pages.*;

import static environment.Environment.getEnvProperty;

public class PageNavigationUtil {
    public static CataloguePage toMainPage() {
        DriverWrapper.navigateToPage(getEnvProperty("cataloguePageUrl"));
        return new CataloguePage();
    }

    public static SignInPage toSignInPage() {
        DriverWrapper.navigateToPage(getEnvProperty("signInPageUrl"));
        return new SignInPage();
    }

    public static EmptyCartPage toEmptyCartPage() {
        DriverWrapper.navigateToPage(getEnvProperty("cartPageUrl"));
        return new EmptyCartPage();

    }

    public static FullCartPage toFullCartPage() {
        DriverWrapper.navigateToPage(getEnvProperty("cartPageUrl"));
        return new FullCartPage();
    }
}
