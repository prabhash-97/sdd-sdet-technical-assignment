package com.sdd.automation.pages.web;

import com.microsoft.playwright.Page;

public class JQueryHomePage extends BasePage {

    public JQueryHomePage(Page page) {
        super(page);
    }

    public void navigate() {
        navigateTo("/");
    }

    public String getTitle() {
        return page.title();
    }
}