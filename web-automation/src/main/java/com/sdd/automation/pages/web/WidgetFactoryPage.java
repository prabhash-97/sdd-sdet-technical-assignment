package com.sdd.automation.pages.web;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Page;

public class WidgetFactoryPage {

    private final Page page;

    private static final String WIDGET_FACTORY_URL =
            "https://jqueryui.com/widget/";

    private final FrameLocator demoFrame;

    public WidgetFactoryPage(Page page) {
        this.page = page;
        this.demoFrame = page.frameLocator(".demo-frame");
    }

    public void navigate() {
        page.navigate(WIDGET_FACTORY_URL);
    }

    public void clickGoGreen() {
        demoFrame.locator("#green").click();
    }

    public String getWidgetColor() {
        Object color = demoFrame.locator("#my-widget1")
                .evaluate(
                        "element => getComputedStyle(element).backgroundColor"
                );

        return String.valueOf(color);
    }
}