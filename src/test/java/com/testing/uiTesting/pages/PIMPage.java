package com.testing.uiTesting.pages;

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

    @FindBy(xpath = "(//div[@role='rowgroup']//div[@role='row'])[1]//div[2]")
    private WebElement firstRowSecondColumn;

    public PIMPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void typeEmployeeName(String user) {
        txt_employee_name.sendKeys(user);
    }

    public void clickSearch( ) {
        btn_search.click();
    }

    public String getDisplayedEmployeeName() {
        return firstRowSecondColumn.getText();
    }
    
    
    
}
