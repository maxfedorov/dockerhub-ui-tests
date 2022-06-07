package com.maxfedorov.dockerhub.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.lang.String.format;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.Keys.ENTER;

public class HeaderPanel extends PageBase {
    public HeaderPanel(WebDriver driver) {
        super(driver);
    }

    @Override
    @Step("Check if page is loaded")
    public boolean isLoaded() {
        List<WebElement> fields = driver.findElements(searchField);
        return fields.size() > 0 && fields.get(0).isDisplayed();
    }

    private final By searchField = cssSelector("input[name='q']");
    private final String searchResult = ".//*[contains(@class, 'autocompleteMenu')]//span[@data-testid='autocomplete-result']/parent::span[normalize-space()='%s']";

    @Step("Type {search} in search field")
    public HeaderPanel fillSearchField(String search) {
        WebElement field = driver.findElement(searchField);
        field.clear();
        field.sendKeys(search);
        return this;
    }

    @Step("Select search result {search}")
    public ImagePage selectSearchResult(String search) {
        driver.findElement(xpath(format(searchResult, search))).click();
        return new ImagePage(driver);
    }

    @Step("Search {search}")
    public ExplorePage search(String search) {
        WebElement field = driver.findElement(searchField);
        field.clear();
        field.sendKeys(search);
        field.sendKeys(ENTER);
        return new ExplorePage(driver);
    }
}
