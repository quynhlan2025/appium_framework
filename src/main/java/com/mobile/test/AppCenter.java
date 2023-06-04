package com.mobile.test;

import com.mobile.screens.Home;
import com.mobile.testlistener.TestNGListener;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class AppCenter extends TestNGListener {

    public Home objHome;
    public com.mobile.screens.AppCenter objApp;

    @Test(description = "Show Search label in App screen")
    public void TC001_Show_Search_label_in_App_screen() {
        objHome = goToHomeScreen();
        objApp = objHome.tapOnApp();
        assertTrue(objApp.shouldBeToShowSearchLabel("Menu"));
    }

}
