# Web Automation Framework

## Overview

This module contains automated Web UI tests using Java, Playwright, TestNG, Maven, and Allure reporting.

## Technology Stack

- Java 17
- Maven
- Playwright for Java
- TestNG
- Allure Report

## Prerequisites

Install the following:

- Java 17
- Maven
- Git

Verify the installations:

```bash
java -version
mvn -version
```

## Run Web Tests

Navigate to the Web automation module:

```bash
cd web-automation
```

Execute the test suite:

```bash
mvn clean test
```

Maven compiles the project and executes the configured tests.

## Test Coverage

The Web automation suite includes scenarios such as:

- Drag-and-drop interactions
- Selectable items
- Go Green interaction
- Resizable box behavior
- Control group interactions
- jQuery UI home page validation

Refer to the test source files for the complete list of implemented scenarios.

## Test Results

Latest recorded local execution:

| Metric | Count |
|---|---:|
| Total tests | 8 |
| Passed | 8 |
| Failed | 0 |
| Errors | 0 |
| Skipped | 0 |

## Test Reporting

The project uses Allure reporting and Maven Surefire test results.

Typical output locations:

```text
target/allure-results/
target/allure-report/
target/surefire-reports/
```

If the Allure HTML report has been generated, open it in a browser:

```text
target/allure-report/index.html
```

To generate and open an Allure report using the Allure CLI, if installed:

```bash
allure serve target/allure-results
```

## Troubleshooting

- Confirm Java 17 and Maven are installed.
- Check the Maven console output if tests fail.
- Verify browser dependencies and Playwright configuration.
- Review Surefire reports and Allure results for failure details.

## Notes

Test results represent the execution run in which they were captured. Check the latest test report for current results.