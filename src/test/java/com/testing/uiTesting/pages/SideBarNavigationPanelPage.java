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

    @FindBy(xpath = "//span[normalize-space()='Admin']")
    private WebElement btn_search; 

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'][normalize-space()='PIM']")
    private WebElement btn_pim;

    //Locate the Recruitment menu option
    @FindBy(xpath = "//span[normalize-space()='Recruitment']")
    private WebElement recruitment;

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

    public void clickPIM( ) {
        btn_pim.click();
    }
    public void clickRecruitment() {
        recruitment.click();
    }

}
