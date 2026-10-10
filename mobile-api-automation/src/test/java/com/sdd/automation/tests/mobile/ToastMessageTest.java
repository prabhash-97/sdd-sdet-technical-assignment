
package com.sdd.automation.tests.mobile;

import com.sdd.automation.driver.AppiumDriverManager;
import com.sdd.automation.pages.mobile.HomePage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class ToastMessageTest extends BaseMobileTest {

    @Test
    public void verifyToastMessageIsDisplayed() {
        AppiumDriver driver = AppiumDriverManager.getDriver();
        HomePage homePage = new HomePage(driver);

        Assert.assertEquals(
            homePage.getTitle(),
            "selendroid-test-app",
            "Home screen should be displayed"
        );

        homePage.tapToastButton();

        WebDriverWait wait = new WebDriverWait(
            driver, Duration.ofSeconds(10)
        );

        WebElement toast = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath(
                    "//*[contains(@text,'toast') or contains(@text,'Toast')]"
                )
            )
        );

        String toastText = toast.getText();

        Assert.assertFalse(
            toastText.isBlank(),
            "Toast message should not be empty"
        );

        System.out.println("Toast message received: " + toastText);
    }
}
