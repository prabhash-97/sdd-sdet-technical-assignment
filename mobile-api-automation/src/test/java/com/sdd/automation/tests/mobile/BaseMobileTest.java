package com.sdd.automation.tests.mobile;

import com.sdd.automation.driver.AppiumDriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseMobileTest {

    @BeforeMethod
    public void setUp() {
        AppiumDriverManager.initializeDriver();
    }

    @AfterMethod
    public void tearDown() {
        AppiumDriverManager.quitDriver();
    }
}