package pl.dmcs.bujazdowski.selenium;

public class SeleniumHelpers {

    public static final String BASE_URL = "http://localhost:8080";
    public static Boolean initiated = false;

    private SeleniumHelpers() {
    }

    static {
        init();
    }

    public static void init() {
        if (!initiated) {
            System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
            initiated = true;
        }
    }

}
