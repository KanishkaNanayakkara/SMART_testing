package com.testing.uiTesting.pages.leave;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ApplyLeavePage {

    private WebDriver driver;

    private WebDriverWait wait;

    private String my_leave_request_url = "https://opensource-demo.orangehrmlive.com/web/index.php/leave/viewMyLeaveList";

    private String apply_leave_request_url = "https://opensource-demo.orangehrmlive.com/web/index.php/leave/applyLeave";

    @FindBy(xpath = "//label[text()='Leave Type']/following::div[@class='oxd-select-text oxd-select-text--active']")
    private WebElement leave_type_dropdown;

    @FindBy(xpath = "//div[contains(@class, 'oxd-select-option')][2]")
    private WebElement first_option;

    @FindBy(xpath = "//label[text()='From Date']/following::input[1]")
    private WebElement from_date_input;

    @FindBy(xpath = "//label[text()='To Date']/following::input[1]")
    private WebElement to_date_input;

    @FindBy(xpath = "//label[text()='Duration']/following::div[contains(@class, 'oxd-select-text')][1]")
    private WebElement duration_dropdown;

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

    @FindBy(xpath = "//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']")
    public WebElement confirmation_message;

    public ApplyLeavePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void navigateToApplyLeavePage() {
        driver.get(apply_leave_request_url);
    }

    public void fillApplyLeaveForm(String fromDate, String toDate, String comment) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'oxd-form-loader')]")));
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

        if (!isOptionAlreadySelected(duration_dropdown, "Full Day")) {
            duration_dropdown.click();
            first_option_from_duration_dropdown.click();
        }
        
        comment_input.sendKeys(comment);
        submit_button.click();
    }

    public boolean isLeaveRequestPresent(String expectedDate, String expectedLeaveType, String expectedStatus) {
        List<WebElement> rows = driver.findElements(By.xpath("//div[@class='oxd-table-body']//div[@role='row']"));

        for (WebElement row : rows) {
            String date = row.findElement(By.xpath(".//div[@role='cell'][2]/div")).getText().trim();
            String leaveType = row.findElement(By.xpath(".//div[@role='cell'][4]/div")).getText().trim();
            String status = row.findElement(By.xpath(".//div[@role='cell'][7]/div")).getText().trim();

            if (date.equals(expectedDate) && leaveType.equals(expectedLeaveType) && status.contains(expectedStatus)) {
                return true;
            }
        }

        return false;
    }

    public void navigateToMyLeaveRequestPage() {
        driver.get(my_leave_request_url);
    }

    public String getConfirmation_message() {
        wait.until(ExpectedConditions.visibilityOf(confirmation_message));
        return confirmation_message.getText();
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
