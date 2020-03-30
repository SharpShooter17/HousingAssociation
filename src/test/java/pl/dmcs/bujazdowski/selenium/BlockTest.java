package pl.dmcs.bujazdowski.selenium;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BlockTest extends AbstractSeleniumTest {

    private static final String ZIP_CODE = "00-000";

    private static final String STREET = "SeleniumStreet";

    private static final String STREET_NUMBER = String.valueOf(Instant.now().getEpochSecond());

    private static final String BLOCK_NAME = ZIP_CODE + " " + STREET + " " + STREET_NUMBER;

    private static final String APARTMENT_NAME = BLOCK_NAME + "/1";

    private static final String OCCUPANT_EMAIL = "admin@example.com";

    private static final WebDriver driver = loginAsAdmin();

    @Test
    @Order(0)
    public void addBlock() {
        driver.navigate().to(BASE_URL + "/page/block/add");
        driver.findElement(By.id("input-city")).sendKeys("Selenium Test");
        driver.findElement(By.id("input-zip-code")).sendKeys(ZIP_CODE);
        driver.findElement(By.id("input-street")).sendKeys(STREET);
        driver.findElement(By.id("input-number")).sendKeys(STREET_NUMBER);
        driver.findElement(By.id("submit-add-block")).submit();

        String blockListUrl = "/page/block/list";

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.urlContains(blockListUrl));

        assertTrue(
                driver.getCurrentUrl().endsWith(blockListUrl),
                "User should be redirected to block list"
        );
    }

    @Test
    @Order(10)
    public void addApartment() {
        driver.findElement(By.xpath("//*[text()='" + BLOCK_NAME + "']")).click();
        driver.findElement(By.id("add-apartment")).click();
        driver.findElement(By.id("submit-apartment")).submit();

        By apartmentName = By.xpath("//*[contains(text(),'" + APARTMENT_NAME + "')]");

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(apartmentName));

        assertTrue(
                driver.findElement(apartmentName).isDisplayed(),
                "User should be redirected to block details page with apartment list");
    }

    @Test
    @Order(20)
    public void addOccupants() {
        driver.findElement(By.xpath("//*[contains(text(),'" + APARTMENT_NAME + "')]")).click();

        By addOccupant = By.id("add-occupant");
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(addOccupant));
        driver.findElement(addOccupant).click();

        By occupantEmailElement = By.xpath("//*[contains(text(),'" + OCCUPANT_EMAIL + "')]");
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(occupantEmailElement));
        driver.findElement(By.xpath("//form//*[contains(text(), '" + OCCUPANT_EMAIL + "')]")).click();
        driver.findElement(By.id("save-occupants")).click();

        occupantEmailElement = By.xpath("//table//*[contains(text(), '" + OCCUPANT_EMAIL + "')]");
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(occupantEmailElement));

        assertTrue(
                driver.findElement(occupantEmailElement).isDisplayed(),
                "User should be redirected to apartment details page with occupants list");
    }

    @Test
    @Order(30)
    public void addBill() {
        By addBill = By.id("add-bill");
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(addBill));
        driver.findElement(addBill).click();

        By saveBill = By.id("save-bill");
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(saveBill));
        driver.findElement(saveBill).click();

        By billElement = By.xpath("//*[text()='ELECTRICITY']");
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(billElement));

        assertTrue(
                driver.findElement(billElement).isDisplayed(),
                "User should be redirected to apartment details page with bill list"
        );

    }

}
