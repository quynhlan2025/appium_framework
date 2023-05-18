package com.mobile.configs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

public class PropertySettingStoreUtil {
    public static final String PROPERTY_FILE_EXTENSION = ".properties";
    public static final String DF_CHARSET = "UTF-8";
    public static final String SETTING_ROOT_FOLDER_NAME = "settings";

    public static Properties getSettings(String projectFolderLocation, String settingName) throws IOException {
        Properties folder=getSettings(projectFolderLocation + File.separator + SETTING_ROOT_FOLDER_NAME + File.separator
                + settingName + PROPERTY_FILE_EXTENSION);

        return folder;
    }

    private static Properties getSettings(String filePath) throws IOException {
        File settingFile = new File(filePath);
        if(!settingFile.exists()) {
            settingFile.createNewFile();
        }
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(settingFile);
            Properties settings = new Properties();
            settings.load(new InputStreamReader(fis, Charset.forName(DF_CHARSET)));
            return settings;
        } finally {
            if(fis != null) {
                fis.close();
            }
        }
    }
}
