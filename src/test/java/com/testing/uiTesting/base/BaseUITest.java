package com.testing.uiTesting.base;

import java.time.Duration;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.Cookie;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseUITest {

    protected static WebDriver driver;
    private static Set<Cookie> savedCookies;
    private static boolean isFirstTest = true;

    @BeforeSuite
    public void suiteSetUp() {
        System.setProperty("allure.results.directory", "target/allure-results");
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        String isCiEnvironment = System.getenv("CI");
        if (isCiEnvironment != null && isCiEnvironment.equals("true")) {
            options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu");
        } else {
            options.addArguments("--start-maximized");
        }

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void beforeMethod() {
        if (isFirstTest) {
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
            performLogin();
            savedCookies = driver.manage().getCookies();
            isFirstTest = false;
        } else {
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
            for (Cookie cookie : savedCookies) {
                driver.manage().addCookie(cookie);
            }
            driver.navigate().refresh();
            
            if (!driver.getCurrentUrl().contains("dashboard")) {
                performLogin();
                savedCookies = driver.manage().getCookies();
            }
        }
    }

    @AfterSuite
    public void suiteTearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void performLogin() {
        if (!driver.getCurrentUrl().contains("login")) {
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        }
        driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
    }
}