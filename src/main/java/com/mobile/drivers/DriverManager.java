package com.mobile.drivers;

import io.appium.java_client.AppiumDriver;

import java.net.MalformedURLException;

public abstract class DriverManager {
    protected AppiumDriver<?> driver;
    protected abstract void createDriver(String appPath) throws MalformedURLException;
    public void quitDriver() {
        if (null != driver) {
            driver.quit();
            driver = null;
        }
    }
    public AppiumDriver<?>  getDriver(String appPath) {
        if (null == driver) {

            try {
                createDriver(appPath);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
        return driver;
    }
    public AppiumDriver<?> getDriver() {
        return driver;
    }
}
