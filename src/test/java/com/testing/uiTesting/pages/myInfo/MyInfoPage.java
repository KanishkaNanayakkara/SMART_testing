package com.testing.uiTesting.pages.myInfo;
import java.time.Duration;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyInfoPage {

    private WebDriver driver;

    @FindBy(xpath = "//input[@placeholder='First Name']")
    private static WebElement txt_employee_name;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement btn_save;

    @FindBy(xpath = "//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']")
    private static WebElement successMessage;

    @FindBy(xpath = "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")
    private static WebElement errorMessage;

    public MyInfoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void typeEmployeeName(String user) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(txt_employee_name));
        txt_employee_name.click();
        txt_employee_name.sendKeys(Keys.CONTROL + "a");
        txt_employee_name.sendKeys(Keys.BACK_SPACE);
        txt_employee_name.sendKeys(user);
    }

    public void clickSave() {
        btn_save.click();
    }

    public static String getSuccessMessage() {
        return successMessage.getText();
    }

    public static String getErrorMessage() {
        return errorMessage.getText();
    }

    public String getUpdatedEmployeeName() {
        return txt_employee_name.getDomProperty("value"); 
    }

}