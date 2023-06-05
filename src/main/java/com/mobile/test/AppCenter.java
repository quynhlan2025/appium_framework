package com.mobile.test;

import com.mobile.screens.Home;
import com.mobile.testlistener.TestNGListener;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;


import static org.testng.Assert.assertTrue;
@Feature("Test case for Home Screen")
@Test
public class AppCenter extends TestNGListener {

    public Home objHome;
    public com.mobile.screens.AppCenter objApp;

    @Test(description = "Show Search label in App screen")
    @Description("Some detailed test description")
    @Severity(SeverityLevel.CRITICAL)
    public void TC001_Show_Search_label_in_App_screen() {
        objHome = goToHomeScreen();
        objApp = objHome.tapOnApp();
        assertTrue(objApp.shouldBeToShowSearchLabel("Menu"));
    }

}
