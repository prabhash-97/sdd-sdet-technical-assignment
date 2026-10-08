# SDD SDET Technical Assignment

A maintainable automation framework developed for the **Sharjah Digital Department (SDD) Senior Software Development Engineer in Test technical evaluation**.

The solution covers **Web UI, REST API, BDD, reporting, logging, screenshots, and CI/CD**, with mobile automation implemented using Appium.

---

## Technology Stack

| Area                 | Technology              |
| -------------------- | ----------------------- |
| Web UI Automation    | Playwright              |
| Programming Language | Java 17                 |
| API Automation       | REST Assured            |
| Mobile Automation    | Appium                  |
| Test Framework       | TestNG                  |
| BDD                  | Cucumber                |
| Build Tool           | Maven                   |
| Reporting            | Allure                  |
| Logging              | Log4j2                  |
| CI/CD                | GitHub Actions          |
| Version Control      | Git / GitHub            |
| Design Pattern       | Page Object Model (POM) |

---

## Project Structure

```text
sdd-sdet-technical-assignment/
│
├── .github/
│   └── workflows/
│       └── ci.yml
│
├── web-automation/
│   ├── pom.xml
│   ├── testng.xml
│   └── src/
│       ├── main/java/com/sdd/automation/
│       │   ├── config/
│       │   ├── constants/
│       │   ├── driver/
│       │   ├── pages/web/
│       │   └── utils/
│       │
│       └── test/java/com/sdd/automation/
│           ├── listeners/
│           ├── runners/
│           ├── stepdefinitions/
│           └── tests/web/
│
├── mobile-api-automation/
│   ├── pom.xml
│   ├── testng.xml
│   ├── src/
│   │   ├── main/java/com/sdd/automation/
│   │   │   ├── api/
│   │   │   ├── config/
│   │   │   ├── constants/
│   │   │   ├── driver/
│   │   │   └── utils/
│   │   │
│   │   └── test/
│   │       ├── java/com/sdd/automation/
│   │       │   ├── runners/
│   │       │   ├── stepdefinitions/
│   │       │   └── tests/
│   │       └── resources/
│   │           ├── config/
│   │           ├── features/
│   │           └── schemas/
│   │
│   └── apps/
│       └── selendroid-test-app.apk
│
├── manual-testing/
│
├── README.md
└── .gitignore
```

---

## Automation Architecture

The framework follows a modular and maintainable structure.

### Web Automation

Playwright is used for browser automation with the **Page Object Model (POM)**.

Responsibilities are separated into:

* Page classes — UI locators and page actions
* Test classes — test scenarios and assertions
* Driver manager — Playwright lifecycle management
* Utilities — reusable framework functionality
* Listeners — reporting and failure handling
* TestNG — test execution and suite management

The Playwright browser is managed centrally through `PlaywrightManager`, allowing browser creation and cleanup to remain separate from individual test cases.

### API Automation

REST Assured is used for API testing.

The API framework includes:

* Centralized request specification
* API endpoint constants
* Reusable test data generation
* Status code validation
* Response field validation
* API chaining
* JSON schema validation
* Cucumber BDD scenarios

### Mobile Automation

Appium is used for Android mobile automation with the supplied Selendroid test application.

The mobile automation layer uses a dedicated Appium driver manager and base test structure.

---

## Design Patterns and Framework Practices

The solution uses the following practices:

### Page Object Model

Web page interactions are encapsulated within page classes rather than placing locators and UI operations directly inside tests.

### Manager Pattern

Centralized managers are used for resources such as:

* Playwright
* Appium

This keeps driver lifecycle management consistent and reusable.

### Utility Classes

Common functionality is isolated into reusable utilities, including:

* Screenshot handling
* Test data generation
* Logging

### Constants

API endpoints and reusable configuration values are centralized to reduce duplication and improve maintainability.

---

## Web Automation Coverage

The Playwright automation covers the following scenarios:

1. jQuery UI Droppable
2. jQuery UI Selectable
3. jQuery UI Controlgroup
4. jQuery UI Datepicker
5. jQuery UI Resizable
6. jQuery UI Sortable
7. jQuery UI Widget Factory
8. Home page smoke test

Current local execution result:

