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

    public void clickLogin( ) {
        btn_login.click();
    }
}
