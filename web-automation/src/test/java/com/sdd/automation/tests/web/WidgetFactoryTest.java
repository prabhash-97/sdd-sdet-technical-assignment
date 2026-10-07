package com.sdd.automation.tests.web;

import com.microsoft.playwright.Page;
import com.sdd.automation.driver.PlaywrightManager;
import com.sdd.automation.pages.web.WidgetFactoryPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WidgetFactoryTest extends BaseWebTest {

    @Test
    public void verifyGoGreen() {

        Page page = PlaywrightManager.getPage();

        WidgetFactoryPage widgetFactoryPage =
                new WidgetFactoryPage(page);

        widgetFactoryPage.navigate();

        widgetFactoryPage.clickGoGreen();

        String actualColor =
                widgetFactoryPage.getWidgetColor();

        Assert.assertEquals(
                actualColor,
                "rgb(64, 250, 8)",
                "Widget did not change to green"
        );
    }
}