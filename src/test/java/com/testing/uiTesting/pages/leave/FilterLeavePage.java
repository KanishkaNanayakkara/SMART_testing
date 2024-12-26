package com.testing.uiTesting.pages.leave;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FilterLeavePage {

    private WebDriver driver;

    private WebDriverWait wait;

    private String leave_list_url = "https://opensource-demo.orangehrmlive.com/web/index.php/leave/viewLeaveList";

    @FindBy(xpath = "//button[@type='reset']")
    private WebElement reset_button;

    @FindBy(xpath = "//div[contains(@class, 'oxd-select-text')]")
    private WebElement leave_status_dropdown;

    @FindBy(xpath = "//div[contains(@class, 'oxd-select-option')][2]")
    private WebElement canceled_option;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement apply_filter_button;

    public FilterLeavePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void navigateToLeaveListPage() {
        driver.get(leave_list_url);
    }

    public void applyFilter() {
        reset_button.click();
        leave_status_dropdown.click();
        canceled_option.click();
        apply_filter_button.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='oxd-table-body']")));
        apply_filter_button.click();
    }

    public boolean areAllRowsMatchingStatus(String expectedStatus) {
        WebElement tableBody = new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='oxd-table-body']")));
    
        List<WebElement> rows = tableBody.findElements(By.xpath("./div[@class='oxd-table-card']"));
    
        if (rows.isEmpty()) {
            System.out.println("The table is empty.");
            return true;
        }
    
        for (WebElement row : rows) {
            try {
                WebElement statusCell = row.findElement(By.xpath("//div[contains(@class, 'oxd-table-row')]//div[contains(@class, 'oxd-table-cell')][7]"));
    
                String statusText = statusCell.getText().trim();
    
                if (!statusText.startsWith(expectedStatus)) {
                    System.out.println("Row with status: " + statusText + " is not '" + expectedStatus + "'");
                    return false;
                }
            } catch (NoSuchElementException e) {
                System.out.println("Status cell not found in row.");
                return false;
            }
        }
    
        return true;
    }
}
