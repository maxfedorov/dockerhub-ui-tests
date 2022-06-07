package com.maxfedorov.dockerhub.cucumber.steps;

import com.maxfedorov.dockerhub.drivers.DriverFactory;
import com.maxfedorov.dockerhub.pages.ExplorePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class ExplorePageStepdefs {
    WebDriver driver = DriverFactory.getDriver();
    ExplorePage page = new ExplorePage(driver);

    @Given("the user opens explore page")
    public void theUserOpensExplorePageForImageAlpine() {
        page.open("");
    }

    @When("the user clicks image {string}")
    public void theUserClicksImageAlpine(String image) {
        page.openImage(image).waitForLoading();
    }
}