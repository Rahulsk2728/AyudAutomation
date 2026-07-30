# Playwright Java Automation Framework

A scalable and enterprise-ready UI Automation Framework built using **Java, Playwright, TestNG, Maven, and Page Object Model (POM)**. The framework is designed with reusability, maintainability, parallel execution, reporting, and CI/CD integration in mind.

---

## Tech Stack

- Java 21
- Playwright
- TestNG
- Maven
- Log4j2
- Extent Reports
- Allure Reports
- Git & GitHub
- GitHub Actions
- Apache POI (Excel)
- Page Object Model (POM)

---

## Framework Architecture

```
src
├── main
│   ├── java
│   │   ├── base
│   │   ├── factory
│   │   ├── listeners
│   │   ├── pages
│   │   ├── reports
│   │   └── utils
│   │
│   └── resources
│       ├── config.properties
│       └── log4j2.xml
│
└── test
    └── java
        └── tests
```

---

## Framework Features

### Browser Management

- BrowserFactory implementation
- Configurable browser selection
- Chromium support
- Firefox support
- WebKit support
- Headless execution support

---

### Test Framework

- TestNG
- Page Object Model (POM)
- BaseTest implementation
- Thread-safe execution using ThreadLocal
- Cross-browser execution
- Parallel execution support

---

### Reporting

### Extent Reports

- HTML Report generation
- Screenshot on failure
- Pass/Fail/Skip status
- Automatic report opening

### Allure Reports

- Step annotations
- Environment information
- Categories
- Screenshot attachments
- Rich interactive reports

---

### Logging

- Log4j2 integration
- Console logging
- File logging

---

### Utilities

- ConfigReader
- Screenshot Utility
- WaitUtils
- BasePage reusable methods
- Excel Utility (Apache POI)

---

### Test Data

- Data-driven testing support
- Excel integration
- TestNG DataProvider

---

### Parallel Execution

Framework supports parallel execution using:

- ThreadLocal Browser
- ThreadLocal BrowserContext
- ThreadLocal Page
- Thread-safe Extent Reports

---

### Cross Browser Execution

Supported browsers:

- Chromium
- Firefox
- WebKit

Configured through TestNG XML.

---

### CI/CD

Integrated with GitHub Actions.

Automatically executes tests on:

- Push
- Pull Request

---

### Logging & Debugging

- Log4j2 logging
- Screenshots on failure
- Playwright tracing (In Progress)
- Allure reporting

---

## Project Structure

```
base/
    BaseTest
    BasePage

factory/
    BrowserFactory

listeners/
    TestListener

pages/
    HomePage

reports/
    ExtentManager

utils/
    ConfigReader
    ScreenshotUtils
    WaitUtils
    ExcelUtils
    AllureUtils

tests/
    HomeTest
```

---

## Reporting

### Extent Report

```
reports/
    ExtentReport.html
```

### Allure Report

Generate report

```
allure serve allure-results
```

---

## Running Tests

### Execute all tests

```
mvn test
```

### Execute specific TestNG XML

```
mvn test -DsuiteXmlFile=testng.xml
```

---

## Current Features

- Playwright Java Framework
- TestNG
- Maven
- POM Design Pattern
- BaseTest
- BasePage
- BrowserFactory
- ConfigReader
- WaitUtils
- Screenshot Utility
- Broken Link Validation
- Log4j2 Logging
- Extent Reports
- Allure Reports
- Parallel Execution
- Cross Browser Execution
- GitHub Actions CI/CD
- Excel Utility
- Data Provider Support

---

## Features Under Development

- Retry Analyzer
- REST Assured API Automation
- Docker Support
- Jenkins Pipeline
- Database Validation
- Playwright Trace Attachment
- Video Recording
- Trace Viewer Integration
- Advanced Playwright Scenarios
- Allure History & Trends

---

## Design Patterns Used

- Page Object Model (POM)
- Factory Pattern
- Singleton Pattern (ExtentManager)
- ThreadLocal Pattern
- Utility Pattern

---

## Future Enhancements

- REST Assured Integration
- Docker Execution
- Jenkins CI/CD
- Database Validation
- API + UI Integration
- Visual Testing
- Accessibility Testing
- SonarQube Integration
- JaCoCo Code Coverage

---

## Author

**Rahul Kshirsagar**

Automation Test Engineer

### Skills

- Java
- Playwright
- TestNG
- Selenium
- API Testing
- Maven
- GitHub Actions
- SQL
- CI/CD

---
