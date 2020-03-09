package pl.dmcs.bujazdowski.selenium;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.util.Assert;

import javax.validation.constraints.AssertTrue;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.dmcs.bujazdowski.selenium.SeleniumHelpers.BASE_URL;

public class LoginTest {

    private static final String LOGIN_PAGE = BASE_URL + "/login.html";

    @BeforeAll
    public void setUpSelenium() {
        SeleniumHelpers.init();
    }

    @Test
    public void login() {
        WebDriver driver = new FirefoxDriver();
        driver.navigate().to(LOGIN_PAGE);

        driver.findElement(By.id("email"))
                .sendKeys("admin@example.com");

        driver.findElement(By.id("password"))
                .sendKeys("password");

        driver.findElement(By.id("login-submit")).submit();

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlContains("/home"));

        assertTrue(
                driver.getCurrentUrl().endsWith("home"),
                "User should be redirected to Homepage"
        );
    }

}
