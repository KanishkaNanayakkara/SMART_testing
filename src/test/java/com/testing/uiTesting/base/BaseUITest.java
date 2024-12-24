package com.testing.uiTesting.base;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.Cookie;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseUITest {

    protected WebDriver driver;
    private static Set<Cookie> savedCookies;

    @BeforeMethod
    public void setUp() {
        // Set up WebDriverManager to handle ChromeDriver
        WebDriverManager.chromedriver().setup();

         // Configure ChromeOptions
         ChromeOptions options = new ChromeOptions();
         options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu");
 
         driver = new ChromeDriver(options);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        // Restore cookies if they exist
        if (savedCookies != null) {
            for (Cookie cookie : savedCookies) {
                driver.manage().addCookie(cookie);
            }
            driver.navigate().refresh(); // Refresh to apply cookies
        } else {
            // Perform login if cookies are not available
            performLogin();
            savedCookies = driver.manage().getCookies(); // Save cookies after login
        }

        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void performLogin() {
        driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
    }
}
