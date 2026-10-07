package com.sdd.automation.api;

import com.sdd.automation.constants.ApiConstants;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public final class ApiRequestSpecification {

    private ApiRequestSpecification() {
    }

    public static RequestSpecification getRequestSpecification() {

        return new RequestSpecBuilder()
                .setBaseUri(ApiConstants.BASE_URL)
                .setContentType("application/json")
                .build();
    }
}