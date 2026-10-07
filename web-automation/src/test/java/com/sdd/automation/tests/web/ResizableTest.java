package com.sdd.automation.tests.web;

import com.microsoft.playwright.Page;
import com.sdd.automation.driver.PlaywrightManager;
import com.sdd.automation.pages.web.ResizablePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ResizableTest extends BaseWebTest {

    @Test
    public void verifyResizableBox() {

        Page page = PlaywrightManager.getPage();

        ResizablePage resizablePage =
                new ResizablePage(page);

        resizablePage.navigate();

        int initialWidth = resizablePage.getWidth();
        int initialHeight = resizablePage.getHeight();

        resizablePage.resize();

        int finalWidth = resizablePage.getWidth();
        int finalHeight = resizablePage.getHeight();

        Assert.assertTrue(
                finalWidth > initialWidth,
                "Resizable box width did not increase"
        );

        Assert.assertTrue(
                finalHeight > initialHeight,
                "Resizable box height did not increase"
        );
    }
}