# SWSignTest


This project is a **test automation framework** for **API** and **GUI** testing using **Java 17**, **Selenium**, and **Rest Assured**.

---

## Technologies

* Java 17
* Selenium WebDriver (GUI tests)
* Rest Assured (API tests)
* JUnit 5
* Allure (reporting)
* WebDriverManager
* Maven

---

## Project Structure

```
src/
 └─ main/java/config/        # Configurations
 └─ test/java/core/          # Base test classes, utilities
 └─ test/java/tests/         # GUI & API tests
 └─ test/java/pages/         # Page Objects
pom.xml                      # Maven dependencies
```

---

## Setup

### Prerequisites

* Java 17, Maven, Chrome browser

### Install Dependencies

```bash
cd <repository_folder>
mvn clean install
```

---

## Running Tests

**GUI Tests (Selenium):**

```bash
mvn test -Dtest=*GuiTest
```

**API Tests (Rest Assured):**

```bash
mvn test -Dtest=*ApiTest
```

**Allure Reporting:**

```bash
mvn allure:report
mvn allure:serve
```

* Failing GUI tests automatically include screenshots in the report.
