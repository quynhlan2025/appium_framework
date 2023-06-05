package com.mobile.screens;

import com.mobile.base.BaseScreen;
import com.mobile.keywords.MobileKeywords;
import io.qameta.allure.Step;

public class Home extends BaseScreen {

    public Home(MobileKeywords mobileAction) {
        super(mobileAction);
        setRepoFile(OBJECT_REPO);
    }

    @Step("Tap on APP")
    public AppCenter tapOnApp() {
        mobileAction.tap(findTestObject("LBL_APP"));
        mobileAction.delayInSeconds(3);
        return new AppCenter(this.mobileAction);
    }
}

