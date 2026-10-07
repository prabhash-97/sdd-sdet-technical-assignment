package com.sdd.automation.pages.web;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Page;

import java.time.LocalDate;

public class DatepickerPage {

    private final Page page;

    private static final String DATEPICKER_URL =
            "https://jqueryui.com/datepicker/";

    private final FrameLocator demoFrame;

    public DatepickerPage(Page page) {
        this.page = page;
        this.demoFrame = page.frameLocator(".demo-frame");
    }

    public void navigate() {
        page.navigate(DATEPICKER_URL);
    }

    public void selectCurrentDate() {

        LocalDate today = LocalDate.now();

        int day = today.getDayOfMonth();

        demoFrame.locator("#datepicker").click();

        demoFrame.locator(
                ".ui-datepicker-calendar td:not(.ui-datepicker-other-month)"
                        + " a:text-is('" + day + "')"
        ).click();
    }

    public String getSelectedDate() {
        return demoFrame.locator("#datepicker")
                .inputValue();
    }
}