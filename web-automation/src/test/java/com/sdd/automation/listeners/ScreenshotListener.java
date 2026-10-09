package com.sdd.automation.listeners;

import com.microsoft.playwright.Page;
import com.sdd.automation.driver.PlaywrightManager;
import com.sdd.automation.utils.ScreenshotUtil;
import io.qameta.allure.Allure;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Path;

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        attachExecutionSummary(result, "PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        attachExecutionSummary(result, "FAILED");
        attachStackTrace(result);
        captureFailureScreenshot(result);
    }

    private void attachExecutionSummary(
            ITestResult result, String status) {

        long durationMs =
                result.getEndMillis() - result.getStartMillis();

        String summary =
                "Test class: " + result.getTestClass().getName() + "\n"
                + "Test method: " + result.getMethod().getMethodName() + "\n"
                + "Status: " + status + "\n"
                + "Duration: " + durationMs + " ms\n";

        Allure.addAttachment(
                "Execution summary - "
                        + result.getMethod().getMethodName(),
                "text/plain",
                summary
        );
    }

    private void attachStackTrace(ITestResult result) {
        Throwable failure = result.getThrowable();

        if (failure == null) {
            return;
        }

        StringWriter writer = new StringWriter();
        failure.printStackTrace(new PrintWriter(writer));

        Allure.addAttachment(
                "Failure stack trace - "
                        + result.getMethod().getMethodName(),
                "text/plain",
                writer.toString()
        );
    }

    private void captureFailureScreenshot(ITestResult result) {
        try {
            Page page = PlaywrightManager.getPage();

            String screenshotPath = ScreenshotUtil.capture(
                    page,
                    result.getMethod().getMethodName()
            );

            Path path = Path.of(screenshotPath);

            try (InputStream inputStream =
                         new FileInputStream(path.toFile())) {

                Allure.addAttachment(
                        "Failure screenshot",
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
            System.err.println(
                    "Unable to capture/attach failure screenshot: "
                            + e.getMessage()
            );
        }
    }
}
