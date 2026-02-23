package tests.saucedemo;

import org.openqa.selenium.WebDriver;
import pageobjects.saucedemo.LoginPage;
import pageobjects.saucedemo.ProductsPage;
import tests.common.BaseTest;

public class BuyProductFromMainPageTest {
    public static void main(String[] args) throws InterruptedException {

        // Set up
        WebDriver driver = BaseTest.initDriver();

        // Navigate to site
        driver.get("https://www.saucedemo.com/inventory.html");
        Thread.sleep(2000);

        // Log in
        LoginPage lp = new LoginPage(driver);
        lp.login("standard_user", "secret_sauce");
        Thread.sleep(2000);

        // Purchase product
        ProductsPage apfmpp = new ProductsPage(driver);
        apfmpp.addProductFromMainProductPage("Sauce Labs Backpack");
        Thread.sleep(4000);

        // Clean up
        driver.quit();
    }
}
