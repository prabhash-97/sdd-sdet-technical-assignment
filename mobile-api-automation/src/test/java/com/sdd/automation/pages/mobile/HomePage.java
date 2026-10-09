
package com.sdd.automation.pages.mobile;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private final AppiumDriver driver;
    private final WebDriverWait wait;

    private final By title =
            By.id("android:id/title");

    private final By greeting =
            By.xpath("//*[@text='Hello Default Locale, Selendroid-test-app!']");

    private final By languageButton =
            By.id("io.selendroid.testapp:id/buttonTest");

    private final By chromeButton =
            By.id("io.selendroid.testapp:id/buttonStartWebview");

    private final By registrationButton =
            By.id("io.selendroid.testapp:id/startUserRegistration");

    private final By progressButton =
            By.id("io.selendroid.testapp:id/waitingButtonTest");

    private final By toastButton =
            By.id("io.selendroid.testapp:id/showToastButton");

    private final By popupButton =
            By.id("io.selendroid.testapp:id/showPopupWindowButton");

    private final By exceptionButton =
            By.id("io.selendroid.testapp:id/exceptionTestButton");

    private final By exceptionField =
            By.id("io.selendroid.testapp:id/exceptionTestField");

    public HomePage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public String getTitle() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(title)
        ).getText();
    }

    public boolean isGreetingDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(greeting)
        ).isDisplayed();
    }

    public boolean isHomeElementDisplayed(By locator) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        ).isDisplayed();
    }

    public boolean isLanguageButtonDisplayed() {
        return isHomeElementDisplayed(languageButton);
    }

    public boolean isChromeButtonDisplayed() {
        return isHomeElementDisplayed(chromeButton);
    }

    public boolean isRegistrationButtonDisplayed() {
        return isHomeElementDisplayed(registrationButton);
    }

    public boolean isProgressButtonDisplayed() {
        return isHomeElementDisplayed(progressButton);
    }

    public boolean isToastButtonDisplayed() {
        return isHomeElementDisplayed(toastButton);
    }

    public boolean isPopupButtonDisplayed() {
        return isHomeElementDisplayed(popupButton);
    }

    public boolean isExceptionButtonDisplayed() {
        return isHomeElementDisplayed(exceptionButton);
    }

    public boolean isExceptionFieldDisplayed() {
        return isHomeElementDisplayed(exceptionField);
    }

    public WebElement getExceptionField() {
        return wait.until(
                ExpectedConditions.elementToBeClickable(exceptionField)
        );
    }

    public void tapProgressButton() {
        wait.until(
            ExpectedConditions.elementToBeClickable(
                By.id("io.selendroid.testapp:id/waitingButtonTest")
            )
        ).click();
    }

    public void tapToastButton() {
        wait.until(
            ExpectedConditions.elementToBeClickable(
                By.id("io.selendroid.testapp:id/showToastButton")
            )
        ).click();
    }
}