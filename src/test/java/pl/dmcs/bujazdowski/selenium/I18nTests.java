package pl.dmcs.bujazdowski.selenium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class I18nTests extends AbstractSeleniumTest {

    @Test
    public void pageShouldBeInPolishLanguage() {
        WebDriver driver = loginAsAdmin();
        driver.findElement(By.id("langPL")).click();
        String pageTitle = driver.findElement(By.id("pageTitle")).getText();
        Assertions.assertEquals("Twoja Wspólnota Mieszkaniowa", pageTitle);
    }

    @Test
    public void pageShouldBeInEnglishLanguage() {
        WebDriver driver = loginAsAdmin();
        driver.findElement(By.id("langEN")).click();
        String pageTitle = driver.findElement(By.id("pageTitle")).getText();
        Assertions.assertEquals("HOUSING ASSOCIATION", pageTitle);
    }


}
