package org.al.home.gmailtest.framework.element;

import org.al.home.gmailtest.framework.browser.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Element {

    private By by;

    public Element(By by) {
        this.by = by;
    }

    public By getBy() {
        return by;
    }

    public String getText() {
        return getWrappedWebElement(by).getText();
    }

    public WebElement getWrappedWebElement(By by) {
        return Browser.getBrowser().findElement(by);
    }

    public boolean isPresent() {
        return Browser.getBrowser().isElementPresent(by);
    }

    public boolean isVisible() {
        return Browser.getBrowser().isElementVisible(by);
    }

    public void waitForAppear() {
        Browser.getBrowser().waitForElementIsVisible(by);
    }
}
