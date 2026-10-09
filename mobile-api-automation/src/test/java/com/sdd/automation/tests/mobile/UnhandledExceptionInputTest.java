
package com.sdd.automation.tests.mobile;

import com.sdd.automation.driver.AppiumDriverManager;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import org.testng.annotations.Listeners;
import com.sdd.automation.listeners.AllureTestListener;

@Listeners(AllureTestListener.class)
public class UnhandledExceptionInputTest extends BaseMobileTest {

    @Test
    public void verifyHomeScreenAfterTypingExceptionInput() {
        AppiumDriver driver = AppiumDriverManager.getDriver();
        WebDriverWait wait = new WebDriverWait(
            driver, Duration.ofSeconds(10)
        );

        
By exceptionField =
    By.id("io.selendroid.testapp:id/exceptionTestField");

wait.until(ExpectedConditions.elementToBeClickable(exceptionField))
    .click();

driver.findElement(exceptionField).sendKeys("test");

String enteredText = wait.until(d -> {
    WebElement field = d.findElement(exceptionField);
    String value = field.getText();
    return value != null && value.contains("test") ? value : null;
});

Assert.assertTrue(
    enteredText.contains("test"),
    "Exception field should contain the entered text"
);

        // Verify the home screen title after entering the text.
        String title = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.id("android:id/title")
            )
        ).getText();

        Assert.assertEquals(
            title,
            "selendroid-test-app",
            "Home screen title should be correct after typing in the exception field"
        );
    }
}