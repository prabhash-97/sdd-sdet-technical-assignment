# SDD SDET Technical Assignment

A modular automation framework developed for the **Sharjah Digital Department (SDD) Senior Software Development Engineer in Test technical evaluation**.

The solution covers web UI automation, REST API testing, Android mobile automation, BDD, test reporting, logging, failure diagnostics, CI/CD, and manual testing deliverables.

## Technology Stack

| Area | Technology |
|---|---|
| Programming Language | Java 17 |
| Web UI Automation | Playwright |
| API Automation | REST Assured |
| Mobile Automation | Appium |
| Test Framework | TestNG |
| BDD | Cucumber |
| Build Tool | Maven |
| Reporting | Allure, Cucumber HTML/JSON, Maven Surefire |
| Logging | Log4j2 |
| CI/CD | GitHub Actions |
| Version Control | Git / GitHub |
| Design Pattern | Page Object Model (POM) |

## Project Structure

```text
sdd-sdet-technical-assignment/
├── .github/
│   └── workflows/
├── web-automation/
│   ├── pom.xml
│   ├── testng.xml
│   └── src/
│       ├── main/java/com/sdd/automation/
│       └── test/java/com/sdd/automation/
├── mobile-api-automation/
│   ├── pom.xml
│   ├── testng.xml
│   ├── testng-mobile.xml
│   ├── src/
│   │   ├── main/java/com/sdd/automation/
│   │   └── test/
│   │       ├── java/com/sdd/automation/
│   │       └── resources/
│   └── apps/
│       └── selendroid-test-app.apk
├── manual-testing/
└── README.md
```

## Framework Architecture

### Web UI Automation

Playwright automates browser interactions using the Page Object Model.

The framework separates page objects, test scenarios, browser lifecycle management, reusable utilities, and TestNG listeners. Failure diagnostics can include screenshots, execution summaries, and stack traces.

### API Automation

REST Assured supports API validation, including HTTP status codes, response fields, API chaining, test data generation, and JSON schema validation.

### Android Mobile Automation

Appium automates the supplied Selendroid Android test application. Driver management and common test setup and teardown are separated from individual test cases.

### BDD Automation

Cucumber feature files describe behavior in a readable format, while step definitions implement the corresponding test actions and assertions.

## Design Patterns and Framework Practices

### Page Object Model

Web page interactions are encapsulated in page classes to keep locators and UI actions separate from test scenarios and assertions.

### Manager Pattern

Centralized managers handle shared resources such as Playwright and Appium drivers. This keeps initialization and cleanup consistent across tests.

### Utility Classes

Reusable utilities support common framework tasks, including screenshots, test data generation, logging, and execution diagnostics.

### Constants and Configuration

Shared endpoint names, configuration values, and other reusable constants are centralized where appropriate to reduce duplication and improve maintainability.

## Automation Coverage

### Web UI Automation

The web suite covers eight jQuery UI interactions and smoke tests:

- Droppable
- Selectable
- Controlgroup
- Datepicker
- Resizable
- Sortable
- Widget Factory
- Home page smoke test

### API Automation

The REST Assured framework supports API smoke checks, GET and create-user scenarios, API chaining, response validation, and JSON schema validation where configured.

### BDD Automation

Cucumber feature files are stored under `mobile-api-automation/src/test/resources/features/`. The TestNG Cucumber runner and step definitions provide the execution layer. The configured runner generates separate HTML and JSON reports.

## Reporting and Diagnostics

The framework uses multiple reporting mechanisms to support test analysis and debugging.

| Report | Purpose |
|---|---|
| Allure dashboard | Test status, duration, execution details, and configured attachments |
| Screenshots | Visual evidence of UI failures when captured and attached |
| Stack traces | Exception details and failure locations |
| Execution summaries | Test name, status, duration, and other configured metadata |
| Application/framework logs | Diagnostic messages produced by Log4j2 |
| API request/response attachments | HTTP diagnostics when configured in API tests |
| Cucumber HTML/JSON | BDD scenario execution results |
| Maven Surefire reports | TestNG results, counts, and failure information |

### Allure Report Structure

Each Maven module has its own reporting output.

```text
web-automation/target/
├── allure-results/
├── allure-report/
├── surefire-reports/
├── screenshots/
└── logs/

mobile-api-automation/target/
├── allure-results/
├── allure-report/
├── surefire-reports/
├── cucumber-report.html
├── cucumber-report.json
└── logs/
```

