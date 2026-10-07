package com.sdd.automation.tests.mobile;

import com.sdd.automation.driver.AppiumDriverManager;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class AppiumSmokeTest extends BaseMobileTest {

   /*  @Test
    public void verifyApplicationLaunches() {

        String currentPackage = (String) AppiumDriverManager.getDriver()
                .executeScript("mobile: getCurrentPackage");

        Assert.assertEquals(
                currentPackage,
                "io.selendroid.testapp",
                "Selendroid application was not launched"
        );
    }*/
   @Test
public void verifyApplicationLaunches() {

    AppiumDriverManager.getDriver()
            .executeScript(
                    "mobile: activateApp",
                    Map.of("appId", "io.selendroid.testapp")
            );

    String currentPackage = (String) AppiumDriverManager.getDriver()
            .executeScript("mobile: getCurrentPackage");

    Assert.assertEquals(
            currentPackage,
            "io.selendroid.testapp",
            "Selendroid application was not launched"
    );
}
  
}