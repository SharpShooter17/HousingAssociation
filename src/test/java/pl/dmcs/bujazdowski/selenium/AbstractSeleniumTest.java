package pl.dmcs.bujazdowski.selenium;

import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractSeleniumTest {

    protected static final String BASE_URL = "http://localhost:8080";
    protected static final String LOGIN_PAGE = BASE_URL + "/login.html";
    private static Boolean initiated = false;

    static {
        init();
    }

    public static void init() {
        if (!initiated) {
            System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
            initiated = true;
        }
    }

    @BeforeAll
    public static void setUpSelenium() {
        init();
    }

    public WebDriver loginAsAdmin() {
        WebDriver driver = new FirefoxDriver();
        driver.navigate().to(LOGIN_PAGE);

        driver.findElement(By.id("email"))
                .sendKeys("admin@example.com");

        driver.findElement(By.id("password"))
                .sendKeys("password");

        driver.findElement(By.id("login-submit")).submit();

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlContains("/home"));

        return driver;
    }

}
