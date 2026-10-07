package com.sdd.automation.driver;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public final class PlaywrightManager {

    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext context;
    private static Page page;

    private PlaywrightManager() {
    }

    public static void initialize() {

        playwright = Playwright.create();

        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(true)
        );

        context = browser.newContext(
                new Browser.NewContextOptions()
                        .setViewportSize(1440, 900)
        );

        page = context.newPage();
    }

    public static Page getPage() {

        if (page == null) {
            throw new IllegalStateException(
                    "Playwright page has not been initialized"
            );
        }

        return page;
    }

    public static void close() {

        if (context != null) {
            context.close();
        }

        if (browser != null) {
            browser.close();
        }

        if (playwright != null) {
            playwright.close();
        }

        page = null;
        context = null;
        browser = null;
        playwright = null;
    }
}