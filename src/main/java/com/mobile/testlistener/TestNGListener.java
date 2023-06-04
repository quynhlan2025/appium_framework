package com.mobile.testlistener;
import com.mobile.configs.ConfigSettings;
import com.mobile.configs.DeviceSettings;
import com.mobile.keywords.MobileKeywords;
import com.mobile.screens.Home;
import org.testng.ITestNGListener;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;

public class TestNGListener implements ITestNGListener {

    protected MobileKeywords mobileAction;
    private ConfigSettings configSettings;

    public TestNGListener() {
        mobileAction = new MobileKeywords();
        configSettings = new ConfigSettings(System.getProperty("user.dir"));
    }

    @BeforeTest
    public void beforeTest() throws MalformedURLException {
        new DeviceSettings(System.getProperty("user.dir"), "emulator-5554");
        mobileAction.startApplication(configSettings.getAppPath());
    }

    @AfterTest
    public void afterTest() {
        mobileAction.closeApplication();
    }

    public Home goToHomeScreen() {
        mobileAction.delayInSeconds(3);
        return new Home(this.mobileAction);
    }
}
