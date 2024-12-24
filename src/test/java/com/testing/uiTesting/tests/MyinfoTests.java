package com.testing.uiTesting.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.testing.uiTesting.base.BaseUITest;
import com.testing.uiTesting.pages.LoginPage;
import com.testing.uiTesting.pages.MyInfoPage;
import com.testing.uiTesting.pages.SideBarNavigationPanelPage;

public class MyinfoTests extends BaseUITest {

    @Test
    public void testUpdateEmployeeName() {
        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setUserName("Admin");
        loginPage.setPassword("admin123");
        loginPage.clickLogin();
        Assert.assertEquals(driver.getTitle(), "OrangeHRM", "Login failed!");

        // navigate to MyInfo
        SideBarNavigationPanelPage sideBarNavigationPanelPage = new SideBarNavigationPanelPage(driver);
        sideBarNavigationPanelPage.clickMyInfo();

        // Update data
        MyInfoPage myinfopage = new MyInfoPage(driver);
        String employeeName = "Amarabandu";
        myinfopage.typeEmployeeName(employeeName);
        myinfopage.clickSave();

        // Validate success message
        String successMessage = MyInfoPage.getSuccessMessage();
        Assert.assertTrue(successMessage.contains("Successfully Updated"), "Update successful!");

        // Validate that the employee name is updated correctly
        String updatedEmployeeName = myinfopage.getUpdatedEmployeeName(); 
        Assert.assertEquals(updatedEmployeeName, employeeName, "The updated employee name match the input.");
    }

    @Test
    public void testFieldValidation() {
        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setUserName("Admin");
        loginPage.setPassword("admin123");
        loginPage.clickLogin();
        Assert.assertEquals(driver.getTitle(), "OrangeHRM", "Login failed!");

        // navigate to MyInfo
        SideBarNavigationPanelPage sideBarNavigationPanelPage = new SideBarNavigationPanelPage(driver);
        sideBarNavigationPanelPage.clickMyInfo();

        // Update data
        MyInfoPage myinfopage = new MyInfoPage(driver);
        String employeeName = "shehandddddddddddddddddddddddddddddddddddddddddddddddddddd";
        myinfopage.typeEmployeeName(employeeName);
 

        // Validate success message
        String errorMessage = MyInfoPage.getErrorMessage();
        Assert.assertTrue(errorMessage.contains("Should not exceed 30 characters"), "Validation passed!");

    }

}