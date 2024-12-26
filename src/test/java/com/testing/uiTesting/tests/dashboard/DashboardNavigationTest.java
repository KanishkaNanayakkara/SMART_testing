package com.testing.uiTesting.tests.dashboard;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.testing.uiTesting.base.BaseUITest;
import com.testing.uiTesting.pages.SideBarNavigationPanelPage;

public class DashboardNavigationTest extends BaseUITest{

    @Test
    public void verifyNavigationToMyInfoModule() {
        SideBarNavigationPanelPage sideBar = new SideBarNavigationPanelPage(driver);
        sideBar.clickMyInfo();
        Assert.assertTrue(driver.getCurrentUrl().contains("viewPersonalDetails"),
        "Failed to navigate to the My Info Module");
    }

    @Test
    public void verifyNavigationToPIMModule() {
        SideBarNavigationPanelPage sideBar = new SideBarNavigationPanelPage(driver);
        sideBar.clickPIM();
        Assert.assertTrue(driver.getCurrentUrl().contains("pim"),
        "Failed to navigate to the PIM Module");
    }
}