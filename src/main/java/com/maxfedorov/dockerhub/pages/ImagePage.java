package com.maxfedorov.dockerhub.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.By.cssSelector;

public class ImagePage extends PageBase {
    public ImagePage(WebDriver driver) {
        super(driver);
    }

    private final By imageName = cssSelector("[data-testid=productHeader_productName]");
    private final By descriptionPanel = cssSelector("div.dMarkdown > [class*='styles__productDescription']");

    @Override
    @Step("Check if page is loaded")
    public boolean isLoaded() {
        List<WebElement> fields = driver.findElements(imageName);
        return fields.size() > 0 && fields.get(0).isDisplayed();
    }

    @Step("Get image name")
    public String getName() {
        return driver.findElement(imageName).getText();
    }

    @Step("Get image description")
    public String getDescription() {
        return driver.findElement(descriptionPanel).getText();
    }
}
