package com.maxfedorov.dockerhub.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.xpath;

public class ExplorePage extends PageBase {
    public ExplorePage(WebDriver driver) {
        super(driver);
    }

    private final By card = cssSelector("[data-testid='imageSearchResult']");
    private final By filtersPanel = cssSelector("[class*='styles__filters']");
    private final By sortingCombobox = cssSelector("[class*='styles__searchHeader'] .Select-arrow-zone");
    private final By filterCheckbox = xpath(".//div[contains(@class, 'styles__filters')]//input[@type='checkbox']");
    private final String sortingComboboxItem = ".//*[text()='%s']";
    private final String filterCheckboxByName = ".//*[contains(@class, 'MuiTypography-root') and text()='%s']" +
            "/following-sibling::div//label[text()='%s']/ancestor::div[contains(@class, 'checkbox')]";
    private final String url = appConfig.baseUrl() + "search?q=%s";

    @Override
    @Step("Check if page is loaded")
    public boolean isLoaded() {
        List<WebElement> fields = driver.findElements(filtersPanel);
        return fields.size() > 0 && fields.get(0).isDisplayed();
    }

    @Step("Open page for image {image}")
    public void open(String image) {
        driver.get(format(url, image));
    }

    @Step("Select filter {name}:{value}")
    public ExplorePage selectFilter(String name, String value) {
        driver.findElement(xpath(format(filterCheckboxByName, name, value))).click();
        webDriverWait().until(driver -> driver.findElement(xpath(format(filterCheckboxByName, name, value)))
                .findElement(cssSelector("input")).isSelected());
        return this;
    }

    @Step("Open Sort results {filter}")
    public ExplorePage sortResults(String filter) {
        driver.findElement(sortingCombobox).click();
        driver.findElement(xpath(format(sortingComboboxItem, filter))).click();
        return this;
    }

    @Step("Open image {name}")
    public ImagePage openImage(String name) {
        getImageCard(name).element().click();
        return new ImagePage(driver);
    }

    @Step("Get selected filters")
    public List<String> getSelectedFilters() {
        return driver.findElements(filterCheckbox)
                .stream()
                .filter(WebElement::isSelected)
                .map(checkbox -> checkbox.findElement(xpath("./parent::div//label")).getText())
                .collect(Collectors.toList());
    }

    @Step("Get image card with name {name}")
    public ImageCard getImageCard(String name) {
        return new ImageCard(name);
    }

    @Step("Get image card with number {number}")
    public ImageCard getImageByNumber(int number) {
        return new ImageCard(number);
    }

    @Step("Get images list")
    public List<WebElement> getImages() {
        return driver.findElements(card);
    }

    public class ImageCard {
        private final WebElement element;
        private final By nameElement = cssSelector("[class*='styles__name']");
        private final By updated = cssSelector("span.tertiaryContent");
        private final String badgeByText = ".//div[@data-testid='productBadge']//*[text()='%s']";
        private final String labelByText = ".//*[@data-testid='productChip' and text()='%s']";
        private final String cardByName = "//*[contains(@class, 'styles__name') and text()='%s']/ancestor::*[@data-testid='imageSearchResult']";

        public ImageCard(String name) {
            this.element = driver.findElement(xpath(format(cardByName, name)));
        }

        public ImageCard(int number) {
            this.element = driver.findElements(card).get(number - 1);
        }

        @Step("Get image name")
        public String getName() {
            return element().findElement(nameElement).getText();
        }

        @Step("Get image updated")
        public String getUpdated() {
            return element().findElement(updated).getText();
        }

        @Step("Check if image is official")
        public boolean isOfficialImage() {
            List<WebElement> officialImageBadges = element().findElements(xpath(format(badgeByText, "Docker Official Image")));
            return officialImageBadges.size() > 0 && officialImageBadges.get(0).isDisplayed();
        }

        @Step("Click image label")
        public ExplorePage clickLabel(String label) {
            element().findElement(xpath(format(labelByText, label))).click();
            return new ExplorePage(driver);

        }

        public WebElement element() {
            return element;
        }
    }
}
