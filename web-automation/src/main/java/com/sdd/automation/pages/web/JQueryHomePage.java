package com.sdd.automation.pages.web;

import com.microsoft.playwright.Page;

public class JQueryHomePage {

    private final Page page;

    private static final String URL = "https://jqueryui.com/";

    public JQueryHomePage(Page page) {
        this.page = page;
    }

    public void navigate() {
        page.navigate(URL);
    }

    public String getTitle() {
        return page.title();
    }
}