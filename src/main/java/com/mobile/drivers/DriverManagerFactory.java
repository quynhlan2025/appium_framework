package com.mobile.drivers;

public class DriverManagerFactory {
    public static DriverManager getManager(DriverType type) {

        DriverManager driverManager;

        switch (type) {
            case IOS:
                driverManager = new IosDriverManager();
                break;
            case ANDROID:
                driverManager = new AndroidDriverManager();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        return driverManager;

    }

}
