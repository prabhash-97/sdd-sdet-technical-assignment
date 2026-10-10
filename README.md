# SDD Senior SDET Technical Assignment

A multi-layer test automation solution covering **Web UI, REST API, and Android Mobile testing**, supported by manual test documentation, automated reporting, and CI/CD integration.

## Table of Contents

- [Project Overview](#project-overview)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Framework Design](#framework-design)
- [Web Automation](#web-automation)
- [API Automation](#api-automation)
- [Mobile Automation](#mobile-automation)
- [Reporting and Test Evidence](#reporting-and-test-evidence)
- [CI/CD Pipeline](#cicd-pipeline)
- [Manual Testing](#manual-testing)
- [Execution Instructions](#execution-instructions)
- [Test Results](#test-results)
- [Deliverables](#deliverables)

## Project Overview

This repository demonstrates a maintainable test automation approach across three testing layers:

- **Web UI automation** using Playwright and Java.
- **REST API automation** using REST Assured, TestNG, and Cucumber.
- **Android mobile automation** using Appium, UiAutomator2, and TestNG.
- Manual test cases and execution documentation.
- Allure reporting, execution logs, and test evidence.
- GitHub Actions workflows for automated test execution.

The solution emphasizes reusable components, clear test reporting, maintainability, and traceable execution evidence.

## Technology Stack

| Area | Technologies |
|---|---|
| Programming language | Java 17 |
| Build management | Maven |
| Web automation | Playwright |
| API automation | REST Assured |
| Test framework | TestNG |
| BDD | Cucumber |
| Mobile automation | Appium, UiAutomator2 |
| Reporting | Allure Report, Cucumber HTML, Maven Surefire |
| CI/CD | GitHub Actions |
| Version control | Git, GitHub |
| Mobile execution | Android Emulator |

## Project Structure

```text
sdd-sdet-technical-assignment/
├── .github/
│   └── workflows/
│       ├── ci.yml
│       └── sdet-tests.yml
├── web-automation/
│   ├── src/
│   ├── pom.xml
│   └── README.md
├── mobile-api-automation/
│   ├── src/
│   ├── testng.xml
│   ├── testng-api.xml
│   ├── pom.xml
│   └── README.md
├── test-evidence/
│   ├── web/
│   ├── api/
│   ├── mobile/
│   └── manual/
├── README.md
└── .gitignore
```

The structure above highlights the main project components. The `test-evidence/` folders should contain the actual screenshots and supporting evidence captured during execution.

## Framework Design

The automation solution follows these principles:

- **Separation of concerns:** test logic is separated from page interactions and supporting utilities where applicable.
- **Reusability:** shared setup, configuration, and utilities reduce duplication.
- **Readable tests:** descriptive test names and scenarios communicate expected behavior.
- **Maintainability:** organized test classes and configuration simplify updates.
- **Traceability:** reports, logs, and screenshots support execution review and troubleshooting.
- **CI/CD integration:** GitHub Actions supports repeatable Web and API test execution.

## Web Automation

**Technologies:** Java, Playwright, TestNG, Maven, Allure.

The Web automation module validates UI behavior and interactions through automated browser tests.

### Test coverage

The implemented scenarios include:

- Drag-and-drop interactions.
- Selectable items.
- Go Green interaction.
- Resizable box behavior.
- Control group interactions.
- jQuery UI home page validation.
- Additional scenarios implemented in the web test suite.

### Execute Web tests

```bash
cd web-automation
mvn clean test
```

### Latest recorded local result

| Metric | Result |
|---|---:|
| Total tests | 8 |
| Passed | 8 |
| Failed | 0 |
| Errors | 0 |
| Skipped | 0 |

## API Automation

**Technologies:** Java, REST Assured, TestNG, Cucumber, Maven, Allure.

The API automation module validates HTTP responses, endpoint behavior, and response data.

### Test coverage

- GET request execution and HTTP status validation.
- User endpoint validation, including page-based user data.
- Response data and field assertions.
- Cucumber feature scenarios and step definitions.
- Dedicated TestNG suite execution for API tests.

### Execute API tests

```bash
cd mobile-api-automation
mvn clean test -Dsurefire.suiteXmlFiles=testng-api.xml
```

### Latest recorded local result

| Metric | Result |
|---|---:|
| Total tests | 6 |
| Passed | 6 |
| Failed | 0 |
| Errors | 0 |
| Skipped | 0 |

The reported count reflects the recorded local execution. Check the generated report for the exact scenarios executed by the selected suite.

## Mobile Automation

**Technologies:** Java, Appium, UiAutomator2, TestNG, Maven, Allure.

The Mobile automation module validates Android application launch, UI interactions, navigation, and application behavior under normal and exception scenarios.

### Automated test scenarios

| Test class | Scenario |
|---|---|
| `AppiumSmokeTest` | `verifyApplicationLaunches` — verifies successful application launch |
| `ChromeWebViewTest` | Validates Chrome WebView interactions |
| `HomeScreenTest` | Validates home screen behavior |
| `LanguageSelectionTest` | Validates language selection |
| `PopupWindowTest` | Validates popup window handling |
| `ProgressBarTest` | Validates progress bar behavior |
| `ToastMessageTest` | Validates toast message behavior |
| `UnhandledExceptionButtonTest` | `verifyHomeScreenAfterUnhandledExceptionButton` — exercises the unhandled-exception button and verifies the home screen title |
| `UnhandledExceptionInputTest` | `verifyHomeScreenAfterTypingExceptionInput` — exercises exception-triggering input and verifies the resulting application behavior |

### Intentional failure scenario

The assignment specifies the following failure scenario:

1. Launch the application.
2. Tap **Press to throw unhandled exception**.
3. Verify the home screen title.

This scenario deliberately exercises the application's behavior after an unhandled exception. If the application does not return to the expected home screen state, the title assertion fails.

The resulting test failure is retained as evidence of the observed behavior. It should not automatically be treated as a defect in the automation framework.

**Reporting distinction:** the scenario is intentionally designed to exercise an exception, but a failed assertion remains a failed test in TestNG and Allure. The expected scenario outcome and the actual test result should be documented separately.

### Mobile execution results

The reported mobile execution contains 10 test cases, with 8 passing and 2 failing.

| Metric | Result |
|---|---:|
| Total tests | 10 |
| Passed | 8 |
| Failed | 2 |
| Skipped | 0 |
| Pass rate | 80% |

The two failures are associated with the intended unhandled-exception scenarios as specified for this assignment.

### Mobile prerequisites

- Android SDK and a compatible Android emulator or device.
- Appium server.
- Required Appium driver, such as UiAutomator2.
- Correct device capabilities and application configuration.

## Reporting and Test Evidence

Detailed test evidence is maintained separately from this README to keep the main documentation concise.

### Evidence directory

```text
test-evidence/
├── web/
│   ├── web-test-execution.png
│   ├── web-allure-overview.png
│   └── web-allure-test-details.png
├── api/
│   ├── api-test-execution.png
│   ├── api-allure-overview.png
│   └── api-response-validation.png
├── mobile/
│   ├── mobile-test-execution.png
│   ├── mobile-allure-overview.png
│   ├── mobile-allure-failures.png
│   └── mobile-app-execution.png
└── manual/
    ├── test-cases.xlsx
    ├── test-execution-summary.png
    └── defect-evidence/
```

These are suggested filenames. Add the actual evidence files and adjust the filenames to match them.

### Reporting tools

- **Allure Report:** suite summaries, individual test results, execution durations, and failure details.
- **Maven Surefire:** TestNG execution summaries.
- **Cucumber HTML report:** readable BDD scenario results.
- **Appium logs:** device interactions and mobile driver diagnostics.
- **Screenshots and video:** visual evidence of execution and failures.

### Local report locations

Typical generated report paths include:

```text
web-automation/target/allure-report/index.html
mobile-api-automation/target/allure-report/index.html
mobile-api-automation/target/cucumber-report.html
```

The actual paths depend on the reporting configuration and execution commands.

To view a local HTML report, open the generated `index.html` or report file in a browser.

Allure reports and screenshots should be captured after running the tests. Local links such as `127.0.0.1` are not accessible to reviewers outside your computer, so include screenshots in the repository and publish CI report artifacts where configured.

## CI/CD Pipeline

GitHub Actions workflows are maintained in `.github/workflows/`.

### Main workflow: `ci.yml`

The main workflow handles Web and API automation.

Configured capabilities include:

- Automatic execution on configured push and pull-request events.
- Manual execution through `workflow_dispatch`.
- Java setup and dependency installation.
- Web and API test execution.
- Allure report generation and artifact publication where configured.

### Android workflow: `sdet-tests.yml`

The separate Android workflow is retained for the mobile automation pipeline.

Current configuration:

- Manual triggering through `workflow_dispatch`.
- The Android job has `if: false` and is therefore skipped.

The Android workflow does not currently execute its test job until that condition is changed. A skipped job must not be described as a successful Android test run.

## Manual Testing

Manual test documentation is maintained separately from the automated test source code.

The manual-testing deliverable should include:

- Test case ID and title.
- Preconditions and test data.
- Execution steps.
- Expected results.
- Actual results.
- Pass/fail status.
- Defect references and supporting evidence where applicable.

Submit the completed Excel workbook separately if required by the assignment. Supporting screenshots and defect evidence can be organized under `test-evidence/manual/`.

## Execution Instructions

### Prerequisites

- Java 17.
- Maven.
- Git.
- Node.js if required by the configured Allure report-generation workflow.
- Android SDK, a compatible emulator or device, and Appium for mobile execution.

### Run Web tests

```bash
cd web-automation
mvn clean test
```

### Run API tests

```bash
cd mobile-api-automation
mvn clean test -Dsurefire.suiteXmlFiles=testng-api.xml
```

### Run the configured Mobile suite

From the `mobile-api-automation` directory:

```bash
mvn clean test
```

Ensure that Appium is running and the required Android device or emulator is available. Confirm the selected TestNG suite and configuration before execution.

The Android GitHub Actions job remains disabled until its job condition is updated.

## Test Results

The following table summarizes the latest local execution results recorded during development.

| Test layer | Total | Passed | Failed | Skipped |
|---|---:|---:|---:|---:|
| Web automation | 8 | 8 | 0 | 0 |
| API automation | 6 | 6 | 0 | 0 |
| Mobile automation | 10 | 8 | 2 | 0 |

The Web and API results show successful recorded local executions. The Mobile suite includes two intended unhandled-exception scenarios whose assertions failed, giving a reported pass rate of 80%.

These results reflect the recorded local runs and do not guarantee the status of the latest GitHub Actions run. Consult the corresponding reports for execution details.

## Deliverables

- Web UI automation framework and source code.
- REST API automation framework and source code.
- Android mobile automation implementation.
- GitHub Actions workflow configuration.
- Manual test cases and execution documentation.
- Allure and other test reports.
- Screenshots and supporting test evidence.

## Notes

- Do not commit credentials, access tokens, passwords, or sensitive test data.
- Avoid committing generated build directories such as `target/`.
- Keep screenshots and videos relevant, clearly named, and reasonably sized.
- Use genuine execution evidence rather than illustrative images.
- Preserve the two intentional mobile failure results accurately in the reports.
- Update the results table whenever a new verified execution changes the recorded results.