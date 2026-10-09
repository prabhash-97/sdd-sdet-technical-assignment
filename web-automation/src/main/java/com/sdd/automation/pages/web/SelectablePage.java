package com.sdd.automation.pages.web;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Page;

public class SelectablePage extends BasePage{

    private final FrameLocator demoFrame;

    public SelectablePage(Page page) {
        super(page);
        this.demoFrame = page.frameLocator(".demo-frame");
    }

    public void navigate() {
        navigateTo("/selectable/");
    }

    public void selectItems() {

        demoFrame.locator("#selectable li").nth(0).click();

        page.keyboard().down("Meta");

        demoFrame.locator("#selectable li").nth(2).click();

        demoFrame.locator("#selectable li").nth(6).click();

        page.keyboard().up("Meta");
    }

    public boolean isItemSelected(int itemIndex) {

        String classes = demoFrame
                .locator("#selectable li")
                .nth(itemIndex)
                .getAttribute("class");

        return classes != null && classes.contains("ui-selected");
    }
}