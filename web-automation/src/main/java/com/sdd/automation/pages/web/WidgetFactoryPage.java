package com.sdd.automation.pages.web;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Page;

public class WidgetFactoryPage extends BasePage{

    private final FrameLocator demoFrame;

    public WidgetFactoryPage(Page page) {
        super(page);
        this.demoFrame = page.frameLocator(".demo-frame");
    }

    public void navigate() {
        navigateTo("/widget/");
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