package com.maxfedorov.dockerhub.helpers;

import com.maxfedorov.dockerhub.drivers.DriverFactory;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Environment implements BeforeEachCallback, AfterEachCallback {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        driver = DriverFactory.getDriver();
    }

    @Override
    public void afterEach(ExtensionContext context) {
        if (context.getExecutionException().isPresent()) {
            new AttachmentHelper(driver).screenshotAs("After test screenshot");
            if (System.getProperty("driver").equals("selenoid")) {
                new AttachmentHelper(driver).attachVideo(((RemoteWebDriver) driver).getSessionId().toString());
            }
        }
        DriverFactory.closeBrowser();
    }
}
