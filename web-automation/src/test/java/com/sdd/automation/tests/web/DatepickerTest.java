package com.sdd.automation.tests.web;

import com.microsoft.playwright.Page;
import com.sdd.automation.driver.PlaywrightManager;
import com.sdd.automation.pages.web.DatepickerPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatepickerTest extends BaseWebTest {

    @Test
    public void verifyCurrentDateSelection() {

        Page page = PlaywrightManager.getPage();

        DatepickerPage datepickerPage =
                new DatepickerPage(page);

        datepickerPage.navigate();

        datepickerPage.selectCurrentDate();

        String actualDate =
                datepickerPage.getSelectedDate();

        String expectedDate =
                LocalDate.now()
                        .format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        Assert.assertEquals(
                actualDate,
                expectedDate,
                "Current date was not selected correctly"
        );
    }
}