package com.testing.uiTesting.tests.applyLeave;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.testing.uiTesting.base.BaseUITest;
import com.testing.uiTesting.pages.applyLeave.ApplyLeavePage;

public class ApplyLeaveTest extends BaseUITest {

    @Test(priority = 1)
    public void applyLeaveWithAllRequiredFieldsShouldPass() {
        ApplyLeavePage applyLeavePage = new ApplyLeavePage(driver);
        String fromDate = "2024-26-12";
        String toDate = "2024-27-12";
        String comment= "Apply Leave for Test";
        applyLeavePage.navigateToApplyLeavePage();
        applyLeavePage.fillApplyLeaveForm(fromDate, toDate, comment);

        String successMessage = applyLeavePage.getConfirmationMessage();
        Assert.assertTrue(successMessage.contains("Successfully Saved"), "Failed to apply leave");
    }

    @Test(priority = 2)
    public void testLeaveRequestAppearsInListShouldPass() {
        ApplyLeavePage applyLeavePage = new ApplyLeavePage(driver);
        
        String expectedDate = "2024-26-12 to 2024-27-12";
        String expectedLeaveType = "CAN - FMLA";
        String expectedStatus = "Pending Approval";
        applyLeavePage.navigateToMyLeaveRequestPage();
        
        boolean isLeaveRequestPresent = applyLeavePage.isLeaveRequestInFirstRow(
            expectedDate,
            expectedLeaveType,
            expectedStatus
        );

        Assert.assertTrue(isLeaveRequestPresent, "The newly created leave request is not displayed in the first row.");
    }

}
