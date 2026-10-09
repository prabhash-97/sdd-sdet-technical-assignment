package com.sdd.automation.pages.web;

import com.microsoft.playwright.Page;
import com.sdd.automation.config.ConfigManager;

public abstract class BasePage {

    protected final Page page;

    protected BasePage(Page page) {
        this.page = page;
    }

    protected void navigateTo(String path) {
        String normalizedPath = path.startsWith("/")
                ? path
                : "/" + path;

        page.navigate(
                ConfigManager.getBaseUrl() + normalizedPath
        );
    }
}
