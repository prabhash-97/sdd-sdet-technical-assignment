package com.sdd.automation.pages.mobile;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LanguagePage {

    private final WebDriverWait wait;

    private final By languageButton =
            By.id("io.selendroid.testapp:id/buttonTest");

    private final By noNoButton =
            By.id("android:id/button2");

    public LanguagePage(AppiumDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void tapLanguageButton() {
        wait.until(
                ExpectedConditions.elementToBeClickable(languageButton)
        ).click();
    }

    public String getDialogMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("android:id/message")
                )
        ).getText();
    }

    public void selectNoNo() {
        wait.until(
                ExpectedConditions.elementToBeClickable(noNoButton)
        ).click();
    }
}