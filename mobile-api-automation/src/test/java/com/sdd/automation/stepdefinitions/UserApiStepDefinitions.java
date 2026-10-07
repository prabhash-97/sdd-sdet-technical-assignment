package com.sdd.automation.stepdefinitions;

import com.sdd.automation.api.ApiRequestSpecification;
import com.sdd.automation.constants.ApiConstants;
import com.sdd.automation.utils.TestDataGenerator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import org.testng.Assert;

import org.apache.logging.log4j.Logger;
import com.sdd.automation.utils.LoggerUtil;

public class UserApiStepDefinitions {
    private static final Logger LOGGER =
        LoggerUtil.getLogger(UserApiStepDefinitions.class);
    private Response response;
    private String requestBody;

   @Given("I send a GET request to the users endpoint for page 2")
   public void sendGetRequest() {

        LOGGER.info("Sending GET request to users endpoint - page 2");

        response =
                given()
                        .spec(ApiRequestSpecification.getRequestSpecification())
                .when()
                        .get(ApiConstants.USERS_ENDPOINT + "?page=2");

        LOGGER.info(
                "GET request completed with status code: {}",
                response.statusCode()
        );
   }

    @Then("the response status code should be {int}")
    public void verifyStatusCode(int expectedStatusCode) {

        Assert.assertEquals(
                response.statusCode(),
                expectedStatusCode,
                "Unexpected response status code"
        );
    }

    @Then("the user with id {int} should have first name {string}")
    public void verifyUserFirstName(
            int userId,
            String expectedFirstName) {

        String actualFirstName =
                response.jsonPath()
                        .getString(
                                "data.find { it.id == "
                                        + userId
                                        + " }.first_name"
                        );

        Assert.assertEquals(
                actualFirstName,
                expectedFirstName,
                "User first name is incorrect"
        );
    }

    @Given("I prepare a new user with name {string} and job {string}")
    public void prepareNewUser(
            String name,
            String job) {

        requestBody =
                TestDataGenerator.createUserRequest(
                        name,
                        job
                );
    }

    @When("I send a POST request to the users endpoint")
    public void sendPostRequest() {

        LOGGER.info("Sending POST request to create a new user");

        response =
                given()
                        .spec(ApiRequestSpecification.getRequestSpecification())
                        .body(requestBody)
                .when()
                        .post(ApiConstants.USERS_ENDPOINT);

        LOGGER.info(
                "POST request completed with status code: {}",
                response.statusCode()
        );
        }

    @Then("the response should contain a generated user id")
    public void verifyGeneratedId() {

        String generatedId =
                response.jsonPath().getString("id");

        LOGGER.info("Generated user ID: {}", generatedId);

        Assert.assertNotNull(
                generatedId,
                "Generated user ID should not be null"
        );

        Assert.assertFalse(
                generatedId.isBlank(),
                "Generated user ID should not be empty"
        );
}
}