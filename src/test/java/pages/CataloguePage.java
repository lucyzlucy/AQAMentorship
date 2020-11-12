package pages;

import business_objects.builders.ProductBuilder;
import business_objects.entities.Product;
import driver.DriverWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static data.TestData.CATALOGUE_PAGE_URL;
import static driver.DriverWrapper.isElementLoaded;
import static utils.Randomizer.getRandomIntWithinSize;

public class CataloguePage extends BasePage {

    @FindBy(className = "menu-content")
    protected WebElement catalogueMenu;

    @FindBy(className = "product_list")
    protected WebElement productList;

    @FindBy(id = "layer_cart_product_title")
    protected WebElement productName;

    @FindBy(id = "layer_cart_product_price")
    protected WebElement productPrice;

    @FindBy(id = "layer_cart_product_quantity")
    protected WebElement productQuantity;

    @FindBy(xpath = "//h2[child::i]")
    protected WebElement successMessage;

    @FindBy(xpath = "//div[@class='shopping_cart']/a")
    protected WebElement cartButton;

    public CataloguePage() {
        DriverWrapper.getDriver().get(CATALOGUE_PAGE_URL);
        waitForPageToLoad(productList);
    }

    public CataloguePage clickOnRandomCatalogueSection() {
        List<WebElement> children = catalogueMenu.findElements(By.xpath("./li"));
        children.get(getRandomIntWithinSize(children.size())).click();
        return this;
    }

    public Product chooseRandomProduct() {

        List<WebElement> children = productList.findElements(By.xpath("./li"));
        WebElement productElement = children.get(getRandomIntWithinSize(children.size()));
        String productName = productElement.findElement(By.className("product-name")).getText();

        String productPrice = getVisibleElement(productElement.findElements(By.className("price"))).getText();
        return new ProductBuilder().setName(productName).setPrice(productPrice).setProductElement(productElement).make();
    }

    public CataloguePage addProductToCart(Product product) {
        product.getProductElement().click();
        product.getProductElement().findElement(By.className("ajax_add_to_cart_button")).click();
        return this;
    }

    public String getMessage() {
        isElementLoaded(successMessage);
        return successMessage.getText();
    }

    public String getProductName() {
        return productName.getText();
    }

    public String getProductPrice() {
        return productPrice.getText();
    }

    public int getProductQuantity() {
        return Integer.parseInt(productName.getText());
    }

    public FullCartPage clickOnCart() {
        cartButton.click();
        return new FullCartPage();
    }


}
