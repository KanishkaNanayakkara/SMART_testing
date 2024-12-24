package com.testing.uiTesting.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {

    private WebDriver driver;

    // Locator for Quick Launch section (replace with a generic way to handle dynamic buttons in the section)
    @FindBy(xpath = "//button[@title='Assign Leave']//*[name()='svg']")
    private WebElement quickLaunchAssignLeave;

    // Locator for Employee Statistics section
    @FindBy(id = "employee_distribution")
    private WebElement employeeStatisticsSection;

    // Locator for Leave Module in Side Navigation
    @FindBy(xpath = "//a[@class='oxd-main-menu-item active']")
    private WebElement leaveMenu;

    // Locator for Sub-Unit buttons (Engineering, HR, etc.)
    @FindBy(xpath = "//span[@title='Engineering']")
    private WebElement engineeringButton;
    
    @FindBy(xpath = "(//span[@title='Human Resources'])[1]")
    private WebElement humanResourcesButton;

    @FindBy(xpath = "//span[@title='Administration']")
    private WebElement administrationButton;

    @FindBy(xpath = "//span[@title='Client Services']")
    private WebElement clientServicesButton;

    @FindBy(xpath = "//li[5]//span[2]")
    private WebElement unassignedButton;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Generic method to click Quick Launch items based on the item name
    public void clickQuickLaunchAssignLeave() {
        quickLaunchAssignLeave.click();
    }

    // Method to check if Employee Statistics section is visible
    public boolean isEmployeeStatisticsSectionVisible() {
        return employeeStatisticsSection.isDisplayed();
    }

    // Method to click on the Leave menu from the side navigation
    public void clickLeaveMenu() {
        leaveMenu.click();
    }

    // Generic method to click Sub-Unit buttons (Engineering, HR, etc.)
    public void clickSubUnit(String subUnitName) {
        switch (subUnitName) {
            case "Engineering":
                engineeringButton.click();
                break;
            case "Human Resources":
                humanResourcesButton.click();
                break;
            case "Administration":
                administrationButton.click();
                break;
            case "Client Services":
                clientServicesButton.click();
                break;
            case "Unassigned":
                unassignedButton.click();
                break;
            default:
                throw new IllegalArgumentException("Invalid sub-unit name: " + subUnitName);
        }
    }

    /// Method to check if the chart has updated after clicking on a sub-unit
    public boolean isChartUpdated(String subUnitName) {
        // Logic to verify if the chart has updated according to the selected sub-unit.

        WebElement chartElement = driver.findElement(By.xpath("//body/div[@id='app']/div[@class='oxd-layout orangehrm-upgrade-layout']/div[@class='oxd-layout-container']/div[@class='oxd-layout-context']/div[@class='oxd-grid-3 orangehrm-dashboard-grid']/div[6]/div[1]"));
        String chartText = chartElement.getText();

        // Verify that the chart's text or content indicates the change based on the selected sub-unit
        return chartText.contains(subUnitName);
    }

}
