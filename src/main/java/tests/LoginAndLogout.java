package tests;

import org.openqa.selenium.WebDriver;
import pageobjects.LoginPage;

public class LoginAndLogout {
    public static void main(String[] args) throws InterruptedException {

        /*================= SETUP =================*/
        // Use the BaseTest to initialize our driver with one line
        WebDriver driver = BaseTest.initDriver();

        // Navigate to the site
        driver.get("https://www.saucedemo.com/");
        Thread.sleep(4000);

        /*================= LOGIN =================*/
        // Pass the intuitive 'driver' variable to the Page Object
        LoginPage lp = new LoginPage(driver);
        lp.login("standard_user", "secret_sauce");
        Thread.sleep(4000);

        /*================= LOGOUT =================*/
        lp.logout();
        Thread.sleep(4000);


        /*================= CLEANUP =================*/
        driver.quit();
    }
}