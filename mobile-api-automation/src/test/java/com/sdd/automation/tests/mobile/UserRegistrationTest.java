package com.sdd.automation.tests.mobile;

import com.sdd.automation.driver.AppiumDriverManager;
import com.sdd.automation.pages.mobile.HomePage;
import com.sdd.automation.pages.mobile.RegistrationPage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import com.sdd.automation.listeners.AllureTestListener;

@Listeners(AllureTestListener.class)
public class UserRegistrationTest extends BaseMobileTest {

    @Test
    public void verifyUserRegistrationFormAndSubmit() {
        AppiumDriver driver = AppiumDriverManager.getDriver();

        HomePage homePage = new HomePage(driver);
        RegistrationPage registrationPage =
            new RegistrationPage(driver);

        // Verify the home screen.
        Assert.assertEquals(
            homePage.getTitle(),
            "selendroid-test-app"
        );

        // Open registration.
        driver.findElement(
            By.id("io.selendroid.testapp:id/startUserRegistration")
        ).click();

        // Wait for the registration screen.
        registrationPage.waitForPage();

        // Verify default values.
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

        // Keep Ruby selected and accept the checkbox.
        registrationPage.selectRuby();
        registrationPage.acceptTerms();

        /*Assert.assertTrue(
            driver.findElement(
                By.id("io.selendroid.testapp:id/input_adds")
            ).isSelected(),
            "Acceptance checkbox should be checked"
        );*/

String checked = driver.findElement(
    By.id("io.selendroid.testapp:id/input_adds")
).getAttribute("checked");

Assert.assertEquals(
    checked,
    "true",
    "Acceptance checkbox should be checked"
);

        // Scroll to the Register User button and submit.
        registrationPage.tapRegisterUser();

        // Print the resulting screen so we can identify
        // the confirmation-page elements.
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        System.out.println("===== AFTER REGISTRATION SUBMISSION =====");
        System.out.println(driver.getPageSource());
    }
}

/*package com.sdd.automation.tests.mobile;

import com.sdd.automation.driver.AppiumDriverManager;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class UserRegistrationTest extends BaseMobileTest {

    @Test
    public void inspectRegistrationScreen() {
        AppiumDriver driver = AppiumDriverManager.getDriver();

        // Click the File / User Registration icon.
        driver.findElement(
            By.id("io.selendroid.testapp:id/startUserRegistration")
        ).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        System.out.println("===== CURRENT SCREEN SOURCE =====");
        System.out.println(driver.getPageSource());

        System.out.println("===== REGISTRATION ELEMENT COUNTS =====");

        System.out.println("Name field: " +
            driver.findElements(
                By.id("io.selendroid.testapp:id/inputName")
            ).size());

        System.out.println("Email field: " +
            driver.findElements(
                By.id("io.selendroid.testapp:id/inputEmail")
            ).size());

        System.out.println("Register button by ID: " +
            driver.findElements(
                By.id("io.selendroid.testapp:id/btnRegisterUser")
            ).size());

        System.out.println("Register button by text: " +
            driver.findElements(
                By.xpath("//*[contains(@text,'Register User')]")
            ).size());
    }
}*/


    /*@Test
    public void verifyUserRegistrationFormAndSubmit() {
        AppiumDriver driver = AppiumDriverManager.getDriver();

        HomePage homePage = new HomePage(driver);
        RegistrationPage registrationPage =
            new RegistrationPage(driver);

        // Verify home screen.
        Assert.assertEquals(
            homePage.getTitle(),
            "selendroid-test-app",
            "Home screen title is incorrect"
        );

        // Open the registration screen.
        driver.findElement(
            org.openqa.selenium.By.id(
                "io.selendroid.testapp:id/startUserRegistration"
            )
        ).click();

        // Verify the form controls.
        Assert.assertTrue(
            registrationPage.isRegistrationFormDisplayed(),
            "Registration form controls are not displayed"
        );

        // Verify default values.
        Assert.assertEquals(
            registrationPage.getDefaultName(),
            "Mr. Burns",
            "Unexpected default name"
        );

        Assert.assertEquals(
            registrationPage.getSelectedLanguage(),
            "Ruby",
            "Unexpected default programming language"
        );

        // Enter registration details.
        registrationPage.enterUsername("sdd_test_user");
        registrationPage.enterEmail("sdd.test@example.com");
        registrationPage.enterPassword("Passw0rd123!");
        registrationPage.enterName("Kasuni Test");

        // Explicitly select Ruby and accept the checkbox.
        registrationPage.selectRubyIfNeeded();
        registrationPage.acceptTerms();

        Assert.assertTrue(
            registrationPage.areTermsAccepted(),
            "The acceptance checkbox was not selected"
        );

        // Submit registration.
        registrationPage.tapRegisterUser();

        // Temporary diagnostic: inspect the confirmation screen.
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        System.out.println(
            "===== REGISTRATION RESULT SCREEN ====="
        );
        System.out.println(driver.getPageSource());
    }*/