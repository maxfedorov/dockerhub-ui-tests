package com.maxfedorov.dockerhub.drivers;

import com.maxfedorov.dockerhub.configs.DriverConfig;
import org.aeonbits.owner.ConfigFactory;

public class DriverBase {
    public static DriverConfig driverConfig = ConfigFactory.create(DriverConfig.class);
}
