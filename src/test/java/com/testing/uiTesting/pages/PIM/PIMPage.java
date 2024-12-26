package com.testing.uiTesting.pages.PIM;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PIMPage {

    private WebDriver driver;

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    private WebElement input_employee_name;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement btn_search;

    @FindBy(xpath = "(//div[@role='rowgroup']//div[@role='row'])[2]//div[3]")
    private WebElement firstRowSecondColumn;

    @FindBy(xpath = "(//div[@role='rowgroup']//div[@role='row'])[2]//div[3]")
    private WebElement EmployeeNameCell;

    @FindBy(xpath = "(//div[@role='rowgroup']//div[@role='row'])[2]//div[2]")
    private WebElement EmployeeIdCell;

    @FindBy(xpath = "//div[@class='orangehrm-container']")
    private WebElement employeeTable;

    @FindBy(xpath = "//div[@role='rowgroup']//div[@role='row']")
    private List<WebElement> tableRows;

    @FindBy(xpath = "//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@class='oxd-input oxd-input--active']")
    private WebElement input_employee_id; 

    @FindBy(xpath = "//button[@type='reset']")
    private WebElement btn_reset;

    public PIMPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String typeEmployeeName() {
        String employeeNameSearchKey = this.getEmployeeNameFromTheTableToSearch();
        input_employee_name.sendKeys(employeeNameSearchKey);
        return employeeNameSearchKey;
    }

    public String typeEmployeeId() {
        String employeeIdSearchKey = this.getEmployeeIdFromTheTableToSearch();
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", input_employee_id);
        input_employee_id.sendKeys(employeeIdSearchKey);
        return employeeIdSearchKey;
    }

    public void clickSearch( ) {
        btn_search.click();
    }

    public void clickReset( ) {
        btn_reset.click();
    }

    public String getEmployeeNameFromTheTableToSearch( ) {
        return EmployeeNameCell.getText();
    }

    public String getEmployeeIdFromTheTableToSearch( ) {
        return EmployeeIdCell.getText();
    }

    public List<String> getDisplayedEmployeeNamesAfterSearchKeyApplied() {
        List<String> employeeNames = new ArrayList<>();

        for (WebElement row : tableRows) {
            WebElement nameCell = row.findElement(By.xpath(".//div[3]"));
            employeeNames.add(nameCell.getText());
        }

        return employeeNames;
    }

    public String getDisplayedEmployeeIdAfterSearchKeyApplied() {
        return EmployeeIdCell.getText();
    }

    public List<String> getDisplayedEmploymentStatusAfterSearchKeyApplied() {
        List<String> EmploymentStatus = new ArrayList<>();

        for (WebElement row : tableRows) {
            WebElement employmentStatusCell = row.findElement(By.xpath(".//div[6]"));
            EmploymentStatus.add(employmentStatusCell.getText());
        }

        return EmploymentStatus;
    }
}