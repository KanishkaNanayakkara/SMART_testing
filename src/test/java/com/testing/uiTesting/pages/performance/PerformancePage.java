package com.testing.uiTesting.pages.performance;
import java.time.Duration;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PerformancePage {
    
    private WebDriver driver;

    @FindBy(xpath = "//a[normalize-space()='My Trackers']" )
    private WebElement my_trackers_select;

    @FindBy(xpath = "//button[@name='view']" )
    private WebElement btn_view;

    @FindBy(xpath = "//button[normalize-space()='Add Log']" )
    private WebElement btn_addLog;

    @FindBy(xpath = "/html/body/div[1]/div[1]/div[2]/div[2]/div/div[3]/div/div/div/form/div[1]/div/div[2]/input" )
    private WebElement txt_log_name;
    
    @FindBy(xpath = "/html/body/div[1]/div[1]/div[2]/div[2]/div/div[3]/div/div/div/form/div[3]/div/div[2]/textarea" )
    private WebElement txt_comment;
    
    @FindBy(xpath = "//button[normalize-space()='Save']" )
    private WebElement save_btn;

    @FindBy(xpath = "//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']" )
    private WebElement successMessage;

    @FindBy(xpath = "//i[@class='oxd-icon bi-three-dots-vertical']" )
    private WebElement options_btn;

    @FindBy(xpath = "//p[normalize-space()='Edit']" )
    private WebElement edit_btn;

    @FindBy(xpath = "//button[normalize-space()='Cancel']" )
    private WebElement close_btn;

    @FindBy(xpath = "//p[normalize-space()='Delete']" )
    private WebElement delete_btn;

    @FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin']" )
    private WebElement yes_delete_btn;

    @FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--text orangehrm-tracker-rating-button --deselected']" )
    private WebElement negative_feedback;

    public PerformancePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clearLogName() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(txt_log_name));
        txt_log_name.click();
        txt_log_name.sendKeys(Keys.CONTROL + "a");
        txt_log_name.sendKeys(Keys.BACK_SPACE);
    }

    public void clearLogComment() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(txt_comment));
        txt_comment.click();
        txt_comment.sendKeys(Keys.CONTROL + "a");
        txt_comment.sendKeys(Keys.BACK_SPACE);
    }

    public  String getSuccessMessage() {
        return successMessage.getText();
    }

    public String getCreatedLogName(){
        return txt_log_name.getDomProperty("value");
    }

    public String getCreatedLogComment(){
        return txt_comment.getDomProperty("value");
    }

    public void typeLogName(String name) {
        txt_log_name.sendKeys(name);
    }

    public void typeComment(String name) {
        txt_comment.sendKeys(name);
    }

    public void clickMyTrackers(){
        my_trackers_select.click();
    }

    public void clickViewBtn(){
        btn_view.click();
    }

    public void clickAddLog(){
        btn_addLog.click();
    }

    public void clickSaveLog(){
        save_btn.click();
    }

    public void clickOptions(){
        options_btn.click();
    }

    public void clickEdit(){
        edit_btn.click();
    }

    public void clickDelete(){
        delete_btn.click();
    }

    public void clickYesDelete(){
        yes_delete_btn.click();
    }

    public void clickNegativeFeedback(){
        negative_feedback.click();
    }

    public void clickClose(){
        close_btn.click();
    }
}
