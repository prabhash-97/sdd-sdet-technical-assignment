package com.sdd.automation.pages.web;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Page;

public class DroppablePage {

    private final Page page;

    private static final String DROPPABLE_URL =
            "https://jqueryui.com/droppable/";

    private final FrameLocator demoFrame;

    public DroppablePage(Page page) {
        this.page = page;
        this.demoFrame = page.frameLocator(".demo-frame");
    }

    public void navigate() {
        page.navigate(DROPPABLE_URL);
    }

    public void dragItemToTarget() {

        demoFrame.locator("#draggable")
                .dragTo(
                        demoFrame.locator("#droppable")
                );
    }

    public String getDropMessage() {

        return demoFrame.locator("#droppable")
                .innerText();
    }
}