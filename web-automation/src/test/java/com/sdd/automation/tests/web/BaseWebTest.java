package com.sdd.automation.tests.web;

import com.microsoft.playwright.Page;
import com.sdd.automation.driver.PlaywrightManager;
import io.qameta.allure.Allure;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.ByteArrayInputStream;

public class BaseWebTest {

    @BeforeMethod
    public void setUp() {
        PlaywrightManager.initialize();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        try {
            if (!result.isSuccess()) {
                attachFailureScreenshot(result);
            }
        } finally {
            PlaywrightManager.close();
        }
    }

    private void attachFailureScreenshot(ITestResult result) {
        try {
            Page page = PlaywrightManager.getPage();

            byte[] screenshot = page.screenshot(
                    new Page.ScreenshotOptions()
                            .setFullPage(true)
            );

            Allure.addAttachment(
                    "Failure Screenshot - " + result.getName(),
                    "image/png",
                    new ByteArrayInputStream(screenshot),
                    ".png"
            );
        } catch (Exception e) {
            Allure.addAttachment(
                    "Screenshot Capture Error - " + result.getName(),
                    "text/plain",
                    "Unable to capture failure screenshot: "
                            + e.getMessage(),
                    ".txt"
            );
        }
    }
}