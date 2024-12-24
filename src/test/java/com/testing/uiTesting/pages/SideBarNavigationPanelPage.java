package com.testing.uiTesting.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SideBarNavigationPanelPage {

 private WebDriver driver;

    // Locate the Leave menu option
    @FindBy(xpath = "//span[normalize-space()='My Info']")
    private WebElement myInfo;

    // Constructor to initialize the elements
    public SideBarNavigationPanelPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to click the my info option
    public void clickMyInfo() {
        myInfo.click();
    }
    
    // General method to click any available menu option
    public void clickMenuOption(WebElement menuOption) {
        menuOption.click();
    }
    
    // Verify if my info is active
    @SuppressWarnings("deprecation")
    public boolean isLeaveMenuActive() {
        return myInfo.getAttribute("class").contains("active");
    }

}