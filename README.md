# Ayud Software Automation Framework

## Overview

This project is a UI automation framework developed using **Java**, **Playwright**, **TestNG**, and **Maven**. It automates functional test scenarios for the Ayud Software website and follows the **Page Object Model (POM)** design pattern for better maintainability and scalability.

---

## Tech Stack

- Java 17+
- Playwright for Java
- TestNG
- Maven
- SLF4J Logging
- IntelliJ IDEA

---

## Project Structure

```
AyudAutomation
│
├── src
│   ├── main
│   │   └── java
│   │       ├── base
│   │       ├── pages
│   │       └── utils
│   │
│   └── test
│       └── java
│           └── tests
│
├── test-output
├── pom.xml
├── testng.xml
└── README.md
```

---

## Features

- Cross-browser support
- Page Object Model (POM)
- Reusable utility classes
- Explicit waits using Playwright
- Functional UI automation
- Broken link verification
- Easy test execution using TestNG
- Maven dependency management

---

## Prerequisites

Install the following before running the project:

- Java JDK 17 or above
- Maven 3.9+
- Node.js (required by Playwright)
- IntelliJ IDEA or Eclipse
- Git

---

## Installation

### Clone the repository

```bash
git clone https://github.com/Rahulsk2728/AyudAutomation.git
```

Navigate to the project:

```bash
cd AyudAutomation
```

Install Maven dependencies:

```bash
mvn clean install
```

Install Playwright browsers:

```bash
playwright install
```

or

```bash
mvn exec:java -e -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install"
```

---

## Running Tests

Run all tests:

```bash
mvn test
```

Run a TestNG suite:

```bash
mvn test -DsuiteXmlFile=testng.xml
```

Run a specific test class:

```bash
mvn -Dtest=HomeTest test
```

---

## Framework Components

### Base Layer

- Browser initialization
- Browser cleanup
- Playwright configuration

### Page Objects

Contains all page locators and reusable page methods.

Example:

- HomePage
- LoginPage

### Test Classes

Contains TestNG test cases.

Example:

- HomeTest

### Utilities

Reusable helper methods such as:

- Link verification
- Wait utilities
- File handling
- Screenshot capture

---

## Implemented Test Scenarios

- Verify Home Page loads successfully
- Verify page title
- Verify navigation menu
- Verify logo visibility
- Verify broken/dead links
- Verify page elements
- Verify navigation functionality

---

## Reporting

TestNG generates reports after execution under:

```
test-output/
```

---

## Design Pattern

The framework follows the **Page Object Model (POM)** to separate:

- Page Locators
- Page Actions
- Test Logic

This improves readability and maintainability.

---

## Best Practices

- Reusable page methods
- No hardcoded waits
- Clean code structure
- Separate test data from test logic
- Reusable utility classes
- Assertions using TestNG

---

## Future Enhancements

- Allure Reporting
- Extent Reports
- CI/CD with GitHub Actions
- Parallel Execution
- Data-driven testing
- API automation integration
- Docker support

---

## Author

**Rahul Kshirsagar**

Automation Test Engineer

GitHub: https://github.com/Rahulsk2728

---

## License

This project is created for learning and automation practice purposes.
