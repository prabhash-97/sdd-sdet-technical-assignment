package com.sdd.automation.pages.web;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Page;

import java.time.LocalDate;

public class DatepickerPage extends BasePage {

    private final FrameLocator demoFrame;

    public DatepickerPage(Page page) {
        super(page);
        this.demoFrame = page.frameLocator(".demo-frame");
    }

    public void navigate() {
        navigateTo("/datepicker/");
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