
package com.maxfedorov.dockerhub.cucumber.hooks;

import com.maxfedorov.dockerhub.drivers.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

public class BrowserHook {

    private final WebDriver driver = DriverFactory.getDriver();

    @After
    public void closeWebDriver(Scenario scenario) {
        DriverFactory.closeBrowser();
    }
}