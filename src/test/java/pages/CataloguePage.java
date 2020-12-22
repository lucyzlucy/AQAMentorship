package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static utils.Randomizer.getRandomInt;

@Log4j2
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
        WebElement randomCatalogueSection = children.get(getRandomInt(children.size()));
        String catalogueItemName = randomCatalogueSection.getText();

        randomCatalogueSection.click();

        log.info("Clicked on catalogue section " + catalogueItemName);

        return this;
    }

    public CataloguePage clickOnCatalogueSection(int catalogueSectionOrder) {
        List<WebElement> children = catalogueMenu.findElements(By.xpath("./li"));
        WebElement catalogueSection = children.get(catalogueSectionOrder);
        String catalogueItemName = catalogueSection.getText();

        catalogueSection.click();

        log.info("Clicked on catalogue section " + catalogueItemName);

        return this;
    }

    public ProductCell chooseRandomProduct() {
        int productOrder = getRandomInt(productList.size());
        WebElement productElement = productList.get(productOrder);
        log.info("Clicked on catalogue product number  " + productOrder);


        return new ProductCell(productElement);
    }

//    public ProductCell chooseRandomDiscountedProduct() throws NoDiscountedProductException {
//        WebElement productElement = null;
//        for (int i = 0; i < 1; i++) {
//            clickOnCatalogueSection(i);
//            for (WebElement element : productList) {
//                try {
//                    productElement = element.findElement(By.className("price-percent-reduction"));
//                    return new ProductCell(productElement);
//                } catch (NoSuchElementException ignored) {
//                }
//                int ooo = 0;
//                System.out.println(element.toString() + ooo);
//                ooo++;
//            }
//        }
//        throw new NoDiscountedProductException();
//    }


}
