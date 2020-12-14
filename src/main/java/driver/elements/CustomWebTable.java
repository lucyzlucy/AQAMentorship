package driver.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CustomWebTable extends CustomElement {
    public CustomWebTable(final WebElement base) {
        super(base);
    }

    public int getRowCount() {
        int noOfRows = base.findElements(By.xpath("./tbody/tr")).size();
        return noOfRows;
    }

    public String getCellData(int rowNumber, int columnNumber) {
        String cellData = base.findElement(By.xpath("./tbody//tr[" + rowNumber + "]/td[" + columnNumber + "]")).getText();
        return cellData;
    }

    public String getCellData(int rowNumber, int columnNumber, String xpath) {
        String cellData = base.findElement(By.xpath("./tbody/tr[" + rowNumber + "]/td[" + columnNumber + "]" + xpath)).getText();
        return cellData;
    }

    public String getCellValue(int rowNumber, int columnNumber, String xpath) {
        String cellData = base.findElement(By.xpath("./tbody/tr[" + rowNumber + "]/td[" + columnNumber + "]" + xpath)).getAttribute("value");
        return cellData;
    }


    public WebElement getCellElement(int rowNumber, int columnNumber, String xpath) {
        WebElement element = base.findElement(By.xpath("./tbody/tr[" + rowNumber + "]/td[" + columnNumber + "]" + xpath));
        return element;
    }
}
