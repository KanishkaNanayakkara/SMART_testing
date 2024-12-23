package com.testing.uiTesting.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PIMPage {

    private WebDriver driver;

    //div[@class='oxd-autocomplete-text-input oxd-autocomplete-text-input--focus']//input[@placeholder='Type for hints...']

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    private WebElement txt_employee_name;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement btn_search;

    @FindBy(xpath = "(//div[@role='rowgroup']//div[@role='row'])[2]//div[3]")
    private WebElement firstRowSecondColumn;

    @FindBy(xpath = "(//div[@role='rowgroup']//div[@role='row'])[2]//div[3]")
    private WebElement EmployeeNameCell;

    @FindBy(xpath = "//div[@class='orangehrm-container']")
    private WebElement employeeTable;

    @FindBy(xpath = "//div[@role='rowgroup']//div[@role='row']")
    private List<WebElement> tableRows;

    //body/div[@id='app']/div[@class='oxd-layout orangehrm-upgrade-layout']/div[@class='oxd-layout-container']/div[@class='oxd-layout-context']/div[@class='orangehrm-background-container']/div[@class='orangehrm-paper-container']/div[@class='orangehrm-container']/div[@role='table']/div[2]/div[1]/div[1]

    public PIMPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String typeEmployeeName() {
        String employeeNameSearchKey = this.getEmployeeNameFromTheTableToSearch();
        txt_employee_name.sendKeys(employeeNameSearchKey);
        return employeeNameSearchKey;
    }

    public void clickSearch( ) {
        btn_search.click();
    }

    public String getEmployeeNameFromTheTableToSearch( ) {
        return EmployeeNameCell.getText();
    }

    public List<String> getDisplayedEmployeeNamesafterSearchKeyApplied() {
        List<String> employeeNames = new ArrayList<>();

        for (WebElement row : tableRows) {
            // Get the name from column 3 of each row
            WebElement nameCell = row.findElement(By.xpath(".//div[3]"));
            employeeNames.add(nameCell.getText());
        }

        return employeeNames;
    }
     
}
