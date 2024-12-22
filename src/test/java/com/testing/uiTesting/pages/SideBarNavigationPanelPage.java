package com.testing.uiTesting.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SideBarNavigationPanelPage {

    private WebDriver driver;

    @FindBy(xpath = "//span[normalize-space()='Admin']")
    private WebElement btn_search;

    //a[@class='oxd-main-menu-item active']

    @FindBy(xpath = "//a[@class='oxd-main-menu-item active']")
    private WebElement btn_pim;

    public SideBarNavigationPanelPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickPIM( ) {
        btn_pim.click();
    }
}
