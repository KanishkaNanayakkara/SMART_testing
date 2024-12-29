package com.testing.uiTesting.tests.performance;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.testing.uiTesting.base.BaseUITest;
import com.testing.uiTesting.pages.performance.PerformancePage;
import com.testing.uiTesting.pages.sidebarNavigation.SideBarNavigationPanelPage;

public class PerformanceTest extends BaseUITest {
 
    @Test
    public void verifyNavigationToPerformanceModule() {

        SideBarNavigationPanelPage sideBarNavigationPanelPage = new SideBarNavigationPanelPage(driver);

        sideBarNavigationPanelPage.clickPerformance();

        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/performance/searchEvaluatePerformanceReview";
        String currentUrl = driver.getCurrentUrl();

        Assert.assertEquals(currentUrl, expectedUrl, "Failed to navigate to the Performance page.");

    }

    @Test
    public void verifyAddNewTrackerReview () {
        SideBarNavigationPanelPage sideBarNavigationPanelPage = new SideBarNavigationPanelPage(driver);
        PerformancePage performancePage = new PerformancePage(driver);

        sideBarNavigationPanelPage.clickPerformance();

        String employeeLogName = "Sales Performance Tracker";
        String employeeLogComment = "Successfully finished sales for 10 shops.";


        performancePage.clickMyTrackers();
        performancePage.clickViewBtn();
        performancePage.clickAddLog();
        performancePage.typeLogName(employeeLogName);
        performancePage.typeComment(employeeLogComment);
        performancePage.clickSaveLog();

        String Message = performancePage.getSuccessMessage();

        Assert.assertTrue(Message.toLowerCase().contains("successfully saved"), 
                  "The success message does not indicate the candidate was successfully saved. Message: ");
    }

    @Test
    public void verifyUpdateTrackerReview () {
        SideBarNavigationPanelPage sideBarNavigationPanelPage = new SideBarNavigationPanelPage(driver);
        PerformancePage performancePage = new PerformancePage(driver);

        sideBarNavigationPanelPage.clickPerformance();

        String newEmployeeLogName = "Newly updated Sales Performance Tracker";
        String newEmployeeLogComment = "Successfully finished sales for 20 shops.";

        performancePage.clickMyTrackers();
        performancePage.clickViewBtn();
        performancePage.clickOptions();
        performancePage.clickEdit();
        performancePage.clearLogName();
        performancePage.clearLogComment();
        performancePage.typeLogName(newEmployeeLogName);
        performancePage.typeComment(newEmployeeLogComment);
        performancePage.clickNegativeFeedback();
        performancePage.clickSaveLog();

        String Message = performancePage.getSuccessMessage();

        Assert.assertTrue(Message.toLowerCase().contains("successfully updated"), 
                  "The success message does not indicate the candidate was successfully saved. Message: ");
    }

}
