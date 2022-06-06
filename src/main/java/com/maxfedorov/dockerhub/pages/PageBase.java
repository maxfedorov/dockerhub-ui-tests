package com.maxfedorov.dockerhub.pages;

import com.maxfedorov.dockerhub.configs.ApplicationConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class PageBase {
    protected final ApplicationConfig appConfig = ConfigFactory.create(ApplicationConfig.class);

    public PageBase(WebDriver driver) {
        this.driver = driver;
    }

    protected WebDriver driver;

    protected WebDriverWait webDriverWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public abstract boolean isLoaded();

    public void waitForLoading() {
        webDriverWait().until(driver -> isLoaded());
    }
}
