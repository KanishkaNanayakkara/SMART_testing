package com.testing.uiTesting.tests.dashboard;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.testing.uiTesting.base.BaseUITest;
import com.testing.uiTesting.pages.dashboard.DashboardPage;
import com.testing.uiTesting.pages.sidebarNavigation.SideBarNavigationPanelPage;

public class DashboardNavigationTest extends BaseUITest {

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

    @Test
    public void verifyQuickLaunchNavigatesToAssignLeavePage() {
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.navigateToDashboard();
        dashboardPage.clickQuickLaunchAssignLeave();
        Assert.assertTrue(driver.getCurrentUrl().contains("assignLeave"),
                "Failed to navigate to Assign Leave Page!");
    }

    @Test
    public void verifyEmployeeStatisticsChartUpdatesForSubUnits() {
        DashboardPage dashboardPage = new DashboardPage(driver);

        String[] subUnits = { "Engineering", "Human Resources", "Administration", "Client Services", "Unassigned" };

        for (String subUnit : subUnits) {
            dashboardPage.clickSubUnit(subUnit);
            Assert.assertTrue(dashboardPage.isChartUpdated(subUnit),
                    "Chart did not update correctly when '" + subUnit + "' was selected.");
        }
    }

    @Test
    public void verifyNavigationToLeaveModule() {
        SideBarNavigationPanelPage sideBar = new SideBarNavigationPanelPage(driver);
        sideBar.clickLeaveMenu();
        Assert.assertTrue(driver.getCurrentUrl().contains("leave"),
                "Failed to navigate to the Leave Module");
    }

}