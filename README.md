# AyudAutomation

Enterprise-style UI and API automation framework built using Java, Playwright, TestNG, REST Assured, Docker, Jenkins, and GitHub Actions.

The framework is designed to demonstrate real-world SDET automation practices including Page Object Model, API automation, data-driven testing, parallel execution, retry handling, logging, reporting, screenshots, Playwright traces, JSON schema validation, Docker execution, and CI/CD integration.

---

## 📌 Project Overview

**AyudAutomation** is an end-to-end test automation framework developed for demonstrating modern SDET automation practices.

The framework covers:

- Web UI automation using Playwright
- REST API automation using REST Assured
- Data-driven testing using Excel
- Parallel test execution
- Cross-browser testing
- API request/response logging
- JSON schema validation
- API chaining
- Negative testing
- Retry mechanism
- Screenshot capture on UI test failure
- Playwright trace collection
- Allure reporting
- Extent reporting
- Log4j2 logging
- Docker-based test execution
- Jenkins CI execution
- GitHub Actions CI execution
- Test artifact persistence

---

# 🛠️ Technology Stack

| Technology | Version / Purpose |
|------------|-------------------|
| Java | 21 |
| Maven | Build & dependency management |
| Playwright | 1.54.0 |
| TestNG | 7.12.0 |
| REST Assured | 5.5.6 |
| Jackson | 2.19.2 |
| JSON Schema Validator | 5.5.6 |
| Apache POI | 5.4.1 |
| Extent Reports | 5.1.2 |
| Allure | 2.29.1 |
| Log4j2 | 2.25.1 |
| Docker | Containerized execution |
| Jenkins | CI/CD |
| GitHub Actions | CI automation |
| Git | Version control |

---

# 🏗️ Framework Architecture

```text
                         AyudAutomation
                               |
              +----------------+----------------+
              |                                 |
         UI Automation                     API Automation
              |                                 |
          Playwright                      REST Assured
              |                                 |
        Page Objects                    API Client
              |                                 |
          BasePage                       Endpoints
              |                                 |
        TestNG Tests                    Models/POJOs
              |                                 |
              +----------------+----------------+
                               |
                        Test Data Layer
                               |
                         Excel / DataProvider
                               |
              +----------------+----------------+
              |                |               |
           Logging          Reporting       Validation
              |                |               |
           Log4j2       Allure / Extent    Assertions
              |
       Screenshots / Traces
              |
        +-----+------+
        |            |
     Jenkins    GitHub Actions
        |
      Docker
