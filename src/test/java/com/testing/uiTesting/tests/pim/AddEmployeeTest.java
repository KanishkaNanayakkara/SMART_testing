package com.testing.uiTesting.tests.pim;

import java.io.IOException;
import java.text.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.testing.uiTesting.base.BaseUITest;
import com.testing.uiTesting.pages.pim.AddEmployeePage;
import com.testing.uiTesting.utils.Utils;

public class AddEmployeeTest extends BaseUITest{
     
    @Test(priority = 1)
    public void verifyEmployeeCanBeAddedSuccessfully() throws Exception {
        AddEmployeePage addEmployeePage = new AddEmployeePage(driver);
        addEmployeePage.navigateToAddEmployeePage();

        String firstName = "Radha";
        String lastName = "Kamani";
        String employeeId= "UOM" +Utils.generateRandomNumber(100,4999);
        addEmployeePage.enterEmployeeDetails(firstName, lastName, employeeId);

        String imagePath = System.getProperty("user.dir") + "/src/test/resources/Images/employeePhoto.png";
        System.out.println("Image file path: " + imagePath);

        addEmployeePage.uploadEmployeeImage(imagePath);
        addEmployeePage.clickSaveButton();

        String profileHeading = addEmployeePage.getProfileHeading();
        // String confirmationMessage = addEmployeePage.getConfirmationMessage();

        if (profileHeading.contains("Personal Details")) {
            System.out.println("Employee added successfully!");
        } else {
            System.out.println("Failed to add employee!");
        }

        Assert.assertEquals(profileHeading, "Personal Details", "Added employee profile is not vissible!");
        // Assert.assertTrue(confirmationMessage.contains("Successfully Saved"),
        // "Addition successful!");
    }

    @Test(priority = 2)
    public void verifyErrorMessageWhenFirstNameIsMissing() throws InterruptedException, IOException, ParseException {
        AddEmployeePage addEmployeePage = new AddEmployeePage(driver);
        addEmployeePage.navigateToAddEmployeePage();
        String lastName = "Nanayakkara";
        String employeeId= "UOM" +Utils.generateRandomNumber(100,999);
        addEmployeePage.enterLastName(lastName);
        addEmployeePage.enterEmployeeId( employeeId);

        String imagePath = System.getProperty("user.dir") + "/src/test/resources/Images/employeePhoto.png";
        System.out.println("Image file path: " + imagePath);

        addEmployeePage.uploadEmployeeImage(imagePath);
        addEmployeePage.clickSaveButton();

        String displayedErrorMessage = addEmployeePage.getRequiredValidationErrorMessage();
        Assert.assertEquals(displayedErrorMessage, "Required", "Validation is not happening!");
    }

}