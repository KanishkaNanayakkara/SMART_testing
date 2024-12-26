package com.testing.uiTesting.pages.PIM;

import java.io.IOException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddEmployeePage {

    private WebDriver driver;

    @FindBy(xpath = "//span[text()='PIM']")
    public WebElement pimMenu;

    @FindBy(xpath = "//a[text()='Add Employee']")
    public WebElement addEmployeeOption;

    @FindBy(xpath = "//input[@placeholder='First Name']")
    public WebElement firstNameField;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    public WebElement lastNameField;

    @FindBy(xpath = "//button[@class='oxd-icon-button oxd-icon-button--solid-main employee-image-action']")
    public WebElement addImageButton;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    public WebElement saveButton;

    @FindBy(xpath = "//h6[normalize-space()='Personal Details']")
    public WebElement profileHeading;

    @FindBy(xpath = "//div[@class='oxd-toast-content oxd-toast-content--success']")
    public WebElement confirmationMessage;

    @FindBy(xpath = "//div[@class='oxd-input-group']//div[1]//span[1]")
    public WebElement firstNameErrorMessageContainer;

    @FindBy(xpath = "(//input)[6]")
    public WebElement employeeIdField;

    @FindBy(xpath = "//input[@type='file']")
    public WebElement fileInput;

    public AddEmployeePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToAddEmployeePage() {
        pimMenu.click();
        addEmployeeOption.click();
    }

    public void enterFirstName(String firstName) {
        firstNameField.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        lastNameField.sendKeys(lastName);
    }

    public void enterEmployeeId(String employeeId) {
        employeeIdField.clear();
        employeeIdField.sendKeys(Keys.CONTROL + "a");
        employeeIdField.sendKeys(Keys.DELETE);
        employeeIdField.sendKeys(employeeId);
    }

    public void enterEmployeeDetails(String firstName, String lastName, String employeeId) {
        this.enterFirstName(firstName);
        this.enterLastName(lastName);
        this.enterEmployeeId(employeeId);
    }

    public void uploadEmployeeImage(String imagePath) throws IOException, InterruptedException {
        Thread.sleep(5000);
        addImageButton.click();
        Thread.sleep(5000); // Allow the file dialog to open
        fileInput.sendKeys(imagePath);
        Thread.sleep(5000);
    }

    public void clickSaveButton() {
        saveButton.click();
    }

    public String getProfileHeading() {
        return profileHeading.getText();
    }

    public String getRequiredValidationErrorMessage() {
        return firstNameErrorMessageContainer.getText();
    }

    public String getConfirmationMessage() {
        return confirmationMessage.getText();
    }
}
