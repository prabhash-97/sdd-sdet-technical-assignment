package com.sdd.automation.utils;

import com.microsoft.playwright.Page;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ScreenshotUtil {

    private ScreenshotUtil() {
    }

    public static String capture(Page page, String testName) {

        try {
            Path screenshotDirectory =
                    Paths.get("target", "screenshots");

            Files.createDirectories(screenshotDirectory);

            String timestamp =
                    LocalDateTime.now()
                            .format(
                                    DateTimeFormatter.ofPattern(
                                            "yyyyMMdd_HHmmss"
                                    )
                            );

            String fileName =
                    testName + "_" + timestamp + ".png";

            Path screenshotPath =
                    screenshotDirectory.resolve(fileName);

            page.screenshot(
                    new Page.ScreenshotOptions()
                            .setPath(screenshotPath)
                            .setFullPage(true)
            );

            return screenshotPath.toString();

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to capture screenshot",
                    e
            );
        }
    }
}