
package com.sdd.automation.listeners;

import com.sdd.automation.driver.AppiumDriverManager;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

public class AllureTestListener implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        attachExecutionSummary(result, "PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        attachExecutionSummary(result, "FAILED");

        Throwable failure = result.getThrowable();

        if (failure != null) {
            StringWriter writer = new StringWriter();
            failure.printStackTrace(new PrintWriter(writer));

            Allure.addAttachment(
                "Failure stack trace - " + result.getMethod().getMethodName(),
                "text/plain",
                writer.toString()
            );
        }

        // Capture screenshots for mobile UI failures.
        String className = result.getTestClass().getName();

        if (className.contains(".tests.mobile.")) {
            try {
                AppiumDriver driver = AppiumDriverManager.getDriver();

                if (driver != null) {
                    byte[] screenshot =
                        driver.getScreenshotAs(OutputType.BYTES);

                    Allure.addAttachment(
                        "Failure screenshot - "
                            + result.getMethod().getMethodName(),
                        "image/png",
                        new ByteArrayInputStream(screenshot),
                        ".png"
                    );
                }
            } catch (Exception e) {
                Allure.addAttachment(
                    "Screenshot capture error",
                    "text/plain",
                    e.toString()
                );
            }
        }
    }

    private void attachExecutionSummary(
            ITestResult result, String status) {

        String testName = result.getMethod().getMethodName();
        String className = result.getTestClass().getName();

        long durationMs =
            result.getEndMillis() - result.getStartMillis();

        String summary =
            "Test class: " + className + "\n"
            + "Test method: " + testName + "\n"
            + "Status: " + status + "\n"
            + "Duration: " + durationMs + " ms\n";

        Allure.addAttachment(
            "Execution summary - " + testName,
            "text/plain",
            summary
        );
    }
}