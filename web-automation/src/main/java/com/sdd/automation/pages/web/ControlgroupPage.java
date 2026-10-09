package com.sdd.automation.pages.web;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Page;

public class ControlgroupPage extends BasePage{

    private final FrameLocator demoFrame;

    public ControlgroupPage(Page page) {
        super(page);
        this.demoFrame = page.frameLocator(".demo-frame");
    }

    public void navigate() {
         navigateTo("/controlgroup/");;
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