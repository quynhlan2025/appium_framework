package com.mobile.drivers;

import com.mobile.configs.DeviceSettings;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class IosDriverManager  extends DriverManager{

    private static String APPIUM_SERVER_URL="http://127.0.0.1:4723/wd/hub";
    @Override
    protected void createDriver(String appPath)  {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("platformName", DeviceSettings.getPlatformName());
        dc.setCapability("platformVersion", DeviceSettings.getPlatformVersion());
        dc.setCapability("app", appPath);
        dc.setCapability("deviceName",  DeviceSettings.getDeviceName());

        dc.setCapability("uuid", DeviceSettings.getUuid());

        if(DeviceSettings.getPlatformName().equals("Android")){
            try {
                driver = new IOSDriver<>(new URL(APPIUM_SERVER_URL), dc);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
