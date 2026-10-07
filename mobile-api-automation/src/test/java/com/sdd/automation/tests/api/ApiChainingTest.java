package com.sdd.automation.tests.api;

import com.sdd.automation.api.ApiRequestSpecification;
import com.sdd.automation.constants.ApiConstants;
import com.sdd.automation.utils.TestDataGenerator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiChainingTest {

    @Test
    public void createUserUsingApiChaining() {

        // Step 1: Get an existing user
        Response getResponse =
                given()
                        .spec(ApiRequestSpecification.getRequestSpecification())
                .when()
                        .get(ApiConstants.USERS_ENDPOINT + "?page=2")
                .then()
                        .statusCode(ApiConstants.SUCCESS_OK)
                        .extract()
                        .response();

        // Step 2: Extract data from previous API response
        String firstName = getResponse.jsonPath()
                .getString("data.find { it.id == 10 }.first_name");

        String userId = getResponse.jsonPath()
                .getString("data.find { it.id == 10 }.id");

        Assert.assertEquals(
                firstName,
                "Byron",
                "Expected user was not found"
        );

        Assert.assertNotNull(
                userId,
                "User ID should be available for chaining"
        );

        // Step 3: Dynamically create POST request using previous API data
        String requestBody =
                TestDataGenerator.createUserRequest(
                        firstName,
                        "BA-" + userId
                );

        // Step 4: Send POST request
        Response postResponse =
                given()
                        .spec(ApiRequestSpecification.getRequestSpecification())
                        .body(requestBody)
                .when()
                        .post(ApiConstants.USERS_ENDPOINT)
                .then()
                        .statusCode(ApiConstants.SUCCESS_CREATED)
                        .body(
                                matchesJsonSchemaInClasspath(
                                        "schemas/create-user-schema.json"
                                )
                        )
                        .extract()
                        .response();

        // Step 5: Validate generated ID
        String generatedId =
                postResponse.jsonPath().getString("id");

        Assert.assertNotNull(
                generatedId,
                "Generated ID should not be null"
        );

        Assert.assertFalse(
                generatedId.isBlank(),
                "Generated ID should not be empty"
        );

        // Step 6: Validate chained request data
        Assert.assertEquals(
                postResponse.jsonPath().getString("name"),
                firstName,
                "Chained user name is incorrect"
        );

        Assert.assertEquals(
                postResponse.jsonPath().getString("job"),
                "BA-" + userId,
                "Chained job value is incorrect"
        );
    }
}