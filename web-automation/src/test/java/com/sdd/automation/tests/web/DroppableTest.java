package com.sdd.automation.tests.web;

import com.microsoft.playwright.Page;
import com.sdd.automation.driver.PlaywrightManager;
import com.sdd.automation.pages.web.DroppablePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DroppableTest extends BaseWebTest {

    @Test
    public void verifyDragAndDrop() {

        Page page = PlaywrightManager.getPage();

        DroppablePage droppablePage =
                new DroppablePage(page);

        droppablePage.navigate();

        droppablePage.dragItemToTarget();

        String dropMessage =
                droppablePage.getDropMessage();

        Assert.assertTrue(
                dropMessage.contains("Dropped!"),
                "Drop operation was not successful"
        );
    }
}