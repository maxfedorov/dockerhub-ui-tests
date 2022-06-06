package com.maxfedorov.dockerhub.configs;

import org.aeonbits.owner.Config;

import java.net.URL;

@Config.Sources("classpath:properties/${driver}.properties")
public interface DriverConfig extends Config {

    @Key("enable.vnc")
    @DefaultValue("true")
    boolean enableVNC();

    @Key("enable.video")
    @DefaultValue("true")
    boolean enableVideo();

    @Key("selenoid.url")
    URL selenoidUrl();

    @Key("browser.name")
    @DefaultValue("chrome")
    String browserName();

    @Key("browser.version")
    @DefaultValue("91.0")
    String browserVersion();

    @Key("video.url")
    String videoUrl();

    @Key("driverPath")
    @DefaultValue("/drivers/chromedriver")
    String driverPath();
}
