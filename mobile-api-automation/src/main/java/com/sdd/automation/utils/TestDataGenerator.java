package com.sdd.automation.utils;

public final class TestDataGenerator {

    private TestDataGenerator() {
    }

    public static String createUserRequest(String name, String job) {

        return String.format(
                """
                {
                    "name": "%s",
                    "job": "%s"
                }
                """,
                name,
                job
        );
    }
}