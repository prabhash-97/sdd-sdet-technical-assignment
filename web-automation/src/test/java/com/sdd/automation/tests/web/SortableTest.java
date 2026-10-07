package com.sdd.automation.tests.web;

import com.microsoft.playwright.Page;
import com.sdd.automation.driver.PlaywrightManager;
import com.sdd.automation.pages.web.SortablePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SortableTest extends BaseWebTest {

    @Test
    public void verifySortableOrder() {

        Page page = PlaywrightManager.getPage();

        SortablePage sortablePage =
                new SortablePage(page);

        sortablePage.navigate();

        String initialFirstItem =
                sortablePage.getFirstItem();

        String lastItem =
                sortablePage.getLastItem();

        sortablePage.moveLastItemToFirst();

        Assert.assertNotEquals(
                sortablePage.getFirstItem(),
                initialFirstItem,
                "Sortable order did not change"
        );

        Assert.assertTrue(
                sortablePage.isItemFirst(lastItem),
                "Last item was not moved to the first position"
        );
    }
}