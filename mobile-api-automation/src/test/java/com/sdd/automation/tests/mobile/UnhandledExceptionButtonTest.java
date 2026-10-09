
package com.sdd.automation.tests.mobile;

import com.sdd.automation.driver.AppiumDriverManager;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import org.testng.annotations.Listeners;
import com.sdd.automation.listeners.AllureTestListener;

@Listeners(AllureTestListener.class)
public class UnhandledExceptionButtonTest extends BaseMobileTest {

    @Test
    public void verifyHomeScreenAfterUnhandledExceptionButton() {
        AppiumDriver driver = AppiumDriverManager.getDriver();
        WebDriverWait wait = new WebDriverWait(
            driver, Duration.ofSeconds(10)
        );

        By exceptionButton =
            By.id("io.selendroid.testapp:id/exceptionTestButton");

        wait.until(
            ExpectedConditions.elementToBeClickable(exceptionButton)
        ).click();

        // Verify the app remains on the expected home screen.
        String title = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.id("android:id/title")
            )
        ).getText();

        Assert.assertEquals(
            title,
            "selendroid-test-app",
            "Home screen title should be correct after tapping the exception button"
        );
    }
}