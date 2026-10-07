Feature: User API validation

  Scenario: Verify user details from page 2
    Given I send a GET request to the users endpoint for page 2
    Then the response status code should be 200
    And the user with id 10 should have first name "Byron"

  Scenario: Create a new user
    Given I prepare a new user with name "Bryant" and job "BA"
    When I send a POST request to the users endpoint
    Then the response status code should be 201
    And the response should contain a generated user id