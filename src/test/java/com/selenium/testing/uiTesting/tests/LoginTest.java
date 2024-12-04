package com.selenium.testing.uiTesting.tests;

import org.testng.annotations.Test;
import org.testng.Assert;

import com.selenium.testing.uiTesting.BaseUITest;
import com.selenium.testing.uiTesting.pages.LoginPage;

public class LoginTest extends BaseUITest {

    @Test
    public void testSuccessfulLogin() {
        driver.get("https://github.com/login");
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("validuser", "validpassword");
        
        // Add assertion to verify successful login
        // This could be checking for a dashboard page, welcome message, etc.
        Assert.assertTrue(driver.getCurrentUrl().contains("/dashboard"), 
            "Login was not successful");
    }

    @Test
    public void testInvalidLogin() {
        driver.get("https://example.com/login");
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("invaliduser", "wrongpassword");
        
        // Verify error message is displayed
        Assert.assertTrue(loginPage.isErrorDisplayed(), 
            "Error message not displayed for invalid login");
        Assert.assertEquals(loginPage.getErrorMessage(), 
            "Invalid username or password", 
            "Incorrect error message displayed");
    }
}
