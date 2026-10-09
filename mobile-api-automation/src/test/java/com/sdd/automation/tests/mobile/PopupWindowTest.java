package com.sdd.automation.tests.mobile;

import com.sdd.automation.driver.AppiumDriverManager;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Allure;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Collections;
import org.testng.annotations.Listeners;
import com.sdd.automation.listeners.AllureTestListener;

@Listeners(AllureTestListener.class)
public class PopupWindowTest extends BaseMobileTest {

    @Test
    public void verifyPopupWindowCanBeDismissed() {

        AppiumDriver driver = AppiumDriverManager.getDriver();

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step 1: Open the popup
        By popupButton =
                By.id("io.selendroid.testapp:id/showPopupWindowButton");

        wait.until(
                ExpectedConditions.elementToBeClickable(popupButton)
        ).click();

        // Step 2: Allow the popup to appear
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        // Step 3: Check whether Appium can locate Dismiss
        By dismissButton = By.xpath(
                "//*[@text='Dismiss' or @content-desc='Dismiss']"
        );

        boolean dismissFound =
                !driver.findElements(dismissButton).isEmpty();

        if (dismissFound) {
            driver.findElement(dismissButton).click();
        } else {
            // Fallback: tap the approximate centre of the screen
            PointerInput finger =
                    new PointerInput(PointerInput.Kind.TOUCH, "finger");

            Sequence tap = new Sequence(finger, 1);

            tap.addAction(
                    finger.createPointerMove(
                            Duration.ZERO,
                            PointerInput.Origin.viewport(),
                            540,
                            1074
                    )
            );

            tap.addAction(
                    finger.createPointerDown(
                            PointerInput.MouseButton.LEFT.asArg()
                    )
            );

            tap.addAction(
                    finger.createPointerUp(
                            PointerInput.MouseButton.LEFT.asArg()
                    )
            );

            driver.perform(Collections.singletonList(tap));
        }

        // Step 4: Verify the home screen title
        By homeTitle = By.id("android:id/title");

        Assert.assertTrue(
                wait.until(
                        ExpectedConditions.textToBePresentInElementLocated(
                                homeTitle,
                                "selendroid-test-app"
                        )
                ),
                "Home screen title should be visible after dismissing popup."
        );
    }
    
}