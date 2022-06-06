package com.maxfedorov.dockerhub.configs;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:properties/application.properties")
public interface ApplicationConfig extends Config {
    @Key("base.url")
    @DefaultValue("https://hub.docker.com/")
    String baseUrl();
}
