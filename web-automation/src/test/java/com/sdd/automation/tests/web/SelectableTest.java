package com.sdd.automation.tests.web;

import com.microsoft.playwright.Page;
import com.sdd.automation.driver.PlaywrightManager;
import com.sdd.automation.pages.web.SelectablePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SelectableTest extends BaseWebTest {

    @Test
    public void verifySelectableItems() {

        Page page = PlaywrightManager.getPage();

        SelectablePage selectablePage =
                new SelectablePage(page);

        selectablePage.navigate();

        selectablePage.selectItems();

        Assert.assertTrue(
                selectablePage.isItemSelected(0),
                "Item 1 was not selected"
        );

        Assert.assertTrue(
                selectablePage.isItemSelected(2),
                "Item 3 was not selected"
        );

        Assert.assertTrue(
                selectablePage.isItemSelected(6),
                "Item 7 was not selected"
        );
    }
}