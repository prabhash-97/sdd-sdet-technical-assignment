package com.sdd.automation.tests.mobile;

import com.sdd.automation.driver.AppiumDriverManager;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseMobileTest {

    private static final String APP_PACKAGE = "io.selendroid.testapp";

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        AppiumDriverManager.initializeDriver();

        AppiumDriver driver = AppiumDriverManager.getDriver();

        driver.executeScript(
                "mobile: activateApp",
                java.util.Map.of("appId", APP_PACKAGE)
        );

        handleAndroidStartupDialogs(driver);

        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[@text='selendroid-test-app']")
                ));
    }

    private void handleAndroidStartupDialogs(AppiumDriver driver) {
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));

        clickIfPresent(
            driver,
            shortWait,
            By.id("com.android.permissioncontroller:id/continue_button")
        );

        clickIfPresent(
            driver,
            shortWait,
            By.xpath("//*[@text='OK']")
        );
    }

    private void clickIfPresent(
            AppiumDriver driver,
            WebDriverWait wait,
            By locator) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        } catch (org.openqa.selenium.TimeoutException
                | org.openqa.selenium.NoSuchElementException ignored) {
        }
    }
        @AfterMethod(alwaysRun = true)
        public void tearDown() {
            AppiumDriverManager.quitDriver();
        }
}
