package com.sdd.automation.pages.mobile;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {

    private final AppiumDriver driver;
    private final WebDriverWait wait;

    private final By usernameField =
        By.id("io.selendroid.testapp:id/inputUsername");

    private final By nameField =
        By.id("io.selendroid.testapp:id/inputName");

    private final By emailField =
        By.id("io.selendroid.testapp:id/inputEmail");

    private final By passwordField =
        By.id("io.selendroid.testapp:id/inputPassword");

    private final By languageSpinner =
        By.id("io.selendroid.testapp:id/input_preferedProgrammingLanguage");

    private final By registerButton =
        By.id("io.selendroid.testapp:id/btnRegisterUser");

    public RegistrationPage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
            driver, Duration.ofSeconds(20)
        );
    }

    public void enterUsername(String username) {
        enterText(usernameField, username);
    }

    public String getDefaultName() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(nameField)
        ).getText();
    }

    public String getSelectedLanguage() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.id("android:id/text1")
            )
        ).getText();
    }

    public void enterEmail(String email) {
        enterText(emailField, email);
    }

    public void enterPassword(String password) {
        enterText(passwordField, password);
    }

    public void enterName(String name) {
        enterText(nameField, name);
    }

    private void enterText(By locator, String value) {
        WebElement element = wait.until(
            ExpectedConditions.elementToBeClickable(locator)
        );
        element.click();
        element.clear();
        element.sendKeys(value);
    }

    public void selectRuby() {
        wait.until(
            ExpectedConditions.elementToBeClickable(languageSpinner)
        ).click();

        wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@text='Ruby']")
            )
        ).click();
    }

    public void acceptTerms() {
        WebElement checkbox = wait.until(
            ExpectedConditions.presenceOfElementLocated(
                By.id("io.selendroid.testapp:id/input_adds")
            )
        );

        if (!"true".equalsIgnoreCase(checkbox.getAttribute("checked"))) {
            checkbox.click();
        }

        wait.until(driver ->
            "true".equalsIgnoreCase(
                driver.findElement(
                    By.id("io.selendroid.testapp:id/input_adds")
                ).getAttribute("checked")
            )
        );
    }

    public void waitForPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.id("io.selendroid.testapp:id/inputUsername")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.id("io.selendroid.testapp:id/inputName")));
    }

    public void tapRegisterUser() {
        driver.findElement(
            io.appium.java_client.AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                ".scrollIntoView(new UiSelector().resourceId(" +
                "\"io.selendroid.testapp:id/btnRegisterUser\"))"
            )
        );
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }
}