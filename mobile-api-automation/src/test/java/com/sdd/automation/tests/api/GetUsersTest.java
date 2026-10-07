package com.sdd.automation.tests.api;

import com.sdd.automation.api.ApiRequestSpecification;
import com.sdd.automation.constants.ApiConstants;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetUsersTest {

    @Test
    public void verifyUserById() {

        Response response =
                given()
                        .spec(ApiRequestSpecification.getRequestSpecification())
                .when()
                        .get(ApiConstants.USERS_ENDPOINT + "?page=2")
                .then()
                        .extract()
                        .response();

        Assert.assertEquals(
                response.statusCode(),
                ApiConstants.SUCCESS_OK,
                "Expected status code 200"
        );

        String firstName = response.jsonPath()
                .getString("data.find { it.id == 10 }.first_name");

        Assert.assertEquals(
                firstName,
                "Byron",
                "First name for user ID 10 is incorrect"
        );
    }
}