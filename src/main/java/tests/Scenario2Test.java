package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.LoginPage;
import pageobjects.ProductPage;
import pageobjects.ProductsPage;
import pageobjects.YourCartPage;

public class Scenario2Test {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.saucedemo.com/");
        Thread.sleep(3000);

        // LOG IN
        LoginPage lp = new LoginPage(driver);
        lp.login("standard_user", "secret_sauce");
        Thread.sleep(3000);

        // SELECT A PRODUCT
        ProductsPage psp1 = new ProductsPage(driver);
        psp1.chooseProduct("Sauce Labs Bike Light");
        Thread.sleep(3000);


        // ADD A PRODUCT TO CART AND NAVIGATE BACK TO THE MAIN PRODUCTS PAGE
        ProductPage pp1 = new ProductPage(driver);
        pp1.addToCart();
        Thread.sleep(1000);
        pp1.back();
        Thread.sleep(3000);

        // SELECT AN ADDITIONAL PRODUCT
        ProductsPage psp2 = new ProductsPage(driver);
        psp2.chooseProduct("Sauce Labs Fleece Jacket");
        Thread.sleep(3000);

        // ADD THE SECOND PRODUCT TO CART AND NAVIGATE BACK TO THE MAIN PRODUCTS PAGE
        ProductPage pp2 = new ProductPage(driver);
        pp2.addToCart();
        Thread.sleep(1000);
        pp2.back();
        Thread.sleep(3000);

        // NAVIGATE TO CART PAGE
        YourCartPage ycp = new YourCartPage(driver);
        ycp.checkout();
        Thread.sleep(3000);


        driver.quit();
    }
}
