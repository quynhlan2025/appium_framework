package com.mobile.keywords;

import com.mobile.configs.DeviceSettings;
import com.mobile.drivers.DriverManager;
import com.mobile.drivers.DriverManagerFactory;
import com.mobile.drivers.DriverType;
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
import java.util.Locale;

public class MobileKeywords {
    private static String APPIUM_SERVER_URL="http://127.0.0.1:4723/wd/hub";
    private static AppiumDriver<?> driver;
    private static Logger logger = LogHelper.getLogger();
    private static DriverManager driverManager;

    public static void startApplication(String appPath) throws MalformedURLException {
        logger.debug(MessageFormat.format("Starting application ''{0}'' on ''{1}''", appPath, DeviceSettings.getPlatformName()));
        new DeviceSettings(System.getProperty("user.dir"), "emulator-5554");

        driverManager= DriverManagerFactory.getManager(DriverType.valueOf(DeviceSettings.getPlatformName().toUpperCase()));
        driver=driverManager.getDriver(appPath);
        logger.info(MessageFormat.format("Started application ''{0}'' on ''{1}''", appPath,
                DeviceSettings.getPlatformName()));
    }
    public  void tap(String locator){
        WebElement we = driver.findElement(By.xpath(locator));

        TouchAction<?> tap = new TouchAction<>(driver)
                .tap(TapOptions.tapOptions().withElement(ElementOption.element(we, 1, 1)));
        tap.release().perform();
    }
}
