package com.maxfedorov.dockerhub;

import com.google.common.collect.Lists;
import com.maxfedorov.dockerhub.helpers.Environment;
import com.maxfedorov.dockerhub.pages.ExplorePage;
import com.maxfedorov.dockerhub.pages.ImagePage;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;

@DisplayName("UI tests")
public class ExplorePageTest {

    private WebDriver driver;

    @RegisterExtension
    final Environment env = new Environment();

    @BeforeEach
    void setup() {
        driver = env.getDriver();
        new ExplorePage(driver).open("alpine");
    }

    @Test
    @Feature("UI tests")
    @Story("Explore Page")
    @DisplayName("Open image")
    void openImageTest() {
        ExplorePage page = new ExplorePage(driver);
        page.openImage("alpine").waitForLoading();
        ImagePage imagePage = new ImagePage(driver);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(imagePage.isLoaded())
                .as("Image page should be loaded")
                .isTrue();
        softAssertions.assertThat(imagePage.getName()).isEqualTo("alpine");
        softAssertions.assertThat(imagePage.getDescription()).contains("github.com/alpinelinux/docker-alpine/issues");
        softAssertions.assertAll();
    }

    @Test
    @Feature("UI tests")
    @Story("Explore Page")
    @DisplayName("Click image label")
    void clickImageLabelTest() {
        ExplorePage page = new ExplorePage(driver);
        page.getImageCard("alpine")
                .clickLabel("Linux");
        Assertions.assertThat(page.getSelectedFilters()).isEqualTo(Lists.newArrayList("Images", "Linux"));
    }

    @Test
    @Feature("UI tests")
    @Story("Explore Page")
    @DisplayName("Select filter")
    void selectFilterTest() {
        ExplorePage page = new ExplorePage(driver);
        page.selectFilter("Operating Systems", "Windows");
        Assertions.assertThat(page.getImageByNumber(1).isOfficialImage())
                .as("Official image should not be found")
                .isFalse();
    }

    @Test
    @Feature("UI tests")
    @Story("Explore Page")
    @DisplayName("Sort images")
    void sortImagesTest() {
        ExplorePage page = new ExplorePage(driver);
        page.sortResults("Recently Updated");
        Assertions.assertThat(page.getImageByNumber(1).getUpdated()).endsWith("minutes ago");
    }
}
