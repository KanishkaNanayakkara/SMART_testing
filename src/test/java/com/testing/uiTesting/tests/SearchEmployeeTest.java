package com.testing.uiTesting.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.testing.uiTesting.base.BaseUITest;
import com.testing.uiTesting.pages.LoginPage;
import com.testing.uiTesting.pages.PIMPage;
import com.testing.uiTesting.pages.SideBarNavigationPanelPage;

public class SearchEmployeeTest extends BaseUITest{

    @Test
    public void testSearchEmployeeByName() {
        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setUserName("Admin");
        loginPage.setPassword("admin123");
        loginPage.clickLogin();
        Assert.assertEquals(driver.getTitle(), "OrangeHRM", "Login failed!");

        //navigate to PIM
        SideBarNavigationPanelPage sideBarNavigationPanelPage = new SideBarNavigationPanelPage(driver);
        sideBarNavigationPanelPage.clickPIM();
        // Assert.assertEquals(driver.getTitle(), "PIM");

        // Step 3: Search for employee
        PIMPage pimPage = new PIMPage(driver);
        String employeeName = "Amelia"; // Replace with the actual name in your test data
        pimPage.typeEmployeeName(employeeName);
        pimPage.clickSearch();

        // Step 4: Verify the result
        String displayedName = pimPage.getDisplayedEmployeeName();
        Assert.assertEquals(displayedName, employeeName, "Employee name does not match!");
    }
    
}
