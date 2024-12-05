# QA Testing Project

## Project Description  
This project provides comprehensive API and UI testing functionalities .  
The API tests validate the functionality of given endpoints of the library management system including handle book registration, retrieval, updating, and deletion. These tests also aim to uncover hidden bugs in the system.  
The UI tests focus on a demo application, [SauceDemo](https://www.saucedemo.com/), specifically designed for testing and learning purposes.  

## endpoints to be tested under API testing(library management system )  
https://drive.google.com/file/d/1Rx9NPHZpqzRVazSVbaZIX-0DJM9UPWR9/view?usp=sharing

## Prerequisites  

Before running this project, ensure the following software dependencies are installed:  
- **Java**: Version 13 or higher  
- **Maven**: For building and managing dependencies  
- **Chrome Browser**: For UI testing  
- **IDE**: IntelliJ IDEA, Eclipse, or any preferred Java IDE  
- **WebDriver**: Managed using WebDriverManager for Selenium  

## Project Structure  

The directory structure is organized as follows:  

```
qa-testing/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com.software/         # Application-specific files
│   │   │   └── Main.java             # Entry point for test output validation
│   │
│   └── test/
│       ├── java/
│       │   ├── com.selenium.testing.apiTesting.tests/
│       │   │   ├── BaseApiTest.java  # Base class for API testing setup
│       │   │   ├── registerBookApiTest/  # API tests for book management
│       │   │   └── utils/            # Utility classes for API response validation
│       │   ├── com.selenium.testing.uiTesting/
│       │   │   ├── BaseUITest.java   # Base class for UI testing setup
│       │   │   └── pages/            # Page Object Model (POM) files for SauceDemo
│       │
│       └── resources/
│           └── testng.xml            # TestNG suite configuration
│
├── pom.xml                           # Maven configuration file
└── README.md                         # Project documentation
```

## Building and Running the Project  

### For API Tests  

#### Setting Up the API Server  
1. Ensure Java is installed (version 13 or higher).  
2. Navigate to the directory containing the `demo-0.0.1-SNAPSHOT.jar` file.  
3. Start the API server by running the following command:  

   ```sh
   java -jar demo-0.0.1-SNAPSHOT.jar
   ```  

4. Verify the server is running by accessing the base URL: `http://localhost:7081`.  

#### Running the API Tests  
1. Open a terminal and navigate to the project directory.  
2. Use Maven to run the tests:  

   ```sh
   mvn clean test
   ```  

3. API test results will be displayed in the terminal.  

---

### For UI Tests  

#### Setting Up WebDriver  
1. Install Chrome browser if not already installed.  
2. The WebDriver setup is managed automatically using WebDriverManager. No manual setup is required.  

#### Running the UI Tests  
1. Open a terminal and navigate to the project directory.  
2. Use Maven to run the tests:  

   ```sh
   mvn clean test
   ```  

3. UI test results will be displayed in the terminal.  

## Notes  
- **Data Persistence:** The API server uses an in-memory database. Data will persist only while the server is running. Upon restarting, all data is cleared.  
- **User Credentials:**  
  - Admin: `username: admin`, `password: password`  
  - User: `username: user`, `password: password`  
- Ensure the terminal running the API server remains open during testing.  

---

## Branch Naming Convension  
1. Feature - ft , Chore- ch or Bug- bg
/
2. Initials of dev
-
3. branch name
ex:ch/kn-add-read-me-file
   ft/ws-login-ui-test
   bg/kn-fix-login-test-failure 



This README provides a clear guide to set up, understand, and run the project effectively.