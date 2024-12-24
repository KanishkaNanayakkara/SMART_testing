package com.testing.uiTesting.pages.PIM;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.testing.uiTesting.pages.SideBarNavigationPanelPage;

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

    @FindBy(xpath = "//body/div[@id='app']/div[@class='oxd-layout orangehrm-upgrade-layout']/div[@class='oxd-layout-container']/div[@class='oxd-layout-context']/div[@class='orangehrm-background-container']/div[@class='oxd-table-filter']/div[@class='oxd-table-filter-area']/form[@class='oxd-form']/div[@class='oxd-form-row']/div[@class='oxd-grid-4 orangehrm-full-width-grid']/div[3]/div[1]/div[2]/div[1]/div[1]")
    private WebElement input_employment_status;

    @FindBy(xpath = "//button[@type='reset']")
    private WebElement btn_reset;

    public PIMPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToPIM() {
        SideBarNavigationPanelPage sideBarNavigationPanelPage = new SideBarNavigationPanelPage(driver);
        sideBarNavigationPanelPage.clickPIM();
        Assert.assertTrue(driver.findElement(By.xpath("//input[@placeholder='Type for hints...']")).isDisplayed(),
                "Failed to navigate to the PIM page!");
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

    public String selectEmploymentStatusFromTheDropdownMenuToSearch() {
        input_employment_status.click(); 
        WebElement firstOption = driver.findElement(By.xpath("//div[@role='listbox']/div[1]")); // Get the first option
        String selectedValue = firstOption.getText(); 
        firstOption.click(); 
        return selectedValue.trim();
        
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