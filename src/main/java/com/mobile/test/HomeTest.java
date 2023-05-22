package com.mobile.test;

import com.mobile.configs.ConfigSettings;
import com.mobile.configs.DeviceSettings;
import com.mobile.keywords.MobileKeywords;

import java.io.File;
import java.net.MalformedURLException;

public class HomeTest {
    public static String APP_PATH_ANDROID = System.getProperty("user.dir") + File.separator + "apps" + File.separator + "ApiDemos-debug.apk";
    public static String APP_PATH_IOS = System.getProperty("user.dir") + File.separator + "apps" + File.separator + "SwagLabsMobileApp.app";


    private static String LBL_APP = "//android.widget.TextView[@content-desc=\"App\"]";
    private static String LBL_SEARCH = "//android.widget.TextView[@content-desc=\"Search\"]";
    private static String LBL_GRAPHICS = "//android.widget.TextView[@content-desc=\"Graphics\"]";
    private static String LBL_VERTICES = "//android.widget.TextView[@content-desc=\"Vertices\"]";
    public static void main(String[] arg) throws MalformedURLException {
        ConfigSettings configSettings = new ConfigSettings(System.getProperty("user.dir"));
        MobileKeywords mk = new MobileKeywords();
        MobileKeywords mobileAction = new MobileKeywords();
        new DeviceSettings(System.getProperty("user.dir"), "emulator-5554");
        mk.startApplication(configSettings.getAppPath());
        mobileAction.tap(LBL_GRAPHICS);
        mobileAction.delayInSeconds(5);
        mobileAction.scrollDown();
        mobileAction.delayInSeconds(5);
        mobileAction.scrollDown(LBL_VERTICES);
        mobileAction.delayInSeconds(5);
        mobileAction.scrollToText("Compass");
        mobileAction.delayInSeconds(5);
        mobileAction.closeApplication();
    }
}
