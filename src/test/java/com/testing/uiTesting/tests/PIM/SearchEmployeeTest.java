package com.testing.uiTesting.tests.PIM;

import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.testing.uiTesting.base.BaseUITest;
import com.testing.uiTesting.pages.PIM.PIMPage;

public class SearchEmployeeTest extends BaseUITest{

    @Test
    public void testSearchEmployeeByName() {
        PIMPage pimPage = new PIMPage(driver);
        pimPage.navigateToPIM();
        String searchedEmployeeName = pimPage.typeEmployeeName();
        pimPage.clickSearch();

        //Verify the result
        List<String> displayedNames = pimPage.getDisplayedEmployeeNamesAfterSearchKeyApplied();
        Assert.assertTrue(displayedNames.contains(searchedEmployeeName), 
                "The employee name is not displayed in the search results! Expected: " + searchedEmployeeName + ", Found: " + displayedNames);
    }

    @Test
    public void testSearchEmployeeById() {
        PIMPage pimPage = new PIMPage(driver);
        pimPage.navigateToPIM();
        String searchedEmployeeId = pimPage.typeEmployeeId();
        pimPage.clickSearch();

        // Verify the result
        String displayedEmployeeId = pimPage.getDisplayedEmployeeIdAfterSearchKeyApplied();
        Assert.assertEquals(displayedEmployeeId, searchedEmployeeId,
                "The employee ID in the search results does not match the searched ID! Expected: " + searchedEmployeeId
                        + ", Found: " + displayedEmployeeId);
    }

    // @Test
    // public void testSearchEmployeeByEmploymentStatus() {
       
    //     PIMPage pimPage = new PIMPage(driver);
    //     pimPage.navigateToPIM();
    //     String selectedEmploymentStatus = pimPage.selectEmploymentStatusFromTheDropdownMenuToSearch();
    //     pimPage.clickSearch();

    //     // Verify the result
    //     List<String> displayedStatuses = pimPage.getDisplayedEmploymentStatusAfterSearchKeyApplied();
    //     // for (String status : displayedStatuses) {
    //     //     Assert.assertEquals(status, selectedEmploymentStatus,
    //     //             "Employment status mismatch! Expected: " + selectedEmploymentStatus + ", Found: " + status);
    //     // }

    //     Assert.assertTrue(displayedStatuses.contains(selectedEmploymentStatus), 
    //             "The employee name is not displayed in the search results! Expected: " + selectedEmploymentStatus + ", Found: " + displayedStatuses);

    //     // List<String> displayedNames = pimPage.getDisplayedEmployeeNamesAfterSearchKeyApplied();
    //     // Assert.assertTrue(displayedNames.contains(searchedEmployeeName), 
    //     //         "The employee name is not displayed in the search results! Expected: " + searchedEmployeeName + ", Found: " + displayedNames);

    // }
}