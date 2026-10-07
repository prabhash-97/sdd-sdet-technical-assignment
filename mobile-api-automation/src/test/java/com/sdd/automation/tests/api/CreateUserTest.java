package com.sdd.automation.tests.api;

import com.sdd.automation.api.ApiRequestSpecification;
import com.sdd.automation.constants.ApiConstants;
import com.sdd.automation.utils.TestDataGenerator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateUserTest {

    @Test
    public void createUser() {

        String requestBody =
                TestDataGenerator.createUserRequest(
                        "Bryant",
                        "BA"
                );

        Response response =
                given()
                        .spec(ApiRequestSpecification.getRequestSpecification())
                        .body(requestBody)
                .when()
                        .post(ApiConstants.USERS_ENDPOINT)
                .then()
                        .extract()
                        .response();

        Assert.assertEquals(
                response.statusCode(),
                ApiConstants.SUCCESS_CREATED,
                "Expected status code 201"
        );

        Assert.assertNotNull(
                response.jsonPath().getString("id"),
                "Generated user ID should not be null"
        );

        Assert.assertEquals(
                response.jsonPath().getString("name"),
                "Bryant",
                "User name is incorrect"
        );

        Assert.assertEquals(
                response.jsonPath().getString("job"),
                "BA",
                "User job is incorrect"
        );
    }
}