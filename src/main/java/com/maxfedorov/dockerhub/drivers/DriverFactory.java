package com.maxfedorov.dockerhub.drivers;

import org.openqa.selenium.WebDriver;

public class DriverFactory {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() != null) {
            return driver.get();
        }
        if (System.getProperty("driver") == null) {
            System.setProperty("driver", "local");
        }
        switch (System.getProperty("driver")) {
            case "local":
                driver.set(new LocalDriver().getDriver());
                break;
            case "selenoid":
                driver.set(new SelenoidDriver().getDriver());
                break;
            default:
                throw new RuntimeException("Use only driver: local, selenoid");
        }
        return driver.get();
    }

    public static void closeBrowser() {
        driver.get().quit();
        driver.remove();
    }
}
