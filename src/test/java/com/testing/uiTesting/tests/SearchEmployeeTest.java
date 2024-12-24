package com.testing.uiTesting.tests;

import java.util.List;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.testing.uiTesting.base.BaseUITest;
import com.testing.uiTesting.pages.LoginPage;
import com.testing.uiTesting.pages.PIMPage;
import com.testing.uiTesting.pages.SideBarNavigationPanelPage;

public class SearchEmployeeTest extends BaseUITest{

    @Test
    public void testSearchEmployeeByName() throws InterruptedException {
        //Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setUserName("Admin");
        loginPage.setPassword("admin123");
        loginPage.clickLogin();
        Assert.assertEquals(driver.getTitle(), "OrangeHRM", "Login failed!");

        //navigate to PIM
        SideBarNavigationPanelPage sideBarNavigationPanelPage = new SideBarNavigationPanelPage(driver);
        sideBarNavigationPanelPage.clickPIM();
        Assert.assertTrue(driver.findElement(By.xpath("//input[@placeholder='Type for hints...']")).isDisplayed(), 
                  "Failed to navigate to the PIM page!");

        //Search for employee
        PIMPage pimPage = new PIMPage(driver);
        String searchedEmployeeName = pimPage.typeEmployeeName();
        pimPage.clickSearch();

        //Verify the result
        List<String> displayedNames = pimPage.getDisplayedEmployeeNamesafterSearchKeyApplied();
        Assert.assertTrue(displayedNames.contains(searchedEmployeeName), 
                "The employee name is not displayed in the search results! Expected: " + searchedEmployeeName + ", Found: " + displayedNames);
    }

}