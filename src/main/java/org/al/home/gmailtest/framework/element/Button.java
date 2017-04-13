package org.al.home.gmailtest.framework.element;

import org.openqa.selenium.By;

public class Button extends Element {

    public Button(By by) {
        super(by);
    }

    public void click() {
        getWrappedWebElement(getBy()).click();
    }

    public void waitAndClick() {
        waitForAppear();
        click();
    }
}
