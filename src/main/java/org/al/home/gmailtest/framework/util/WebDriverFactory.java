package org.al.home.gmailtest.framework.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import static org.al.home.gmailtest.framework.util.DriverTimeouts.IMPLICIT_WAIT;
import static org.al.home.gmailtest.framework.util.DriverTimeouts.PAGE_LOAD;

import java.util.HashMap;

public class WebDriverFactory {
    private static final String PATH_TO_CHROME_DRIVER = "./src/main/resources/driver/chromedriver.exe";
    private static final String DOWNLOADS_PATH = "D:\\downloads";
    private static volatile WebDriver driver;

    private WebDriverFactory() {
    }

    public static WebDriver getDriver() {
        driver = getChromeDriver();
        return driver;
    }

    private static WebDriver getChromeDriver() {
        String chromeProperty = "webdriver.chrome.driver";
        String defaultContentSettingsPopups = "profile.default_content_settings.popups";
        String downloadDefauldDirectory = "download.default_directory";
        String prefs = "prefs";

        System.setProperty(chromeProperty, PATH_TO_CHROME_DRIVER);

        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put(defaultContentSettingsPopups, 0);
        chromePrefs.put(downloadDefauldDirectory, DOWNLOADS_PATH);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption(prefs, chromePrefs);

        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(ChromeOptions.CAPABILITY, options);
        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        driver = new ChromeDriver(cap);
        configureDriver();
        return driver;
    }

    private static void configureDriver() {
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT.getValue(), IMPLICIT_WAIT.getUnit());
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD.getValue(), PAGE_LOAD.getUnit());
        driver.manage().window().maximize();
    }


}
