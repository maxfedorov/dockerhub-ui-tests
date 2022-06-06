package com.maxfedorov.dockerhub.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import static java.time.Duration.ofSeconds;

public class SelenoidDriver extends DriverBase {
    public WebDriver getDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(driverConfig.browserName());
        capabilities.setVersion(driverConfig.browserVersion());
        capabilities.setCapability("enableVNC", driverConfig.enableVNC());
        capabilities.setCapability("enableVideo", driverConfig.enableVideo());
        WebDriver driver = new RemoteWebDriver(driverConfig.selenoidUrl(), capabilities);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(ofSeconds(10));
        return driver;
    }
}
