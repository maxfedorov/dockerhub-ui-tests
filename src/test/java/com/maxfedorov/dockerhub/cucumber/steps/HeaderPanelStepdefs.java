package com.maxfedorov.dockerhub.cucumber.steps;

import com.maxfedorov.dockerhub.drivers.DriverFactory;
import com.maxfedorov.dockerhub.pages.HeaderPanel;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;

public class HeaderPanelStepdefs {
    WebDriver driver = DriverFactory.getDriver();

    @Given("the user searches image {string}")
    public void theUserSearchesImageAlpine(String image) {
        new HeaderPanel(driver).search(image).waitForLoading();
    }
}