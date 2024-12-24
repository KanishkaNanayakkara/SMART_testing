package com.testing.uiTesting.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver driver;

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement txt_username;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement txt_password;

    @FindBy(xpath = "//button[normalize-space()='Login']")
    private WebElement btn_login;

    @FindBy(xpath = "//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']")
    private WebElement btn_profile_dropdown;

    @FindBy(xpath = "//a[normalize-space()='Logout']")
    private WebElement btn_logout;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setUserName(String user) {
        txt_username.sendKeys(user);
    }

    public void setPassword(String password) {
        txt_password.sendKeys(password);
    }

    public void logout() {
        btn_profile_dropdown.click();
        btn_general_logout.click();
    }

    public void clickLogin( ) {
        btn_login.click();
    }
}
