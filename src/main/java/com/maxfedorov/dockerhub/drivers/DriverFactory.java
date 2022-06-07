package com.maxfedorov.dockerhub.drivers;

import org.openqa.selenium.WebDriver;

public class DriverFactory {
    private static WebDriver driver = null;

    public static synchronized WebDriver getDriver() {
        if (driver != null) {
            return driver;
        }
        if (System.getProperty("driver") == null) {
            System.setProperty("driver", "local");
        }
        switch (System.getProperty("driver")) {
            case "local":
                driver = new LocalDriver().getDriver();
                break;
            case "selenoid":
                driver = new SelenoidDriver().getDriver();
                break;
            default:
                throw new RuntimeException("Use only driver: local, selenoid");
        }
        return driver;
    }

    public static void closeBrowser() {
        driver.quit();
        driver = null;
    }
}
