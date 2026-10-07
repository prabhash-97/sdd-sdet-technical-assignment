package com.sdd.automation.pages.web;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SortablePage {

    private final Page page;

    private static final String SORTABLE_URL =
            "https://jqueryui.com/sortable/";

    private final FrameLocator demoFrame;

    public SortablePage(Page page) {
        this.page = page;
        this.demoFrame = page.frameLocator(".demo-frame");
    }

    public void navigate() {
        page.navigate(SORTABLE_URL);
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