package com.sdd.automation.pages.web;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Page;

public class ControlgroupPage {

    private final Page page;

    private static final String CONTROLGROUP_URL =
            "https://jqueryui.com/controlgroup/";

    private final FrameLocator demoFrame;

    public ControlgroupPage(Page page) {
        this.page = page;
        this.demoFrame = page.frameLocator(".demo-frame");
    }

    public void navigate() {
        page.navigate(CONTROLGROUP_URL);
    }

    public boolean isControlgroupDisplayed() {
        return demoFrame.locator(".ui-controlgroup")
                .first()
                .isVisible();
    }

    public void clickBookNow() {
        demoFrame.locator("#book").click();
    }
}