package com.sdd.automation.driver;

import com.sdd.automation.config.ConfigManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;

public final class AppiumDriverManager {

    private static AppiumDriver driver;

    private AppiumDriverManager() {
    }

    public static void initializeDriver() {

        try {
            UiAutomator2Options options = new UiAutomator2Options();

            options.setPlatformName(ConfigManager.get("platform.name"));
            options.setAutomationName(ConfigManager.get("automation.name"));
            options.setDeviceName(ConfigManager.get("device.name"));

            options.setApp(ConfigManager.get("app.path"));

            options.setAppPackage(ConfigManager.get("app.package"));
            options.setAppActivity(ConfigManager.get("app.activity"));

            options.setAutoGrantPermissions(true);

            driver = new AndroidDriver(
                    URI.create(ConfigManager.get("appium.server.url"))
                            .toURL(),
                    options
            );

            driver.manage()
                    .timeouts()
                    .implicitlyWait(
                            Duration.ofSeconds(
                                    Long.parseLong(
                                            ConfigManager.get("implicit.wait")
                                    )
                            )
                    );

        } catch (MalformedURLException e) {
            throw new RuntimeException(
                    "Invalid Appium server URL",
                    e
            );
        }
    }

    public static AppiumDriver getDriver() {

        if (driver == null) {
            throw new IllegalStateException(
                    "Appium driver has not been initialized"
            );
        }

        return driver;
    }

    public static void quitDriver() {

        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}