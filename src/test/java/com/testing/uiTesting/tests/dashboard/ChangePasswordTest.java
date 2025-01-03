package com.testing.uiTesting.tests.dashboard;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.testing.uiTesting.base.BaseUITest;
import com.testing.uiTesting.pages.dashboard.ChangePasswordPage;
import com.testing.uiTesting.pages.sidebarNavigation.SideBarNavigationPanelPage;

public class ChangePasswordTest extends BaseUITest {

    @Test
    public void verifyPasswordChangeSucces() {
        SideBarNavigationPanelPage sideBar = new SideBarNavigationPanelPage(driver);
        sideBar.navigateToProfileSettings();

        ChangePasswordPage changePasswordPage = new ChangePasswordPage(driver);
        String currentPassword = "admin123";
        String newPassword = "newPassword123";

        changePasswordPage.enterCurrentPassword(currentPassword);
        changePasswordPage.enterNewPassword(newPassword);
        changePasswordPage.enterConfirmPassword(newPassword);
        changePasswordPage.clickSaveButton();

        String successMessage = changePasswordPage.getSuccessMessage();
        Assert.assertTrue(successMessage.contains("Successfully Saved"),
                "Password change failed or success message is incorrect!");
    }

}