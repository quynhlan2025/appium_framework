package com.mobile.keywords;

import com.mobile.configs.DeviceSettings;
import com.mobile.helper.LogHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;

public class MobileKeywords {
    private static String APPIUM_SERVER_URL="http://127.0.0.1:4723/wd/hub";
    private static AppiumDriver<?> driver;
    private static Logger logger = LogHelper.getLogger();

    public static void startApplication(String appPath) throws MalformedURLException {
        logger.debug(MessageFormat.format("Starting application ''{0}'' on ''{1}''", appPath, DeviceSettings.getPlatformName()));
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("platformName", DeviceSettings.getPlatformName());
        dc.setCapability("platformVersion", DeviceSettings.getPlatformVersion());
        dc.setCapability("app", appPath);
        dc.setCapability("deviceName",  DeviceSettings.getDeviceName());

        dc.setCapability("uuid", DeviceSettings.getUuid());

        if(DeviceSettings.getPlatformName().equals("Android")){
            driver = new AndroidDriver(new URL(APPIUM_SERVER_URL), dc);
        }else{
            dc.setCapability("automationName", "UiAutomator2");
            appPath="SwagLabsMobileApp.app";
            dc.setCapability("app", appPath);
            driver = new IOSDriver<IOSElement>(new URL(APPIUM_SERVER_URL), dc);
        }

    }
    public  void tap(String locator){
        WebElement we = driver.findElement(By.xpath(locator));

        TouchAction<?> tap = new TouchAction<>(driver)
                .tap(TapOptions.tapOptions().withElement(ElementOption.element(we, 1, 1)));
        tap.release().perform();
    }
}
