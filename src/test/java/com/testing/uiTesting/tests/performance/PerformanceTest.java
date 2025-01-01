package com.testing.uiTesting.tests.performance;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.testing.uiTesting.base.BaseUITest;
import com.testing.uiTesting.pages.performance.PerformancePage;
import com.testing.uiTesting.pages.sidebarNavigation.SideBarNavigationPanelPage;

public class PerformanceTest extends BaseUITest {

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
        performancePage.clickOptions();
        performancePage.clickEdit();

        String Message = performancePage.getSuccessMessage();
        String CreatedLogName = performancePage.getCreatedLogName();
        String CreatedLogComment = performancePage.getCreatedLogComment();

        Assert.assertTrue(Message.toLowerCase().contains("successfully saved"), 
                  "The success message does not indicate the candidate was successfully saved. Message: ");
        
        Assert.assertEquals(CreatedLogName, employeeLogName, "The created log name match the input.");
        Assert.assertEquals(CreatedLogComment, employeeLogComment, "The created log comment match the input.");
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

        performancePage.clickOptions();
        performancePage.clickEdit();

        String updatedLogName = performancePage.getCreatedLogName();
        String updatedLogComment = performancePage.getCreatedLogComment();

        Assert.assertTrue(Message.toLowerCase().contains("successfully updated"), 
                  "The success message does not indicate the candidate was successfully saved. Message: ");
        Assert.assertEquals(updatedLogName, newEmployeeLogName, "The updated log name match the input.");
        Assert.assertEquals(updatedLogComment, newEmployeeLogComment, "The updated log comment match the input.");
    }
}
