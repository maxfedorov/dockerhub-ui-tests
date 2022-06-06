package com.maxfedorov.dockerhub.drivers;

import org.openqa.selenium.WebDriver;

public class DriverFactory {
    public WebDriver getDriver() {
        String driverProperty = System.getProperty("driver");
        if (driverProperty == null) {
            System.setProperty("driver", "local");
            return new LocalDriver().getDriver();
        }
        switch (driverProperty) {
            case "local":
                return new LocalDriver().getDriver();
            case "selenoid":
                return new SelenoidDriver().getDriver();
            default:
                throw new RuntimeException("Use only driver: local, selenoid");
        }
    }
}
