package tests;

import business_objects.entities.Product;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;
import utils.CustomAssert;

import static environment.Environment.getEnvProperty;
import static navigationUtil.PageNavigationUtil.toMainPage;

public class CatalogueTests extends BaseTest {

    @Test
    public void verifyCanNavigateToCartPageFromCatalogue() {
        CataloguePage page = toMainPage();

        page.clickOnCart();

        Assert.assertEquals(page.getUrl(), getEnvProperty("cartPageUrl"));
    }

    @Test
    public void verifyCanNavigateToProductPageFromCatalogue() {
        ProductPage page = toMainPage().clickOnRandomCatalogueSection().chooseRandomProduct().clickOnProductName();
        Assert.assertTrue(page.getUrl().contains(getEnvProperty("productPageUrl")));
    }

    @Test
    public void verifyProductDetailsOnProductPage() {
        ProductCell chosenProductCell = toMainPage().clickOnRandomCatalogueSection().chooseRandomProduct();
        Product product = chosenProductCell.getProduct();
        ProductPage prodPage = chosenProductCell.clickOnProductName();

        CustomAssert customAssert = new CustomAssert();
        Assert.assertEquals(prodPage.getProductName(), product.getName(), "Correct name is shown");
        Assert.assertEquals(prodPage.getProductPrice(), product.getPrice(), "Correct price is shown");
        customAssert.assertAll();
    }

    @Test
    public void verifyProductAddedPopup() {
        ProductCell page = toMainPage().clickOnRandomCatalogueSection().chooseRandomProduct();
        Product product = page.getProduct();
        page.addProductToCart();
        ProductAddedPopup popup = new ProductAddedPopup();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(popup.getMessage().contains(getEnvProperty("successAddedToCartMessage")), "Confirmation message is shown");
        softAssert.assertEquals(popup.getProductName(), product.getName(), "Correct name is shown");
        softAssert.assertEquals(popup.getProductPrice(), product.getPrice(), "Correct price is shown");
        softAssert.assertAll();
    }


}
