package pl.dmcs.bujazdowski.selenium;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class UserTests extends AbstractSeleniumTest {

    private static WebDriver driver;

    @BeforeAll
    public static void init() {
        driver = loginAsAdmin();
    }

    @Test
    public void userShouldBeRegistered() {
        driver.findElement(By.id("menu")).click();

        driver.findElement(By.id("menu-register")).click();

        driver.findElement(By.id("firstNameInput"))
                .sendKeys("SeleniumTestName");
        driver.findElement(By.id("lastNameInput"))
                .sendKeys("SeleniumTestLastname");
        driver.findElement(By.id("emailNameInput"))
                .sendKeys("selenium@selenium.pl");
        driver.findElement(By.id("telephoneNameInput"))
                .sendKeys("+00123123123");

        driver.findElement(By.id("registerUserButton")).submit();

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlContains("/page/user/list"));

        boolean isFound = driver.findElements(By.xpath("//table[@id='userListTable']/tbody/tr/td/a[contains(text(),'selenium@selenium.pl')]")).size() != 0;
        Assertions.assertTrue(isFound, "Element should be found in table");

        deleteUserAfterTests();
    }

    @Test
    public void userShouldBeRemoved() throws InterruptedException {
        createUserForTests();
        driver.findElement(By.id("menu")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("menu-user-list")).click();

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlContains("/page/user/list"));

        WebElement element = driver.findElement(By.xpath("//table[@id='userListTable']/tbody/tr/td/a[contains(text(),'selenium@selenium.pl')]/../following-sibling::td[5]"));

        element.click();

        boolean isFound = driver.findElements(By.xpath("//table[@id='userListTable']/tbody/tr/td/a[contains(text(),'selenium@selenium.pl')]")).size() == 0;

        Assertions.assertTrue(isFound, "Element should not be found in table");
    }

    @Nested
    class TestWhichRequireCreatedUser {

        @BeforeEach
        public void init() {
            createUserForTests();
        }


        @AfterEach
        public void clear() {
            deleteUserAfterTests();
        }

        @Test
        public void userShouldBeEdited() throws InterruptedException {
            driver.findElement(By.id("menu")).click();
            driver.findElement(By.id("menu-user-list")).click();

            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.urlContains("/page/user/list"));

            WebElement element = driver.findElement(By.xpath("//table[@id='userListTable']/tbody/tr/td/a[contains(text(),'selenium@selenium.pl')]/../following-sibling::td[4]"));
            element.click();
            wait.until(ExpectedConditions.urlContains("/page/user/edit"));

            WebElement firstNameInput = driver.findElement(By.id("firstNameInput"));
            firstNameInput.clear();
            firstNameInput.sendKeys("SeleniumTestNameEdited");

            WebElement lastNameInput = driver.findElement(By.id("lastNameInput"));
            lastNameInput.clear();
            lastNameInput.sendKeys("SeleniumTestLastnameEdited");

            driver.findElement(By.id("editUserButton")).submit();
        }

        @Test
        public void userEditionShouldNotBePermitted() throws InterruptedException {
            driver.findElement(By.id("menu")).click();

            driver.findElement(By.id("menu-user-list")).click();
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.urlContains("/page/user/list"));

            WebElement element = driver.findElement(By.xpath("//table[@id='userListTable']/tbody/tr/td/a[contains(text(),'selenium@selenium.pl')]/../following-sibling::td[4]"));

            element.click();

            wait.until(ExpectedConditions.urlContains("/page/user/edit"));

            WebElement firstNameInput = driver.findElement(By.id("firstNameInput"));
            firstNameInput.clear();

            WebElement lastNameInput = driver.findElement(By.id("lastNameInput"));
            lastNameInput.clear();

            driver.findElement(By.id("editUserButton")).submit();

            Thread.sleep(2000);

            String firstNameError = driver.findElement(By.id("firstName.errors")).getText();
            String lastNameError = driver.findElement(By.id("lastName.errors")).getText();

            Assertions.assertEquals(firstNameError, "Field is required");
            Assertions.assertEquals(lastNameError, "Field is required");

        }

    }

    private void createUserForTests() {
        driver.navigate().to(BASE_URL + "/page/user/register");
        driver.findElement(By.id("firstNameInput"))
                .sendKeys("SeleniumTestName");
        driver.findElement(By.id("lastNameInput"))
                .sendKeys("SeleniumTestLastname");
        driver.findElement(By.id("emailNameInput"))
                .sendKeys("selenium@selenium.pl");
        driver.findElement(By.id("telephoneNameInput"))
                .sendKeys("+00123123123");

        driver.findElement(By.id("registerUserButton")).submit();
        driver.navigate().to(BASE_URL + "/page/home");
    }

    private void deleteUserAfterTests() {
        driver.navigate().to(BASE_URL + "/page/user/list");
        WebElement element = driver.findElement(By.xpath("//table[@id='userListTable']/tbody/tr/td/a[contains(text(),'selenium@selenium.pl')]/../following-sibling::td[5]"));
        element.click();
    }
}
