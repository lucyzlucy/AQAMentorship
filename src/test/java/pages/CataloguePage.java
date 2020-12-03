package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static driver.DriverWrapper.isElementLoaded;
import static utils.Randomizer.getRandomInt;
import static utils.StripNonDigitsUtil.stripNonDigits;

public class CataloguePage extends BasePage {

    @FindBy(className = "menu-content")
    protected WebElement catalogueMenu;

    @FindBy(xpath = "//*[contains(@class, 'product_list')]/li")
    protected List<WebElement> productList;

    @FindBy(id = "layer_cart_product_title")
    protected WebElement productName;

    @FindBy(id = "layer_cart_product_price")
    protected WebElement productPrice;

    @FindBy(xpath = "//h2[child::i]")
    protected WebElement successMessage;

    @FindBy(xpath = "//div[@class='shopping_cart']/a")
    protected WebElement cartButton;

    public CataloguePage() {
//        navigateToPage(CATALOGUE_PAGE_URL);
        waitForPageToLoad(catalogueMenu);
    }

    public CataloguePage clickOnRandomCatalogueSection() {
        List<WebElement> children = catalogueMenu.findElements(By.xpath("./li"));
        children.get(getRandomInt(children.size())).click();
        return this;
    }

    public ProductCell chooseRandomProduct() {
        WebElement productElement = productList.get(getRandomInt(productList.size()));
        return new ProductCell(productElement);
    }

    public String getMessage() {
        isElementLoaded(successMessage);
        return successMessage.getText();
    }

    public String getProductName() {
        return productName.getText();
    }

    public double getProductPrice() {
        return Double.parseDouble(stripNonDigits(productPrice.getText()));
    }

    public FullCartPage clickOnCart() {
        cartButton.click();
        return new FullCartPage();
    }


}
