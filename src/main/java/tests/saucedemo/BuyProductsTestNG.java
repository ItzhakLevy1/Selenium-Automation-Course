package tests.saucedemo;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.saucedemo.LoginPage;
import pageobjects.saucedemo.ProductPage;
import pageobjects.saucedemo.ProductsPage;
import pageobjects.saucedemo.YourCartPage;
import tests.common.BaseTest;

public class BuyProductsTestNG {

    // 1. Declare driver but don't initialize yet
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        // 2. Initialize driver inside setup
        driver = BaseTest.initDriver();
        driver.get("https://www.saucedemo.com");
    }

    @Test(priority = 1)
    public void tc01_login() throws InterruptedException {
        LoginPage lp = new LoginPage(driver);
        lp.login("standard_user", "secret_sauce");
        Thread.sleep(2000);
    }

    @Test(priority = 2, dependsOnMethods = "tc01_login")
    public void tc02_addBackpack() throws InterruptedException {
        ProductsPage psp = new ProductsPage(driver);
        psp.chooseProduct("Sauce Labs Backpack");

        ProductPage pp = new ProductPage(driver);
        pp.addToCart();
        pp.back();
        Thread.sleep(1000);
    }

    @Test(priority = 3)
    public void tc03_addBikeLight() throws InterruptedException {
        ProductsPage psp = new ProductsPage(driver);
        psp.chooseProduct("Sauce Labs Bike Light");

        ProductPage pp = new ProductPage(driver);
        pp.addToCart();
        pp.back();
        Thread.sleep(1000);
    }

    @Test(priority = 4)
    public void tc04_processCheckout() throws InterruptedException {
        ProductsPage psp = new ProductsPage(driver);
        psp.openCart();

        YourCartPage ycp = new YourCartPage(driver);
        ycp.checkout();
        Thread.sleep(2000);
    }

    @AfterClass
    public void tearDown() {
        // 3. Proper cleanup method name
        if (driver != null) {
            driver.quit();
        }
    }
}