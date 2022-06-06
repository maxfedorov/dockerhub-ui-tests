package com.maxfedorov.dockerhub.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.By.cssSelector;

public class MainPage extends PageBase {
    public MainPage(WebDriver driver) {
        super(driver);
    }

    private final By landingPage = cssSelector("[class*='styles__homeLandingPage']");

    @Override
    @Step("Check if page is loaded")
    public boolean isLoaded() {
        List<WebElement> fields = driver.findElements(landingPage);
        return fields.size() > 0 && fields.get(0).isDisplayed();
    }

    @Step("Open page")
    public void open() {
        driver.get(appConfig.baseUrl());
    }
}
