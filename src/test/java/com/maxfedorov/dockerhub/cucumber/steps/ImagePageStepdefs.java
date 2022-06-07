package com.maxfedorov.dockerhub.cucumber.steps;

import com.maxfedorov.dockerhub.drivers.DriverFactory;
import com.maxfedorov.dockerhub.pages.ImagePage;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class ImagePageStepdefs {
    WebDriver driver = new DriverFactory().getDriver();
    ImagePage page = new ImagePage(driver);

    @Then("the user verifies that image page is open")
    public void theUserVerifiesThatImagePageIsOpen() {
        assertThat(page.isLoaded())
                .as("Image page should be loaded")
                .isTrue();
    }

    @Then("the user verifies that image name is {string}")
    public void theUserVerifiesThatImageNameIsAlpine(String image) {
        assertThat(page.getName()).isEqualTo(image);
    }

    @Then("the user verifies that description contains text {string}")
    public void theUserVerifiesThatDescriptionContainsTextGithubComAlpinelinuxDockerAlpineIssues(String desc) {
        assertThat(page.getDescription()).contains(desc);
    }
}