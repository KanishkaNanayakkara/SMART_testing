package com.testing.uiTesting.pages.recruitment;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RecruitmentPage {

    public RecruitmentPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]/button[1]" )
    private WebElement btn_add;

    @FindBy(xpath = "//input[@placeholder='First Name']" )
    private WebElement txt_name_first;
    
    @FindBy(xpath = "//input[@placeholder='Middle Name']" )
    private WebElement txt_name_middle;

    @FindBy(xpath = "//input[@placeholder='Last Name']" )
    private WebElement txt_name_last;

    @FindBy(xpath = "//input[@placeholder='Type here']" )
    private WebElement txt_Email;
    
    @FindBy(xpath = "//button[@type='submit']" )
    private WebElement btn_submit;

    @FindBy(xpath = "//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']")
    private static WebElement successMessage;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")
    private static WebElement errorMessage;

    @FindBy(xpath = "//input[@placeholder='Enter comma seperated words...']")
    private static WebElement txt_keyword;

    @FindBy(xpath = "//body/div[@id='app']/div[1]/div[2]/div[2]/div[2]/div[1]/form[1]/div[3]/div[1]/div[2]/div[1]/div[2]/input[1]")
    private static WebElement txt_contact;

    @FindBy(xpath = "//button[@type='submit']")
    private static WebElement btn_search;

    @FindBy(xpath = "//i[@class = 'oxd-icon bi-eye-fill']")
    private static WebElement btn_edit;

    @FindBy(xpath = "//span[@class='oxd-switch-input oxd-switch-input--active --label-left'][1]")
    private static WebElement switch_edit;

    public void typeFirstName(String name) {
        txt_name_first.sendKeys(name);
    }
    public void typeMiddleName(String name) {
        txt_name_middle.sendKeys(name);
    }
    public void typeLastName(String name) {
        txt_name_last.sendKeys(name);
    }
    public void typeEmail(String email) {
        txt_Email.sendKeys(email);
    }
    public void typeKeyword(String keyword) {
        txt_keyword.sendKeys(keyword);
    }
    public void typeContact(String contact){
        txt_contact.sendKeys(contact);
    }
    public void clearFirstName() {
        txt_name_first.click();
        txt_name_first.sendKeys(Keys.CONTROL + "a");
        txt_name_first.sendKeys(Keys.BACK_SPACE);
    }
    public void clearLastName() {
        txt_name_last.click();
        txt_name_last.sendKeys(Keys.CONTROL + "a");
        txt_name_last.sendKeys(Keys.BACK_SPACE);
    }
    public void clearContact(){
        txt_contact.click();
        txt_contact.sendKeys(Keys.CONTROL + "a");
        txt_contact.sendKeys(Keys.BACK_SPACE);
    }
    public  String getSuccessMessage() {
        return successMessage.getText();
    }
    public  String getErrorMessage() {
        return errorMessage.getText();
    }
    public String getCreatedCandidateFirstName(){
        return txt_name_first.getDomProperty("value");
    }
    public String getCreatedCandidateLastName(){
        return txt_name_last.getDomProperty("value");
    }
    public String getCreatedCandidateEmail(){
        return txt_Email.getDomProperty("value");
    }
    public String getUpdatedCandidateMobileNumber(){
        return txt_contact.getDomProperty("value");
    }
    public void clickSave(){
        btn_submit.click();
    }
    public void clickSearch(){    
        btn_search.click();
    }
    public void clickEdit(){
        btn_edit.click();
    }
    public void clickSwitchEdit(){
        switch_edit.click();
    }
    public void clickSubmit() {
        btn_submit.click();
    }
    public void clickAdd() {
        btn_add.click();
    }

}
