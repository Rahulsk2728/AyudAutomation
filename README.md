# AyudAutomation 🚀

Enterprise-style UI and API automation framework built using **Java, Playwright, TestNG, REST Assured, Maven, Docker, Jenkins, Allure, Extent Reports, Log4j2, and GitHub Actions**.

The framework is designed using reusable components and follows **Page Object Model (POM)** and modular API automation practices.

---

## 📌 Project Overview

AyudAutomation is an automation framework covering both:

- 🌐 Web UI automation
- 🔌 REST API automation

The framework provides reusable utilities for browser management, API requests, reporting, logging, screenshots, Playwright traces, parallel execution, cross-browser execution, retry handling, and CI/CD integration.

---

# 🛠️ Technology Stack

| Technology | Purpose |
|------------|---------|
| Java 21 | Programming language |
| Maven | Build & dependency management |
| Playwright | Web UI automation |
| TestNG | Test execution & assertions |
| REST Assured | API automation |
| Jackson | JSON serialization/deserialization |
| Apache POI | Excel test data |
| Log4j2 | Logging |
| Extent Reports | HTML reporting |
| Allure | Test reporting |
| Git | Version control |
| GitHub | Source code repository |
| GitHub Actions | CI automation |
| Jenkins | CI/CD execution |
| Docker | Containerized test execution |

---

# 📂 Project Structure

```text
AyudAutomation
│
├── src
│   │
│   ├── main
│   │   └── java
│   │       │
│   │       ├── api
│   │       │   ├── APIClient.java
│   │       │   ├── BaseAPI.java
│   │       │   └── TokenManager.java
│   │       │
│   │       ├── base
│   │       │   ├── BasePage.java
│   │       │   └── BaseTest.java
│   │       │
│   │       ├── config
│   │       │   └── APIConfig.java
│   │       │
│   │       ├── endpoints
│   │       │   └── UserEndpoints.java
│   │       │
│   │       ├── factory
│   │       │   └── BrowserFactory.java
│   │       │
│   │       ├── models
│   │       │   ├── User.java
│   │       │   ├── CreateUserRequest.java
│   │       │   ├── UpdateUserRequest.java
│   │       │   ├── LoginRequest.java
│   │       │   └── RegisterRequest.java
│   │       │
│   │       ├── utils
│   │       │   ├── ConfigReader.java
│   │       │   ├── ExcelUtils.java
│   │       │   ├── WaitUtils.java
│   │       │   ├── ScreenshotUtils.java
│   │       │   ├── AllureUtils.java
│   │       │   └── LogUtils.java
│   │       │
│   │       └── ...
│   │
│   └── test
│       │
│       ├── java
│       │   │
│       │   ├── apiTests
│       │   │   ├── UserAPITest.java
│       │   │   └── AuthAPITest.java
│       │   │
│       │   ├── dataProviders
│       │   │   └── UserDataProvider.java
│       │   │
│       │   └── tests
│       │       ├── HomeTest.java
│       │       └── AyudJobsTest.java
│       │
│       └── resources
│           └── schemas
│               └── users-schema.json
│
├── reports
├── screenshots
├── traces
├── allure-results
│
├── TestData
│   └── LoginData.xlsx
│
├── .github
│   └── workflows
│       └── playwright.yml
│
├── Dockerfile
├── pom.xml
├── testng.xml
├── testng-single.xml
├── log4j2.xml
└── README.md