```text
Tests run: 8
Failures: 0
Errors: 0
Skipped: 0
BUILD SUCCESS
```

---

## API Automation Coverage

REST Assured automation includes:

* API smoke validation
* GET users
* Create user
* API chaining
* JSON schema validation

The API test suite also contains Cucumber BDD scenarios.

Current local execution result:

```text
Tests run: 6
Failures: 0
Errors: 0
Skipped: 0
BUILD SUCCESS
```

The six tests consist of:

* 4 REST Assured TestNG tests
* 2 Cucumber API scenarios

---

## BDD with Cucumber

Cucumber is integrated with TestNG for API BDD automation.

Feature files are stored under:

```text
mobile-api-automation/src/test/resources/features/
```

Step definitions are maintained separately from feature files.

Cucumber generates:

* HTML report
* JSON report

---

## Reporting

### Allure Reporting

Allure is integrated into the automation framework.

After test execution:

```bash
allure generate target/allure-results -o target/allure-report --clean
```

To open the generated report:

```bash
allure open target/allure-report
```

Allure provides a richer execution view than the default Maven/TestNG reports.

### Cucumber Reporting

Cucumber generates:

```text
target/cucumber-report.html
target/cucumber-report.json
```

### Maven/TestNG Reports

Surefire reports are generated under:

```text
target/surefire-reports/
```

---

## Logging

Log4j2 is configured for automation logging.

Logs are written to:

```text
target/logs/automation.log
```

Console logging is also enabled during execution.

---

## Failure Screenshots

A TestNG listener captures screenshots when a web test fails.

Screenshots are stored under:

```text
target/screenshots/
```

Failure screenshots are also attached to the Allure report.

This provides visual evidence for failed UI test execution.

---

## Running the Tests

### Web Automation

```bash
cd web-automation
mvn clean test
```

Playwright browsers can be installed with:

```bash
mvn exec:java -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install chromium"
```

### API Automation

```bash
cd mobile-api-automation
mvn clean test
```

The API Maven suite executes:

* REST Assured TestNG tests
* Cucumber API tests

Mobile tests are maintained separately so that Android emulator/device execution can be performed independently.

### Mobile Automation

After configuring an Android emulator or physical device:

```bash
cd mobile-api-automation
mvn test
```

---

## CI/CD

GitHub Actions is configured under:

```text
.github/workflows/ci.yml
```

The pipeline currently performs:

### Web Job

1. Checkout source code
2. Configure Java 17
3. Install Playwright Chromium
4. Execute Maven tests
5. Upload test reports and logs as artifacts

### API Job

1. Checkout source code
2. Configure Java 17
3. Execute Maven API/Cucumber tests
4. Upload test reports and logs as artifacts

The workflow is triggered on:

* Push to `main`
* Push to `master`
* Pull requests
* Manual workflow dispatch

---

## Code Quality and Maintainability

The framework focuses on:

* Separation of concerns
* Reusable components
* Centralized configuration
* Page Object Model
* Meaningful test and method names
* Centralized constants
* Assertions close to validation points
* Explicit synchronization through Playwright APIs
* Exception handling around framework utilities
* Centralized driver lifecycle management
* Externalized configuration
* Logging and failure diagnostics

---

## Test Execution Strategy

The automation projects are separated according to their responsibilities:

```text
Web Automation
       │
       └── Playwright + TestNG

API Automation
       │
       ├── REST Assured + TestNG
       │
       └── Cucumber + TestNG

Mobile Automation
       │
       └── Appium + TestNG
```

This structure allows each automation layer to be executed independently while keeping common framework responsibilities organized.

---

## Repository

Source code is maintained in Git and pushed to GitHub.

Repository:

**SDD SDET Technical Assignment**

---

## Assignment Deliverables

The repository contains the following areas required for the evaluation:

* Web UI automation
* API automation
* Mobile automation
* BDD automation
* TestNG execution
* Reporting
* Logging
* Failure screenshots
* CI/CD workflow
* Manual testing deliverables

---

## Notes

Mobile automation is designed for execution against an Android emulator or physical device using Appium. Device-specific configuration may need to be adjusted depending on the Android environment used for execution.

The framework is structured to allow additional test cases, pages, APIs, and mobile screens to be added without modifying the existing framework architecture.
