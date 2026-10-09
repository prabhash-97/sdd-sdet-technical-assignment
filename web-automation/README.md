# Web UI Automation

Java-based web UI automation using Playwright, TestNG, Maven, and Allure.

## Technology Stack

- Java 17
- Playwright for Java
- TestNG
- Maven
- Allure
- Log4j2

## Framework Design

The framework follows the Page Object Model and separates browser lifecycle management, page interactions, test logic, reusable utilities, and reporting listeners.

## Test Coverage

The current web test suite covers the following jQuery UI interactions and smoke tests:

1. Droppable
2. Selectable
3. Controlgroup
4. Datepicker
5. Resizable
6. Sortable
7. Widget Factory
8. Home page smoke test

## Prerequisites

- JDK 17
- Maven
- Playwright-compatible browser binaries
- Allure CLI, if generating reports locally

## Install Browser Binaries

```bash
mvn exec:java \
  -Dexec.mainClass=com.microsoft.playwright.CLI \
  -Dexec.args="install chromium"
```

## Execute Tests

```bash
mvn test
```

## Reporting

### Allure Dashboard

After test execution, generate and open the Allure report:

```bash
allure generate target/allure-results \
  --output target/allure-report \
  --clean

allure open target/allure-report
```

### Failure Diagnostics

Depending on the listener implementation, failed tests can include:

- Test execution summary.
- Exception details and stack trace.
- Screenshot of the browser state at failure.
- Additional diagnostic information attached by the framework.

Screenshots may also be saved to the configured screenshot directory. An on-disk screenshot appears in the Allure dashboard only if it is attached to the relevant test result.

### Other Report Locations

| Output | Location |
|---|---|
| Raw Allure results | `target/allure-results/` |
| Generated Allure HTML | `target/allure-report/` |
| Surefire reports | `target/surefire-reports/` |
| Screenshots | `target/screenshots/`, if configured |
| Logs | `target/logs/`, if configured |

## Troubleshooting

- If browser launch fails, verify that Chromium is installed.
- If tests time out, check the target application's availability and locator synchronization.
- If screenshots are missing from Allure, verify the listener's screenshot capture and attachment logic.
- If the report is empty, confirm that the test execution produced files under `target/allure-results/`