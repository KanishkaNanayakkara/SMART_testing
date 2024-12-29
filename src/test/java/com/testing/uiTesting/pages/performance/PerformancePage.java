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

    @FindBy(xpath = "/html/body/div[1]/div[1]/div[1]/header/div[2]/nav/ul/li[3]" )
    private WebElement my_trackers_select;

    @FindBy(xpath = "/html/body/div[1]/div[1]/div[2]/div[2]/div/div/div[3]/div/div[2]/div/div/div[4]/div/button" )
    private WebElement btn_view;

    @FindBy(xpath = "/html/body/div[1]/div[1]/div[2]/div[2]/div/div[2]/div[1]/button" )
    private WebElement btn_addLog;

    @FindBy(xpath = "/html/body/div[1]/div[1]/div[2]/div[2]/div/div[3]/div/div/div/form/div[1]/div/div[2]/input" )
    private WebElement txt_log_name;
    
    @FindBy(xpath = "/html/body/div[1]/div[1]/div[2]/div[2]/div/div[3]/div/div/div/form/div[3]/div/div[2]/textarea" )
    private WebElement txt_comment;
    
    @FindBy(xpath = "/html/body/div[1]/div[1]/div[2]/div[2]/div/div[3]/div/div/div/form/div[4]/button[2]" )
    private WebElement save_btn;

    @FindBy(xpath = "//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']" )
    private WebElement successMessage;

    @FindBy(xpath = "/html/body/div[1]/div[1]/div[2]/div[2]/div/div[2]/div[2]/div[1]/div/div[2]/div[2]/div[1]/li/button" )
    private WebElement options_btn;

    @FindBy(xpath = "/html/body/div[1]/div[1]/div[2]/div[2]/div/div[2]/div[2]/div[1]/div/div[2]/div[2]/div[1]/li/ul/li[1]/p" )
    private WebElement edit_btn;

    @FindBy(xpath = "/html/body/div[1]/div[1]/div[2]/div[2]/div/div[3]/div/div/div/form/div[2]/div/button[2]" )
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

    public void clickNegativeFeedback(){
        negative_feedback.click();
    }
}
