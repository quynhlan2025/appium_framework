package com.mobile.screens;

import com.mobile.base.BaseScreen;
import com.mobile.keywords.MobileKeywords;

public class AppCenter extends BaseScreen {

    public AppCenter(MobileKeywords mobileAction) {
        super(mobileAction);
        setRepoFile(OBJECT_REPO);
    }

    public boolean shouldBeToShowSearchLabel(String text) {
        if(mobileAction.verifyElementText(findTestObject("LBL_SEARCH"),text)) {
            return true;
        }
        return false;
    }
}
