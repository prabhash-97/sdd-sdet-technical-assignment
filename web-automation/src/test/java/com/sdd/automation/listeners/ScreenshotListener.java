package com.sdd.automation.listeners;

import com.microsoft.playwright.Page;
import com.sdd.automation.driver.PlaywrightManager;
import com.sdd.automation.utils.ScreenshotUtil;
import io.qameta.allure.Allure;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        try {

            Page page = PlaywrightManager.getPage();

            String testName =
                    result.getMethod().getMethodName();

            String screenshotPath =
                    ScreenshotUtil.capture(
                            page,
                            testName
                    );

            Path path = Path.of(screenshotPath);

            try (InputStream inputStream =
                         new FileInputStream(path.toFile())) {

                Allure.addAttachment(
                        "Failure Screenshot",
                        "image/png",
                        inputStream,
                        "png"
                );
            }

            System.out.println(
                    "Failure screenshot attached to Allure: "
                            + screenshotPath
            );

        } catch (Exception e) {

            System.out.println(
                    "Unable to capture/attach screenshot: "
                            + e.getMessage()
            );
        }
    }
}