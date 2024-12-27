package com.testing.uiTesting.tests.pim;

import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.testing.uiTesting.base.BaseUITest;
import com.testing.uiTesting.pages.pim.PIMPage;
import com.testing.uiTesting.pages.sidebarNavigation.SideBarNavigationPanelPage;

public class SearchEmployeeTest extends BaseUITest{

    @Test
    public void verifySearchEmployeeByNameDisplaysCorrectResults() {
        PIMPage pimPage = new PIMPage(driver);

        SideBarNavigationPanelPage sideBarNavigationPanelPage = new SideBarNavigationPanelPage(driver);
        sideBarNavigationPanelPage.clickPIM();

        String searchedEmployeeName = pimPage.typeEmployeeName();
        pimPage.clickSearch();

        //Verify the result
        List<String> displayedNames = pimPage.getDisplayedEmployeeNamesAfterSearchKeyApplied();
        Assert.assertTrue(displayedNames.contains(searchedEmployeeName), 
                "The employee name is not displayed in the search results! Expected: " + searchedEmployeeName + ", Found: " + displayedNames);
    }

    @Test
    public void verifySearchEmployeeByIdDisplaysCorrectResults() {
        PIMPage pimPage = new PIMPage(driver);

        SideBarNavigationPanelPage sideBarNavigationPanelPage = new SideBarNavigationPanelPage(driver);
        sideBarNavigationPanelPage.clickPIM();

        String searchedEmployeeId = pimPage.typeEmployeeId();
        pimPage.clickSearch();

        // Verify the result
        String displayedEmployeeId = pimPage.getDisplayedEmployeeIdAfterSearchKeyApplied();
        Assert.assertEquals(displayedEmployeeId, searchedEmployeeId,
                "The employee ID in the search results does not match the searched ID! Expected: " + searchedEmployeeId
                        + ", Found: " + displayedEmployeeId);
    }
}