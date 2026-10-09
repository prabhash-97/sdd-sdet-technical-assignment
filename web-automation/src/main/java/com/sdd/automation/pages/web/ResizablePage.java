package com.sdd.automation.pages.web;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Page;

public class ResizablePage extends BasePage{

    private final FrameLocator demoFrame;

    public ResizablePage(Page page) {
        super(page);
        this.demoFrame = page.frameLocator(".demo-frame");
    }

    public void navigate() {
        navigateTo("/resizable/");
    }

    public int getWidth() {
        return demoFrame.locator("#resizable")
                .boundingBox()
                .width > 0
                ? (int) demoFrame.locator("#resizable")
                        .boundingBox()
                        .width
                : 0;
    }

    public int getHeight() {
        return demoFrame.locator("#resizable")
                .boundingBox()
                .height > 0
                ? (int) demoFrame.locator("#resizable")
                        .boundingBox()
                        .height
                : 0;
    }

    public void resize() {

        var box = demoFrame.locator("#resizable")
                .boundingBox();

        double startX = box.x + box.width - 5;
        double startY = box.y + box.height - 5;

        double endX = startX + 100;
        double endY = startY + 100;

        page.mouse().move(startX, startY);
        page.mouse().down();
        page.mouse().move(endX, endY);
        page.mouse().up();
    }
}