
package com.sdd.automation.tests.mobile;

import com.sdd.automation.driver.AppiumDriverManager;
import com.sdd.automation.pages.mobile.HomePage;
import com.sdd.automation.pages.mobile.ChromeWebViewPage;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ChromeWebViewTest extends BaseMobileTest {

    @Test
    public void verifyChromeWebViewNameAndPreferredCar() {

        AppiumDriver driver = AppiumDriverManager.getDriver();

        HomePage homePage = new HomePage(driver);
        ChromeWebViewPage webViewPage = new ChromeWebViewPage(driver);

        Assert.assertEquals(
            homePage.getTitle(),
            "selendroid-test-app",
            "Home screen title is incorrect."
        );

        driver.findElement(
            By.id("io.selendroid.testapp:id/buttonStartWebview")
        ).click();

        webViewPage.waitForPage();

        Assert.assertTrue(
            webViewPage.getIntroductionText().contains("Hello"),
            "Introduction text was not displayed."
        );

        webViewPage.enterName("KTest");
        webViewPage.selectCar("Mercedes");

        webViewPage.submitName();

        String confirmation = webViewPage.getConfirmationText();

        System.out.println("===== CONFIRMATION SCREEN =====");

        Assert.assertTrue(
            confirmation.contains("KTest"),
            "Confirmation should contain the entered name."
        );
        System.out.println("===== CAR SELECTOR PAGE SOURCE =====");

        Assert.assertTrue(
            confirmation.toLowerCase().contains("mercedes"),
            "Confirmation should contain Mercedes."
        );

        webViewPage.clickHereLink();

        Assert.assertEquals(
            webViewPage.getSelectedCar(),
            "Volvo",
            "Default preferred car should be Volvo."
        );
    }
}
