
package com.sdd.automation.tests.mobile;

import com.sdd.automation.driver.AppiumDriverManager;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class AppiumSmokeTest extends BaseMobileTest {

    @Test
    public void verifyApplicationLaunches() {
        AppiumDriver driver = AppiumDriverManager.getDriver();

        driver.executeScript(
                "mobile: activateApp",
                Map.of("appId", "io.selendroid.testapp")
        );

        Assert.assertEquals(
                driver.getCapabilities().getCapability("appium:appPackage"),
                "io.selendroid.testapp",
                "Unexpected configured application package"
        );
    }
}