Actual files and directories depend on the suite executed and the reporting configuration.

**Allure results** are the raw files generated during test execution. The **Allure dashboard** is generated from those results.

When configured, individual Allure test details can include:
- Pass/fail status and execution duration.
- Execution summary attachments.
- Failure messages and stack traces.
- Screenshot attachments for UI failures.
- Log and API diagnostic attachments.

Screenshots and logs are not automatically attached merely because they exist on disk. The appropriate listener or test code must attach them to the relevant Allure test result.

### Generate the Allure Dashboard

Run these commands from the relevant module directory after executing tests:

```bash
allure generate target/allure-results \
  --output target/allure-report \
  --clean

allure open target/allure-report
```

The Allure CLI must be installed and available on `PATH`.

### Additional Reports

- **Surefire:** `target/surefire-reports/`
- **Cucumber HTML:** `target/cucumber-report.html`
- **Cucumber JSON:** `target/cucumber-report.json`

Cucumber scenarios appear in Allure only when the appropriate Cucumber Allure adapter is installed and configured.

## Running the Tests

### Prerequisites

- JDK 17.
- Maven.
- Playwright browser installation.
- Android SDK and an emulator or physical device for mobile tests.
- Appium server and the required Android driver.
- Allure CLI for generating the dashboard.

### Web Automation

```bash
cd web-automation
mvn test
```

### API and BDD Automation

```bash
cd mobile-api-automation
mvn test
```

The configured `testng.xml` may also include mobile tests. Use the appropriate suite configuration if you need to execute only API/BDD tests.

### Mobile Automation

Start the Android emulator or connect a device, start Appium, and verify device connectivity before running the configured mobile suite.

```bash
cd mobile-api-automation
mvn test -Dsurefire.suiteXmlFiles=testng-mobile.xml
```

## CI/CD

GitHub Actions automates web and API test execution and publishes test reports as workflow artifacts.

### Workflow execution

The main workflow is defined in `.github/workflows/ci.yml`. It runs the web and API automation jobs independently when changes are pushed to, or pull requests target, the configured `main` and `master` branches. It can also be started manually when configured in GitHub Actions.

### Web automation pipeline

1. Check out the repository.
2. Set up Java 17 and the required build environment.
3. Install the Playwright browser dependencies.
4. Execute the web automation suite using Maven.
5. Generate the Allure HTML report from the available Allure result files.
6. Upload the web test results and generated reports as the `web-test-reports` workflow artifact.

### API automation pipeline

1. Check out the repository.
2. Set up Java 17 and the Maven environment.
3. Execute the API-only TestNG suite using `testng-api.xml`, keeping API execution separate from Android UI tests.
4. Generate the Allure HTML report when Allure result files are available.
5. Upload the API Allure results and HTML report as the `api-test-reports` workflow artifact.

The API suite also produces a Cucumber HTML report locally as part of test execution.

### Reports and artifacts

- **Allure HTML reports:** Provide a browsable summary of test execution and individual test results.
- **Allure result files:** Preserve machine-readable test results used to generate the HTML reports.
- **Surefire reports:** Provide Maven test execution summaries.
- **Cucumber HTML report:** Provides a readable report of API feature and scenario execution.

Report-generation steps run with an `always()` condition so reports can still be generated when earlier test steps fail, provided result files are available. The workflow uploads available report files as artifacts for inspection from the corresponding GitHub Actions run.

To review the reports, open the relevant workflow run in GitHub Actions and download the `web-test-reports` or `api-test-reports` artifact.

## Manual Testing

The `manual-testing/` directory contains the manual testing deliverables prepared for the evaluation.

## Maintainability

The framework emphasizes separation of concerns, reusable utilities, centralized configuration, meaningful assertions, explicit synchronization, consistent resource management, and actionable failure diagnostics.

## Code Quality and Test Execution Strategy

The framework emphasizes separation of concerns, reusable components, centralized configuration, meaningful test names and assertions, explicit synchronization, consistent driver lifecycle management, and actionable failure diagnostics.

The web, API/BDD, and Android mobile layers can be executed independently using their respective Maven modules and TestNG suite configurations. This supports targeted execution and helps keep browser, API, and emulator-specific setup separate.

## Assignment Deliverables

- Web UI automation.
- REST API automation.
- Android mobile automation.
- Cucumber BDD scenarios.
- TestNG-based test execution.
- Allure and supplementary reports.
- Logging and failure diagnostics.
- GitHub Actions CI/CD configuration.
- Manual testing deliverables.