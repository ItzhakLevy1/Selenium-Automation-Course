package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobjects.LoginPage;

import java.util.HashMap;
import java.util.Map;

public class LoginAndLogout {
    public static void main(String[] args) throws InterruptedException {

        /*================= DRIVER =================*/
        // 1. Initialize Chrome options
        ChromeOptions options = new ChromeOptions();

        // 2. Use Incognito mode - this is the most effective way to bypass password popups
        options.addArguments("--incognito");

        // 3. Additional flags to suppress UI bubbles and popups
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-save-password-bubble");

        // 4. Force automation mode features
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);

        // 5. Apply preferences as a secondary layer
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        // 6. Launch the driver with the defined options
        WebDriver driver = new ChromeDriver(options);
        // Maximize the Chrome window
        driver.manage().window().maximize();
        // Navigate to the URL
        driver.get("https://www.saucedemo.com/");
        Thread.sleep(4000);


        /*================= LOGIN =================*/
        // Create a login class instance
        LoginPage lp = new LoginPage(driver);
        lp.login("standard_user", "secret_sauce");
        Thread.sleep(6000);



        driver.quit();
    }
}
