package com.sdd.automation.tests.web;

import com.microsoft.playwright.Page;
import com.sdd.automation.driver.PlaywrightManager;
import com.sdd.automation.pages.web.ControlgroupPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ControlgroupTest extends BaseWebTest {

    @Test
    public void verifyControlgroup() {

        Page page = PlaywrightManager.getPage();

        ControlgroupPage controlgroupPage =
                new ControlgroupPage(page);

        controlgroupPage.navigate();

        Assert.assertTrue(
                controlgroupPage.isControlgroupDisplayed(),
                "Controlgroup was not displayed"
        );

        controlgroupPage.clickBookNow();
    }
}