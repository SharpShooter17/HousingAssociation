package pl.dmcs.bujazdowski.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BlockTest extends AbstractSeleniumTest {

    @Test
    public void addBlock() {
        WebDriver driver = loginAsAdmin();

        driver.navigate().to(BASE_URL + "/page/block/add");
        driver.findElement(By.id("input-city"))
                .sendKeys("Selenium Test");
        driver.findElement(By.id("input-zip-code"))
                .sendKeys("00-000");
        driver.findElement(By.id("input-street"))
                .sendKeys("SeleniumStreet");
        driver.findElement(By.id("input-number"))
                .sendKeys(String.valueOf(Instant.now().getEpochSecond()));
        driver.findElement(By.id("submit-add-block"))
                .submit();

        String blockListUrl = "/page/block/list";

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlContains(blockListUrl));

        assertTrue(
                driver.getCurrentUrl().endsWith(blockListUrl),
                "User should be redirected to block list"
        );
    }

}
