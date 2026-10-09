package com.sdd.automation.pages.web;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Page;

public class DroppablePage extends BasePage {

    private final FrameLocator demoFrame;

    public DroppablePage(Page page) {
        super(page);
        this.demoFrame = page.frameLocator(".demo-frame");
    }

    public void navigate() {
        navigateTo("/droppable/");
    }

    public void dragItemToTarget() {
        demoFrame.locator("#draggable")
                .dragTo(demoFrame.locator("#droppable"));
    }

    public String getDropMessage() {
        return demoFrame.locator("#droppable").innerText();
    }
}
