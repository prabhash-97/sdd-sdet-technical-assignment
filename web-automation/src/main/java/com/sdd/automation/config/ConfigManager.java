package com.sdd.automation.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigManager {

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream input = ConfigManager.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new IllegalStateException(
                        "config.properties not found in src/test/resources"
                );
            }

            PROPERTIES.load(input);

        } catch (IOException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private ConfigManager() {
    }

    public static String getBaseUrl() {
        String baseUrl = System.getProperty("BASE_URL");

        if (baseUrl == null || baseUrl.isBlank()) {
            baseUrl = PROPERTIES.getProperty("BASE_URL");
        }

        if (baseUrl == null || baseUrl.isBlank()) {
            throw new IllegalStateException(
                    "BASE_URL is missing in config.properties"
            );
        }

        return baseUrl.trim().replaceAll("/+$", "");
    }
}
