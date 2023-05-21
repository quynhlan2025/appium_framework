package com.mobile.configs;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class ConfigSettings {
    private static final String SETTING_FILE_NAME = "mobile_settings";
    private static final String APP_FOLDER = System.getProperty("user.dir") + File.separator + "apps";
    private static final String APP_NAME = "app.name";
    private static final String TIMEOUT = "timeout";

    private Properties configProperties;

    public ConfigSettings(String projectDirLocation) {
        try {
            setConfigProperties(PropertySettingStoreUtil.getSettings(projectDirLocation, SETTING_FILE_NAME));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setConfigProperties(Properties configProperties) {
        this.configProperties = configProperties;
    }

    public String getAppPath() {
        return APP_FOLDER + File.separator + this.configProperties.getProperty(APP_NAME);
    }

    public String getTimeout() {
        return this.configProperties.getProperty(TIMEOUT);
    }

}
