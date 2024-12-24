package com.testing.uiTesting.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SideBarNavigationPanelPage {

    private WebDriver driver;

    @FindBy(xpath = "//span[normalize-space()='Admin']")
    private WebElement btn_search; 

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'][normalize-space()='PIM']")
    private WebElement btn_pim;

    public SideBarNavigationPanelPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickPIM( ) {
        btn_pim.click();
    }
}