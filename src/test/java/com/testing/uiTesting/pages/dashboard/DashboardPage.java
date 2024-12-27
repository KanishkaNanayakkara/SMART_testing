package com.testing.uiTesting.pages.dashboard;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {

    private WebDriver driver;

    private WebDriverWait wait;

    private String dashboard_nav_link = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";

    @FindBy(xpath = "//button[@title='Assign Leave']//*[name()='svg']")
    private WebElement quickLaunchAssignLeave;

    @FindBy(id = "employee_distribution")
    private WebElement employeeStatisticsSection;

    @FindBy(xpath = "//a[@class='oxd-main-menu-item active']")
    private WebElement leaveMenu;

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
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void clickQuickLaunchAssignLeave() {
        wait.until(ExpectedConditions.visibilityOf(quickLaunchAssignLeave));
        quickLaunchAssignLeave.click();
    }

    public boolean isEmployeeStatisticsSectionVisible() {
        return employeeStatisticsSection.isDisplayed();
    }

    public void navigateToDashboard() {
        driver.get(dashboard_nav_link);
    }

    public void clickLeaveMenu() {
        leaveMenu.click();
    }

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

    public boolean isChartUpdated(String subUnitName) {
        WebElement chartElement = driver.findElement(By.xpath(
                "//body/div[@id='app']/div[@class='oxd-layout orangehrm-upgrade-layout']/div[@class='oxd-layout-container']/div[@class='oxd-layout-context']/div[@class='oxd-grid-3 orangehrm-dashboard-grid']/div[6]/div[1]"));
        String chartText = chartElement.getText();
        return chartText.contains(subUnitName);
    }
}