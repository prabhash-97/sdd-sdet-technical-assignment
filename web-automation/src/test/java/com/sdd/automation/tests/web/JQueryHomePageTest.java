package com.sdd.automation.tests.web;

import com.microsoft.playwright.Page;
import com.sdd.automation.driver.PlaywrightManager;
import com.sdd.automation.pages.web.JQueryHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class JQueryHomePageTest extends BaseWebTest {

    @Test
    public void verifyJQueryUiHomePage() {

        Page page = PlaywrightManager.getPage();

        JQueryHomePage homePage =
                new JQueryHomePage(page);

        homePage.navigate();

        Assert.assertTrue(
                homePage.getTitle().contains("jQuery UI"),
                "jQuery UI : page title was not displayed"
        );
    }
}