package org.al.home.gmailtest.framework.browser;

import org.al.home.gmailtest.framework.util.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class Browser implements WrapsDriver {
    private WebDriver driver;
    private static Browser instance;
    public static final int ELEMENT_WAIT_TIMEOUT_SECONDS = 10;


    private Browser() {
        this.driver = WebDriverFactory.getDriver();
    }

    public static synchronized Browser getBrowser() {
        if (instance == null) {
            instance = new Browser();
        }
        return instance;
    }

    public void close() {
        try {
            if (getWrappedDriver() != null) {
                getWrappedDriver().quit();
            }
        } finally {
            instance = null;
        }
    }

    public void open(String url) {
        driver.get(url);
    }

    public WebElement findElement(By by) {
        return driver.findElement(by);
    }

    public List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    public boolean isElementPresent(By by) {
        return (findElements(by).size() > 0);
    }

    public boolean isElementVisible(By by) {
        if (!isElementPresent(by)) {
            return false;
        }
        return findElement(by).isDisplayed();
    }

    public void waitForElementIsPresent(By by) {
        WebDriverWait wait = new WebDriverWait(driver, ELEMENT_WAIT_TIMEOUT_SECONDS);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public void waitForElementIsVisible(By by) {
        WebDriverWait wait = new WebDriverWait(driver, ELEMENT_WAIT_TIMEOUT_SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void waitForElementIsClickable(By by) {
        WebDriverWait wait = new WebDriverWait(driver, ELEMENT_WAIT_TIMEOUT_SECONDS);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    @Override
    public WebDriver getWrappedDriver() {
        return driver;
    }
}
