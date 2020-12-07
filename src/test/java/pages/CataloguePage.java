package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static utils.Randomizer.getRandomInt;

public class CataloguePage extends BasePage {

    @FindBy(className = "menu-content")
    protected WebElement catalogueMenu;

    @FindBy(xpath = "//*[contains(@class, 'product_list')]/li")
    protected List<WebElement> productList;


    public CataloguePage() {
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


}
