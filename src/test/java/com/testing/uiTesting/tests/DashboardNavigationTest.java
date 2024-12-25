package com.testing.uiTesting.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.testing.uiTesting.base.BaseUITest;
import com.testing.uiTesting.pages.LoginPage;
import com.testing.uiTesting.pages.SideBarNavigationPanelPage;

public class DashboardNavigationTest extends BaseUITest{

    @Test
    public void verifyNavigationToMyInfoModule() {

        // //login
        // LoginPage loginPage = new LoginPage(driver);
        // loginPage.setUserName("Admin");
        // loginPage.setPassword("admin123");
        // loginPage.clickLogin();
        // Assert.assertEquals(driver.getTitle(), "OrangeHRM", "Login Failed!" );

        //navigate to leave module
        SideBarNavigationPanelPage sideBar = new SideBarNavigationPanelPage(driver);
        sideBar.clickMyInfo();
        Assert.assertTrue(driver.getCurrentUrl().contains("viewPersonalDetails"),
        "Failed to navigate to the My Info Module");

    }

}