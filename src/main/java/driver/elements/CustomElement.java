package driver.elements;

import org.openqa.selenium.WebElement;

public abstract class CustomElement {
    protected final WebElement base;

    public CustomElement(final WebElement base) {
        this.base = base;
    }
}
