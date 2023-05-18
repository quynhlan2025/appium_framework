package com.mobile.configs;

import java.io.IOException;
import java.util.Properties;

public class DeviceSettings {
    private static Properties configProperties  ;

    private final static String PLATFORM_NAME = "platform.name";
    private final static String PLATFORM_VERSION = "platform.version";
    private final static String DEVICE_NAME = "device.name";
    private final static String UUID =  "uuid";

    public DeviceSettings(String projectDirLocation, String deviceName) {
        try {
            setConfigProperties(PropertySettingStoreUtil.getSettings(projectDirLocation, deviceName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setConfigProperties(Properties configProperties) {
        this.configProperties = configProperties;
    }

    public static String getPlatformName() {
        return configProperties.getProperty(PLATFORM_NAME);
    }

    public static String getPlatformVersion() {
        return configProperties.getProperty(PLATFORM_VERSION);
    }

    public static String getDeviceName() {
        return configProperties.getProperty(DEVICE_NAME);
    }

    public static String getUuid() {
        return configProperties.getProperty(UUID);
    }
}
