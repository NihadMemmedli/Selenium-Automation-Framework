# Introduction
This documentation describes the test automation framework designed to automate testing for the Insider website. The framework is built using Selenium WebDriver and follows the Page Object Model (POM) pattern.

## Key Features
- Utilizes the Page Object Model for maintainability.
- Contains utility classes for common functionalities.
- Separates configuration details.
- Allows taking screenshots for test evidence.

## Project Structure
The framework is organized into different packages, including:

### Pages Package
#### BasePage
An abstract class that contains common functionalities across different pages.

#### Other Page Classes (e.g., HomePage, CareersPage, TeamPage)
These classes extend BasePage and represent individual pages within the application.

### Utils Package
Utility classes for configurations, JavaScript interactions, waiting strategies, etc.

### Tests Package
Includes test classes defining test cases.

### Base Package
#### BaseTest
This class provides the setup and teardown methods for tests, managing the WebDriver initialization and closure.

### Driver Package
#### WebDriverFactory
Manages the lifecycle of WebDriver instances, supporting parallel execution and different browsers (Chrome, Firefox).

### Enums Package
Contains enumerated types for consistent referencing of common values, such as `Department` and `Location`.

### Listeners Package
#### TestListener
Implements custom behavior on test failures, such as logging failures and taking screenshots.

### Manager Package
#### PageObjectManager
Acts as a factory for all the Page Objects, ensuring that only one instance of each is created during the test execution.

## Running Tests

### Prerequisites
- Java 1.8 or higher
- Maven installed

### Dependencies
The following dependencies are used in the project and defined in the `pom.xml`:
- Selenium WebDriver
- TestNG
- Log4j
- ExtentReports
- WebDriverManager

### Command-Line Instructions
1. Navigate to the root directory of the project where the `pom.xml` file is located.
2. Run the following command to execute the tests: `mvn clean test`


## Best Practices
- Consistency: Ensure that all page classes follow the same pattern and structure.
- Reusability: Encourage the use of reusable methods and components.
- Readability: Include meaningful comments and adhere to coding standards.
