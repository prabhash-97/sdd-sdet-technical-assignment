
package com.sdd.automation.tests.mobile;

import com.sdd.automation.driver.AppiumDriverManager;
import com.sdd.automation.pages.mobile.HomePage;
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
public class ProgressBarTest extends BaseMobileTest {

    @Test
    public void verifyProgressBarDisappearsAndRegistrationScreenOpens() {
        AppiumDriver driver = AppiumDriverManager.getDriver();
        HomePage homePage = new HomePage(driver);

        Assert.assertEquals(
            homePage.getTitle(),
            "selendroid-test-app",
            "Home screen should be displayed"
        );

        homePage.tapProgressButton();

        WebDriverWait wait = new WebDriverWait(
            driver, Duration.ofSeconds(30)
        );

        // Wait for the registration screen to appear after loading.
        By registrationHeading =
            By.xpath("//*[@text='Welcome to register a new User']");

        wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                registrationHeading
            )
        );

        // Verify important registration form elements.
        Assert.assertTrue(
            driver.findElement(
                By.id("io.selendroid.testapp:id/inputUsername")
            ).isDisplayed(),
            "Username field should be displayed"
        );

        Assert.assertTrue(
            driver.findElement(
                By.id("io.selendroid.testapp:id/inputEmail")
            ).isDisplayed(),
            "Email field should be displayed"
        );

        Assert.assertTrue(
            driver.findElement(
                By.id("io.selendroid.testapp:id/inputPassword")
            ).isDisplayed(),
            "Password field should be displayed"
        );

        Assert.assertTrue(
            driver.findElement(
                By.id("io.selendroid.testapp:id/inputName")
            ).isDisplayed(),
            "Name field should be displayed"
        );

        System.out.println(
            "PASS: Progress flow completed and registration screen is displayed."
        );
    }
}