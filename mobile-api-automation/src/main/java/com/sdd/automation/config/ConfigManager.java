package com.sdd.automation.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigManager {

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream input = ConfigManager.class
                .getClassLoader()
                .getResourceAsStream("config/config.properties")) {

            if (input == null) {
                throw new RuntimeException("config.properties not found");
            }

            PROPERTIES.load(input);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration", e);
        }
    }

    private ConfigManager() {
    }

    public static String get(String key) {
        String value = PROPERTIES.getProperty(key);

        if (value == null || value.isBlank()) {
            throw new RuntimeException("Missing configuration property: " + key);
        }

        return value;
    }
}