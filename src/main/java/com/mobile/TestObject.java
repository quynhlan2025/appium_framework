package com.mobile;

public class TestObject {
    private String objectName;

    private String locatorValue;

    public TestObject(String objectName, String expressionLocator) {
        this.objectName = objectName;
        this.locatorValue = expressionLocator;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getLocatorValue() {
        return this.locatorValue;
    }

    public void setLocatorValue(String locatorValue) {
        this.locatorValue = locatorValue;
    }
}
