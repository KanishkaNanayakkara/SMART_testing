package com.testing.uiTesting.pages.applyLeave;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ApplyLeavePage {

    private WebDriver driver;

    private String my_leave_request_url = "https://opensource-demo.orangehrmlive.com/web/index.php/leave/viewMyLeaveList";

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'][normalize-space()='Leave']")
    private WebElement sidebar_leave_option;

    @FindBy(xpath = "//a[normalize-space()='Apply']")
    private WebElement apply_leave_button;

    @FindBy(xpath = "//label[text()='Leave Type']/following::div[@class='oxd-select-text oxd-select-text--active']")
    private WebElement leave_type_dropdown;

    @FindBy(xpath = "//div[contains(@class, 'oxd-select-option')][2]")
    private WebElement first_option;

    @FindBy(xpath = "//label[text()='From Date']/following::input[1]")
    private WebElement from_date_input;

    @FindBy(xpath = "//label[text()='To Date']/following::input[1]")
    private WebElement to_date_input;

    @FindBy(xpath = "//label[text()='Duration']/following::div[contains(@class, 'oxd-select-text')][1]")
    private WebElement durationDropdown;

    @FindBy(xpath = "//div[contains(@class, 'oxd-select-option')][2]")
    private WebElement first_option_from_duration_dropdown;

    @FindBy(xpath = "//label[text()='Partial Days']/following::div[contains(@class, 'oxd-select-text')][1]")
    private WebElement partial_days_dropdown;

    @FindBy(xpath = "//div[contains(@class, 'oxd-select-option')][2]")
    private WebElement first_option_partial_days;


    @FindBy(xpath = "//textarea[contains(@class, 'oxd-textarea--active')]")
    private WebElement comment_input;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submit_button;

    @FindBy(xpath = "//div[@class='oxd-toast-content oxd-toast-content--success']")
    public WebElement confirmationMessage;

    @FindBy(xpath = "//div[@class='oxd-table-body']//div[@class='oxd-table-card'][1]//div[@role='cell'][2]/div")
    private WebElement firstRowDate;

    @FindBy(xpath = "//div[@class='oxd-table-body']//div[@class='oxd-table-card'][1]//div[@role='cell'][3]/div")
    private WebElement firstRowEmployeeName;

    @FindBy(xpath = "//div[@class='oxd-table-body']//div[@class='oxd-table-card'][1]//div[@role='cell'][4]/div")
    private WebElement firstRowLeaveType;

    @FindBy(xpath = "//div[@class='oxd-table-body']//div[@class='oxd-table-card'][1]//div[@role='cell'][7]/div")
    private WebElement firstRowStatus;

    @FindBy(xpath = "//p[@class='oxd-userdropdown-name']")
    private WebElement firstNameInput;

    @FindBy(xpath = "//input[@name='middleName']")
    private WebElement middleNameInput;

    @FindBy(xpath = "//input[@name='lastName']")
    private WebElement lastNameInput;

    @FindBy(xpath = "//span[normalize-space()='My Info']")
    private WebElement my_info_tab;

    public ApplyLeavePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToApplyLeavePage() {
        sidebar_leave_option.click();
        apply_leave_button.click();
    }

    public void fillApplyLeaveForm(String fromDate, String toDate, String comment) {
        leave_type_dropdown.click();
        first_option.click();
        from_date_input.clear();
        from_date_input.sendKeys(fromDate);
        to_date_input.sendKeys(Keys.CONTROL + "a");
        to_date_input.sendKeys(Keys.DELETE);
        to_date_input.sendKeys(toDate);
        if (isElementVisible(partial_days_dropdown)) {
            partial_days_dropdown.click();
            first_option_partial_days.click();
        }
        if (!isOptionAlreadySelected(durationDropdown, "Full Day")) {
            durationDropdown.click();
            first_option_from_duration_dropdown.click();
        }
        comment_input.sendKeys(comment);
        submit_button.click();
    }

    public boolean isLeaveRequestInFirstRow(String expectedDate, String expectedLeaveType, String expectedStatus) {
        String actualDate = firstRowDate.getText();
        String actualLeaveType = firstRowLeaveType.getText();
        String actualStatus = firstRowStatus.getText();
    
        return actualDate.equals(expectedDate) &&
               actualLeaveType.equals(expectedLeaveType) &&
               actualStatus.contains(expectedStatus);
    }

    public void navigateToMyLeaveRequestPage() {
        driver.get(my_leave_request_url);
    }

    public String getConfirmationMessage() {
        return confirmationMessage.getText();
    }

    private boolean isElementVisible(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isOptionAlreadySelected(WebElement dropdownElement, String desiredOption) {
        try {
            String selectedOption = dropdownElement.getText();
            return selectedOption.trim().equalsIgnoreCase(desiredOption.trim());
        } catch (Exception e) {
            return false;
        }
    }
}
