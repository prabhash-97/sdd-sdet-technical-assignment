
package com.sdd.automation.tests.mobile;

import com.sdd.automation.driver.AppiumDriverManager;
import com.sdd.automation.pages.mobile.HomePage;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeScreenTest extends BaseMobileTest {

    @Test
    public void verifyHomeScreenTitleAndElements() {
        AppiumDriver driver = AppiumDriverManager.getDriver();

        driver.executeScript(
                "mobile: activateApp",
                java.util.Map.of("appId", "io.selendroid.testapp")
        );

        HomePage homePage = new HomePage(driver);

        Assert.assertEquals(
                homePage.getTitle(),
                "selendroid-test-app",
                "Home screen title does not match"
        );

        Assert.assertTrue(
                homePage.isGreetingDisplayed(),
                "Home screen greeting is not displayed"
        );

        Assert.assertTrue(homePage.isLanguageButtonDisplayed(),
                "EN button is not displayed");

        Assert.assertTrue(homePage.isChromeButtonDisplayed(),
                "Chrome/web-view button is not displayed");

        Assert.assertTrue(homePage.isRegistrationButtonDisplayed(),
                "Registration button is not displayed");

        Assert.assertTrue(homePage.isProgressButtonDisplayed(),
                "Progress button is not displayed");

        Assert.assertTrue(homePage.isToastButtonDisplayed(),
                "Toast button is not displayed");

        Assert.assertTrue(homePage.isPopupButtonDisplayed(),
                "Popup button is not displayed");

        Assert.assertTrue(homePage.isExceptionButtonDisplayed(),
                "Exception button is not displayed");

        Assert.assertTrue(homePage.isExceptionFieldDisplayed(),
                "Exception text field is not displayed");
    }
}
