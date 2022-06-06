package com.maxfedorov.dockerhub;

import com.maxfedorov.dockerhub.helpers.Environment;
import com.maxfedorov.dockerhub.pages.ExplorePage;
import com.maxfedorov.dockerhub.pages.HeaderPanel;
import com.maxfedorov.dockerhub.pages.ImagePage;
import com.maxfedorov.dockerhub.pages.MainPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;

import java.util.stream.Stream;

@DisplayName("UI tests")
public class HeaderPanelTest {

    private WebDriver driver;

    @RegisterExtension
    final Environment env = new Environment();

    @BeforeEach
    void setup() {
        driver = env.getDriver();
        new MainPage(driver).open();
    }

    @ParameterizedTest(name = "Search image {0}")
    @Feature("UI tests")
    @Story("Header Panel")
    @MethodSource("provideImages")
    void searchTest(String search, String image, boolean isOfficial) {
        new HeaderPanel(driver).search(search)
                .waitForLoading();
        ExplorePage explorePage = new ExplorePage(driver);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(explorePage.isLoaded())
                .as("Explore page should be loaded")
                .isTrue();
        softAssertions.assertThat(explorePage.getImageCard(image).isOfficialImage()).isEqualTo(isOfficial);
        softAssertions.assertAll();
    }

    @Test
    @Feature("UI tests")
    @Story("Header Panel")
    @DisplayName("Search with selection test result")
    void searchWithSelectionResultTest() {
        HeaderPanel headerPanel = new HeaderPanel(driver);
        headerPanel.fillSearchField("alpine")
                .selectSearchResult("alpine")
                .waitForLoading();

        ImagePage imagePage = new ImagePage(driver);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(imagePage.isLoaded())
                .as("Image page should be loaded")
                .isTrue();
        softAssertions.assertThat(imagePage.getName()).isEqualTo("alpine");
        softAssertions.assertThat(imagePage.getDescription()).contains("github.com/alpinelinux/docker-alpine/issues");
        softAssertions.assertAll();
    }

    private static Stream<Arguments> provideImages() {
        return Stream.of(
                Arguments.of("alpine", "alpine", true),
                Arguments.of("ubuntu", "ubuntu", true),
                Arguments.of("wiremock", "wiremock/wiremock", false)
        );
    }
}
