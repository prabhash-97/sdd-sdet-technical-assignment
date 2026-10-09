package com.sdd.automation.tests.mobile;

import com.sdd.automation.driver.AppiumDriverManager;
import com.sdd.automation.pages.mobile.HomePage;
import com.sdd.automation.pages.mobile.LanguagePage;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LanguageSelectionTest extends BaseMobileTest {

    @Test
    public void selectNoNoAndVerifyHomeScreen() {
        AppiumDriver driver = AppiumDriverManager.getDriver();

        HomePage homePage = new HomePage(driver);
        LanguagePage languagePage = new LanguagePage(driver);

        Assert.assertEquals(
                homePage.getTitle(),
                "selendroid-test-app",
                "Home screen is not displayed"
        );

        languagePage.tapLanguageButton();

        Assert.assertEquals(
                languagePage.getDialogMessage(),
                "This will end the activity",
                "Unexpected language confirmation dialog"
        );

        languagePage.selectNoNo();

        Assert.assertEquals(
                homePage.getTitle(),
                "selendroid-test-app",
                "Home screen was not displayed after selecting No, no"
        );
    }
}
