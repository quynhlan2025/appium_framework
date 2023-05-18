package com.mobile.test;

import com.mobile.configs.DeviceSettings;
import com.mobile.keywords.MobileKeywords;

import java.io.File;
import java.net.MalformedURLException;

public class HomeTest {
    public static String APP_PATH_ANDROID=System.getProperty("user.dir")+ File.separator +"apps"+ File.separator+"ApiDemos-debug.apk";
    public static String APP_PATH_IOS=System.getProperty("user.dir")+ File.separator +"apps"+ File.separator+"SwagLabsMobileApp.app";

    public static void main(String[] arg) throws MalformedURLException {
         MobileKeywords mk=new MobileKeywords();
         new DeviceSettings(System.getProperty("user.dir"),"emulator-5554");
        mk.startApplication(APP_PATH_ANDROID);
       // mk.startApplication("Android",APP_PATH_IOS);
    }
}
