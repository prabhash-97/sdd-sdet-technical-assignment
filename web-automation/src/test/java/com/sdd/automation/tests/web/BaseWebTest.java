package com.sdd.automation.tests.web;

import com.sdd.automation.driver.PlaywrightManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseWebTest {

    @BeforeMethod
    public void setUp() {
        PlaywrightManager.initialize();
    }

    @AfterMethod
    public void tearDown() {
        PlaywrightManager.close();
    }
}