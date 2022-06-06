package com.maxfedorov.dockerhub.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Objects;

import static java.time.Duration.ofSeconds;

public class LocalDriver extends DriverBase {
    public WebDriver getDriver() {
        String driverPath = Objects.requireNonNull(getClass().getResource(driverConfig.driverPath())).getPath();
        WebDriver driver;
        switch (driverConfig.browserName()) {
            case "chrome": {
                System.setProperty("webdriver.chrome.driver", driverPath);
                driver = new ChromeDriver();
                break;
            }
            case "firefox": {
                System.setProperty("webdriver.gecko.driver", driverPath);
                driver = new FirefoxDriver();
                break;
            }
            default:
                throw new RuntimeException("Supported browsers: chrome, firefox");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(ofSeconds(10));
        return driver;
    }
}
