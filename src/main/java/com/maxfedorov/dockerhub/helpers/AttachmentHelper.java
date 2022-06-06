package com.maxfedorov.dockerhub.helpers;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static com.maxfedorov.dockerhub.drivers.DriverBase.driverConfig;

public class AttachmentHelper {
    public AttachmentHelper(WebDriver driver) {
        this.driver = driver;
    }

    private WebDriver driver;

    @Attachment(value = "{attachName}", type = "text/plain")
    public static String attachText(String attachName, String message) {
        return message;
    }

    @Attachment(value = "{attachName}", type = "image/png")
    public byte[] screenshotAs(String attachName) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Video", type = "text/html", fileExtension = ".html")
    public String attachVideo(String sessionId) {
        String videoUrl = getSelenoidVideoUrl(sessionId);
        return "<html><body><video width='100%' height='100%' controls autoplay><source src='"
                + videoUrl + "' type='video/mp4'></video></body></html>";
    }

    private String getSelenoidVideoUrl(String sessionId) {
        try {
            return new URL(driverConfig.videoUrl() + sessionId + ".mp4") + "";
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
