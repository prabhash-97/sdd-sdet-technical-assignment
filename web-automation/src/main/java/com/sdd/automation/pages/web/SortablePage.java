package com.sdd.automation.pages.web;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SortablePage extends BasePage{

    private final FrameLocator demoFrame;

    public SortablePage(Page page) {
        super(page);
        this.demoFrame = page.frameLocator(".demo-frame");
    }

    public void navigate() {
        navigateTo("/sortable/");
    }

    public String getFirstItem() {
        return demoFrame.locator("#sortable li")
                .first()
                .innerText();
    }

    public String getLastItem() {
        return demoFrame.locator("#sortable li")
                .last()
                .innerText();
    }

    public void moveLastItemToFirst() {

        Locator last =
                demoFrame.locator("#sortable li").last();

        Locator first =
                demoFrame.locator("#sortable li").first();

        last.dragTo(first);
    }

    public boolean isItemFirst(String expectedItem) {
        return getFirstItem().equals(expectedItem);
    }
}