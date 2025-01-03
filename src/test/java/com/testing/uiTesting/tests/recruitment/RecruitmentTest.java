package com.testing.uiTesting.tests.recruitment;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.testing.uiTesting.base.BaseUITest;
import com.testing.uiTesting.pages.sidebarNavigation.SideBarNavigationPanelPage;
import com.testing.uiTesting.pages.recruitment.RecruitmentPage;

public class RecruitmentTest extends BaseUITest {
    
    @Test
    public void verifyNavigationToRecruitmentModule() {
        
        SideBarNavigationPanelPage sideBarNavigationPanelPage = new SideBarNavigationPanelPage(driver);

        sideBarNavigationPanelPage.clickRecruitment();

        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/recruitment/viewCandidates";
        String currentUrl = driver.getCurrentUrl();

        Assert.assertEquals(currentUrl, expectedUrl, "Failed to navigate to the Recruitment page.");
    }

    @Test
    public void verifyAddNewCandidate() {
        
        SideBarNavigationPanelPage sideBarNavigationPanelPage = new SideBarNavigationPanelPage(driver);
        RecruitmentPage recruitmentPage = new RecruitmentPage(driver);

        sideBarNavigationPanelPage.clickRecruitment();
        
        String candidateFirstName = "Jhone";
        String candidateMiddleName = "Doily";
        String candidateLastName = "Fernando";
        String candidateEmail = "jhonedoily@gmail.com";

        recruitmentPage.clickAdd();
        recruitmentPage.typeFirstName(candidateFirstName);
        recruitmentPage.typeMiddleName(candidateMiddleName);
        recruitmentPage.typeLastName(candidateLastName);
        recruitmentPage.typeEmail(candidateEmail);
        recruitmentPage.typeKeyword(candidateEmail);
        recruitmentPage.clickSubmit();

        String Message = recruitmentPage.getSuccessMessage();
        String CreatedCandidateFirstName = recruitmentPage.getCreatedCandidateFirstName();
        String CreatedCandidateEmail = recruitmentPage.getCreatedCandidateEmail();

        Assert.assertTrue(Message.toLowerCase().contains("successfully saved"), "The success message does not indicate the candidate was successfully saved. Message: ");
        Assert.assertEquals(CreatedCandidateFirstName, candidateFirstName, "The candidate first name does not match the input.");
        Assert.assertEquals(CreatedCandidateEmail, candidateEmail, "The candidate email does not match the input.");
    }

    @Test
    public void verifyUpdateCreatedCandidate() {
        
        SideBarNavigationPanelPage sideBarNavigationPanelPage = new SideBarNavigationPanelPage(driver);
        RecruitmentPage recruitmentPage = new RecruitmentPage(driver);

        sideBarNavigationPanelPage.clickRecruitment();

        String newCandidateFirstName = "Sunil";
        String newCandidateLastName = "Gimhana";
        String MobileNumber = "0712345678";
        String CreatedCandidateKeyWord = "jhonedoily@gmail.com";

        recruitmentPage.typeKeyword(CreatedCandidateKeyWord);
        recruitmentPage.clickSearch();
        recruitmentPage.clickEdit();
        recruitmentPage.clickSwitchEdit();
        recruitmentPage.clearFirstName();
        recruitmentPage.clearLastName();
        recruitmentPage.clearContact();
        recruitmentPage.typeFirstName(newCandidateFirstName);
        recruitmentPage.typeLastName(newCandidateLastName);
        recruitmentPage.typeContact(MobileNumber);
        recruitmentPage.clickSave();
        
        String Message = recruitmentPage.getSuccessMessage();
        String updatedCandidateFirstName = recruitmentPage.getCreatedCandidateFirstName();
        String updatedCandidateLastName = recruitmentPage.getCreatedCandidateLastName();
        String updatedCandidateMobileNumber = recruitmentPage.getUpdatedCandidateMobileNumber();

        Assert.assertTrue(Message.toLowerCase().contains("successfully updated"), "The success message does not indicate the candidate was successfully Updated.");
        Assert.assertEquals(updatedCandidateFirstName, newCandidateFirstName, "The candidate first name does not match the input.");
        Assert.assertEquals(updatedCandidateLastName, newCandidateLastName, "The candidate last name does not match the input.");
        Assert.assertEquals(updatedCandidateMobileNumber, MobileNumber, "The candidate mobile number does not match the input.");
    }
}
