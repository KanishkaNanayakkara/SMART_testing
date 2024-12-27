package com.testing.uiTesting.pages.sidebarNavigation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SideBarNavigationPanelPage {

    @FindBy(xpath = "//span[normalize-space()='My Info']")
    private WebElement myInfo;

    @FindBy(xpath = "//span[normalize-space()='Admin']")
    private WebElement btn_search; 

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'][normalize-space()='PIM']")
    private WebElement btn_pim;

    @FindBy(xpath = "//span[normalize-space()='Recruitment']")
    private WebElement recruitment;

    public SideBarNavigationPanelPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickMyInfo() {
        myInfo.click();
    }

    public void clickMenuOption(WebElement menuOption) {
        menuOption.click();
    }

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
