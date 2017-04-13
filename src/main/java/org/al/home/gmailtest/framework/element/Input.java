package org.al.home.gmailtest.framework.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Input extends Element {
    public Input(By by) {
        super(by);
    }

    public void typeValue(String value) {
        WebElement element = getWrappedWebElement(getBy());
        element.clear();
        element.sendKeys(value);
    }
}
