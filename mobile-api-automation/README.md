# Mobile and API Automation

Java Maven automation framework for REST API, Cucumber BDD, and Android mobile testing.

## Technology Stack

- Java 17
- Appium for Android automation
- REST Assured for API testing
- TestNG for test execution
- Cucumber for BDD
- Maven for dependency and build management
- Allure for reporting
- Log4j2 for logging

## Framework Components

### API Automation

The API framework supports:

- HTTP status code validation.
- Response field assertions.
- API chaining.
- Test data generation.
- JSON schema validation.
- Reusable API configuration and endpoint constants.

### BDD Automation

Cucumber feature files are located under:

```text
src/test/resources/features/
```

Step definitions and the TestNG Cucumber runner are maintained separately.

### Mobile Automation

Appium automates the supplied Selendroid Android application. A dedicated driver manager and base test class manage driver setup, common initialization, and cleanup.

## Prerequisites

- JDK 17
- Maven
- Android SDK and ADB
- Android emulator or physical device
- Appium server
- Required Appium Android driver
- Allure CLI for local report generation

Verify Android connectivity:

```bash
adb devices
```

Ensure the Appium server is running before executing mobile tests.

## Execute Tests

### Configured TestNG Suite

```bash
mvn test
```

The default Maven Surefire configuration uses `testng.xml`. Check this file to confirm which mobile, API, and Cucumber tests are included.

### Mobile-Only Suite

```bash
mvn test -Dsurefire.suiteXmlFiles=testng-mobile.xml
```

Use this command when the mobile-specific suite is configured in `testng-mobile.xml`.

## Reporting

### Allure Dashboard

After executing the selected suite:

```bash
allure generate target/allure-results \
  --output target/allure-report \
  --clean

allure open target/allure-report
```

### Mobile Failure Attachments

When implemented by the Allure listener, mobile failures can include:

- Execution summary.
- Failure message and stack trace.
- Screenshot captured from the Android device.
- Additional diagnostic information.

The screenshot should be attached to the failed test result, not merely saved to a separate directory.

### API Diagnostics

API test reporting can include:

- HTTP method and endpoint.
- Request headers and body, where appropriate.
- Response status code.
- Response body.
- Assertion failure details.
- Stack trace and execution summary.

Request and response information must be attached by the API test code or a dedicated reporting filter. Avoid including passwords, access tokens, or other secrets in report attachments.

### Cucumber Reports

The configured Cucumber runner generates:

```text
target/cucumber-report.html
target/cucumber-report.json
```

These are separate from Allure. To include individual Cucumber scenarios in Allure, the Cucumber Allure adapter must be added as a dependency and configured as a Cucumber plugin.

### Report Locations

| Output | Location |
|---|---|
| Allure raw results | `target/allure-results/` |
| Generated Allure dashboard | `target/allure-report/` |
| Surefire reports | `target/surefire-reports/` |
| Cucumber HTML | `target/cucumber-report.html` |
| Cucumber JSON | `target/cucumber-report.json` |
| Logs | `target/logs/`, if configured |

## Troubleshooting

- **No mobile device detected:** Check `adb devices` and emulator status.
- **Appium connection failure:** Confirm the Appium server URL and required driver installation.
- **Missing screenshots:** Verify the listener captures screenshots before the driver is closed and attaches them to Allure.
- **Missing API tests in Allure:** Confirm the full suite ran and that the tests are included in the configured TestNG suite.
- **Missing Cucumber scenarios in Allure:** Verify the Allure Cucumber adapter and plugin configuration.
- **Empty Allure report:** Check that `target/allure-results/` contains result JSON files and attachments.