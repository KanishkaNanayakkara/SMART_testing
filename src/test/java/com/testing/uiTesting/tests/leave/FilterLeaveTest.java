package com.testing.uiTesting.tests.leave;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.testing.uiTesting.base.BaseUITest;
import com.testing.uiTesting.pages.leave.FilterLeavePage;

public class FilterLeaveTest extends BaseUITest {

    @Test()
    public void filterLeaveRequestsByStatusShouldPass() {
        FilterLeavePage filterLeavePage = new FilterLeavePage(driver);
        filterLeavePage.navigateToLeaveListPage();
        filterLeavePage.applyFilter();
        String statusToCheck = "Cancelled";

        boolean result = filterLeavePage.areAllRowsMatchingStatus(statusToCheck);

        Assert.assertTrue(result, "Not all statuses are 'Cancelled' in the filtered list.");
    }
}
