package com.mobile.keywords;

import com.mobile.TestObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MobileElementHelper {
    public static By findBy(Object locator) {
        if(locator instanceof String) {
            return By.xpath((String) locator);
        } else if(locator instanceof By) {
            return (By) locator;
        } else if (locator instanceof TestObject) {
            return By.xpath(((TestObject) locator).getLocatorValue());
        }
        return null;
    }

    public static String findTestObjectName(Object locator) {
        if (locator instanceof By) {
            return "" + locator;
        } else if (locator instanceof String) {
            return "" + locator;
        } else if (locator instanceof WebElement) {
            return "" + locator;
        } else if (locator instanceof TestObject) {
            return ((TestObject) locator).getObjectName();
        }
        return null;
    }

    public static String findTestObjectNameValue(Object locator) {
        if (locator instanceof By) {
            return "" + locator;
        } else if (locator instanceof String) {
            return "" + locator;
        } else if (locator instanceof WebElement) {
            return "" + locator;
        } else if (locator instanceof TestObject) {
            return ((TestObject) locator).getLocatorValue();
        }
        return null;
    }
}
