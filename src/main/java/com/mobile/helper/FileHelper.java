package com.mobile.helper;

import java.io.File;

public class FileHelper {

    public static String getCorrectJsonFilePath(String jsonFilePath) {
        String correctXlFilePath = System.getProperty("user.dir") + File.separator + jsonFilePath;
        return correctXlFilePath;
    }
}
