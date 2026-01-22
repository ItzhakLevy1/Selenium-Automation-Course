package tests.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    // This method sets up the driver and returns it ready for use
    public static WebDriver initDriver() {
        ChromeOptions options = new ChromeOptions();

        // 1. Suppression flags
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-features=PasswordLeakDetection");

        // 2. Automation environment settings
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);

        // 3. Password manager preferences
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        return driver;
    }
}


/*

Provided benefits :
Incognito Mode: Prevents the site from saving "cookies" between runs, which ensures that the test always starts "clean".

Maximize Window: Saves you from having to write driver.manage().window().maximize() every time.

Automated Info-bar: It hides the line "Chrome is being controlled by automated software", which sometimes moves elements on the site.

 */