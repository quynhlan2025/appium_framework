package com.mobile.keywords;

import com.mobile.configs.ConfigSettings;
import com.mobile.configs.DeviceSettings;
import com.mobile.drivers.DriverManager;
import com.mobile.drivers.DriverManagerFactory;
import com.mobile.drivers.DriverType;
import com.mobile.helper.LogHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;

public class MobileKeywords {
    private static String APPIUM_SERVER_URL="http://127.0.0.1:4723/wd/hub";
    private static AppiumDriver<?> driver;
    private static Logger logger = LogHelper.getLogger();
    private static DriverManager driverManager;

    private static int defaultTimeout;
    private ConfigSettings configSettings;
    public MobileKeywords() {
        configSettings = new ConfigSettings(System.getProperty("user.dir"));
        defaultTimeout = Integer.parseInt(configSettings.getTimeout());
    }
    public static void startApplication(String appPath) throws MalformedURLException {
        logger.debug(MessageFormat.format("Starting application ''{0}'' on ''{1}''", appPath, DeviceSettings.getPlatformName()));
        new DeviceSettings(System.getProperty("user.dir"), "emulator-5554");

        driverManager= DriverManagerFactory.getManager(DriverType.valueOf(DeviceSettings.getPlatformName().toUpperCase()));
        driver=driverManager.getDriver(appPath);
        logger.info(MessageFormat.format("Started application ''{0}'' on ''{1}''", appPath,
                DeviceSettings.getPlatformName()));
    }
    public WebElement findMobileElement(Object locator, int... timeOut) {
        if (locator instanceof WebElement) {
            return (WebElement) locator;
        }
        int waitTime = timeOut.length > 0 ? timeOut[0] : defaultTimeout;
        final By by = MobileElementHelper.findBy(locator);
        String webId = MobileElementHelper.findTestObjectName(locator);
        String locatorValue = MobileElementHelper.findTestObjectNameValue(locator);
        logger.info(
                MessageFormat.format("Finding web element ''{0}'' located by ''{1}''", webId, locatorValue));
        try {
            Wait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(waitTime))
                    .pollingEvery(Duration.ofSeconds(500L)).ignoring(NoSuchElementException.class);
            WebElement we = (WebElement) wait.until(new Function<AppiumDriver, WebElement>() {
                @Override
                public WebElement apply(AppiumDriver driver) {
                    return driver.findElement(by);
                }
            });
            if (we != null) {
                logger.info(
                        MessageFormat.format("Found {0} web element ''{1}'' located by ''{2}'' within {3}", 1,
                                webId, locatorValue,
                                waitTime));
                return we;
            }
        } catch (Exception e) {
            logger.error(MessageFormat.format(
                    "Not found web element ''{0}'' located by ''{1}'' within ''{2}''. Root cause: {3}", webId, locatorValue,
                    waitTime, e.getMessage()));
        }
        return null;
    }
    public void tap(Object locator) {
        WebElement we = findMobileElement(locator);
        String webId = MobileElementHelper.findTestObjectName(locator);
        String locatorValue = MobileElementHelper.findTestObjectNameValue(locator);
        logger.debug(MessageFormat.format("Clicking web element ''{0}'' located by ''{1}''", webId, locatorValue));
        try {
            TouchAction<?> tap = new TouchAction<>(driver)
                    .tap(TapOptions.tapOptions().withElement(ElementOption.element(we, 1, 1)));
            tap.release().perform();
            logger.info(MessageFormat.format("Clicked web element ''{0}'' located by ''{1}''", webId, locatorValue));
        } catch (Exception e) {
            logger.error(
                    MessageFormat.format("Cannot tap on web element ''{0}'' located by {1}. Root cause: {2}", webId, locatorValue,
                            e.getMessage()));
        }
    }

    public void delayInSeconds(int milliseconds) {
        try {
            Thread.sleep(milliseconds * 1000);
            logger.info(MessageFormat.format("Delayed {0} millisecond(s) successfully", milliseconds));
        } catch (Throwable e) {
            logger.error(
                    MessageFormat.format("Cannot delay ''{0}'' millisecond(s). Root cause: ", milliseconds,
                            e.getMessage()));
        }
    }
    public void scrollDown() {
        AppiumDriver<?> driver = driverManager.getDriver();
        Dimension size = driver.manage().window().getSize();
        int anchor = size.width / 2;
        int startPoint = (int) (size.height * 0.70);
        int endPoint = (int) (size.height * 0.30);
        try {
            new TouchAction((PerformsTouchActions) driver).longPress(
                            PointOption.point(anchor, startPoint))
                    .moveTo(PointOption.point(anchor, endPoint)).release().perform();
            logger.info("Scrolled down successfully");
        } catch (Exception e) {
            logger.error(
                    MessageFormat.format("Unable to scroll down. Root cause: ''{0}''", e.getMessage()));
        }
    }
    public void scrollDown(Object locator, int... timeOut) {
        int waitTime = timeOut.length > 0 ? timeOut[0] : defaultTimeout;
        boolean found = false;
        AppiumDriver driver = driverManager.getDriver();
        WebElement we = null;
        Dimension size = driver.manage().window().getSize();
        int anchor = size.width / 2;
        int startPoint = (int) (size.height * 0.70);
        int endPoint = (int) (size.height * 0.30);
//    String errorMessage = null;
        float timeCount = 0;
        long milliseconds = System.currentTimeMillis();
        do {
            try {
                new TouchAction((PerformsTouchActions) driver).longPress(
                                PointOption.point(anchor, startPoint))
                        .moveTo(PointOption.point(anchor, endPoint)).release().perform();
                By by = locator instanceof By ? (By) locator : By.xpath(locator.toString());
                we = driver.findElement(by);
                if (we != null) {
                    found = true;
                    break;
                }
            } catch (NoSuchElementException e) {
//        errorMessage = e.getMessage();
            }
            timeCount += ((double) (System.currentTimeMillis() - milliseconds) / 1000);
            delayInMilliSeconds(500);
            timeCount += 0.5;
            milliseconds = System.currentTimeMillis();
        } while (timeCount <= waitTime);
        if (found) {
            logger
                    .info(MessageFormat.format("Scrolling down to element ''{0}'' successfully",
                            locator.toString()));
        } else {
            logger.error(
                    MessageFormat.format(
                            "Reached timeout. Unable to scroll down to element ''{0}''. Root cause: Element ''{0}'' not found",
                            locator.toString()));
        }

    }
    public void scrollToText(String text) {
        AppiumDriver driver = driverManager.getDriver();
        try {
            WebElement element = null;
            if (driver instanceof AndroidDriver) {
                String uiSelector = String.format("new UiSelector().textContains(\"%s\")", text);
                String uiScrollable = String.format(
                        "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(%s)",
                        uiSelector);
                element = ((AndroidDriver) driver).findElement(new MobileBy.ByAndroidUIAutomator(uiScrollable));
            } else if (driver instanceof IOSDriver) {
                List<WebElement> elements =
                        (List<WebElement>) ((IOSDriver) driver).findElement(By.xpath(
                                "//*[contains(@label, '" + text + "') or contains(@text, '" + text + "')]"));
                if (elements != null && !elements.isEmpty()) {
                    logger.info(MessageFormat.format(
                            "Found text '{0}' in element which has size '{1}'", text, elements.size()));
                    RemoteWebElement remoteElement = (RemoteWebElement) elements.get(0);
                    String parentID = remoteElement.getId();
                    HashMap<String, String> scrollObject = new HashMap<>();
                    scrollObject.put("element", parentID);
                    scrollObject.put("toVisible", text);
                    driver.executeScript("mobile:scroll", scrollObject);
                    element = remoteElement;
                }
            }
            if (element != null) {
                logger.info(
                        MessageFormat.format("Scrolled to text ''{0}'' successfully", text));
            } else {
                logger.error(
                        MessageFormat.format("Text ''{0}'' not found.", text));
            }
        } catch (Exception e) {
            logger.error(
                    MessageFormat.format("Unable to scroll to text ''{0}''. Root cause: ''{1}''",
                            text, e.getMessage()));
        }
    }
    public void delayInMilliSeconds(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
            logger.info(MessageFormat.format("Delayed {0} millisecond(s) successfully", milliseconds));
        } catch (Throwable e) {
            logger.error(
                    MessageFormat.format("Cannot delay ''{0}'' millisecond(s). Root cause: ", milliseconds,
                            e.getMessage()));
        }
    }
    public void closeApplication() {
        try {
            logger.info("Closing the application");
            driverManager.quitDriver();
            logger.info("Closed the application");
        } catch (Exception e) {
            logger.error(
                    MessageFormat.format("Cannot close the application. Root cause: {0}", e.getMessage()));
        }
    }
}
