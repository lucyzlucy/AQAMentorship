package driver.elements;

import org.openqa.selenium.WebElement;

public class WebTable {
    WebElement table;

    // accept the web element (table) as parameter to then constructor
    public WebTable(WebElement wtable) {
        this.table = wtable;
    }
}
