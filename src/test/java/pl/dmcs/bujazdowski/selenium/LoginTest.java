package pl.dmcs.bujazdowski.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends AbstractSeleniumTest {

    @Test
    public void shouldLogin() {
        WebDriver driver = loginAsAdmin();

        assertTrue(
                driver.getCurrentUrl().endsWith("home"),
                "User should be redirected to Homepage"
        );
    }

}
