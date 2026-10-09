package com.sdd.automation.tests.mobile;

import com.sdd.automation.driver.AppiumDriverManager;
import com.sdd.automation.pages.mobile.HomePage;
import com.sdd.automation.pages.mobile.RegistrationPage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserRegistrationTest extends BaseMobileTest {

    @Test
    public void verifyUserRegistrationFormAndSubmit() {
        AppiumDriver driver = AppiumDriverManager.getDriver();

        HomePage homePage = new HomePage(driver);
        RegistrationPage registrationPage =
            new RegistrationPage(driver);

        Assert.assertEquals(
            homePage.getTitle(),
            "selendroid-test-app"
        );

        driver.findElement(
            By.id("io.selendroid.testapp:id/startUserRegistration")
        ).click();

        registrationPage.waitForPage();

        Assert.assertEquals(
            registrationPage.getDefaultName(),
            "Mr. Burns"
        );

        Assert.assertEquals(
            registrationPage.getSelectedLanguage(),
            "Ruby"
        );

        // Fill in the registration fields.
        //registrationPage.enterUsername("KTest@com");
        registrationPage.enterName("KTest");
        registrationPage.enterEmail("sdd.test@example.com");
        registrationPage.enterPassword("Passw0rd123!");

        registrationPage.selectRuby();
        registrationPage.acceptTerms();

        String checked = driver.findElement(
            By.id("io.selendroid.testapp:id/input_adds")
        ).getAttribute("checked");

        Assert.assertEquals(
            checked,
            "true",
            "Acceptance checkbox should be checked"
        );

        registrationPage.tapRegisterUser();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        System.out.println("===== AFTER REGISTRATION SUBMISSION =====");
    }
}