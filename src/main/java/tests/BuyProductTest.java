package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.LoginPage;
import pageobjects.ProductPage;
import pageobjects.ProductsPage;
import pageobjects.YourCartPage;

public class BuyProductTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        Thread.sleep(2000);

        // 1. LOG IN
        // Create a LoginPage type object and use its "login" method
        LoginPage lp = new LoginPage(driver);
        lp.login("standard_user", "secret_sauce");
        Thread.sleep(5000);


        // 2. SELECT A PRODUCT ON THE MAIN PRODUCTS PAGE
        // Create a ProductsPage type object and use its "chooseProduct" method
        ProductsPage psp = new ProductsPage(driver);
        psp.chooseProduct("Sauce Labs Backpack");
        Thread.sleep(1000);

        // 3. ADD PRODUCT TO CART ( FROM A SINGLE PRODUCT PAGE )
        // Create a ProductPage type object and use its "addToCart" method + its "back" method
        ProductPage pp = new ProductPage(driver);
        pp.addToCart();
        pp.back();
        Thread.sleep(1000);

        // 4. ARRIVING BACK TO THE PRODUCTS PAGE
        // Re create a ProductsPage type object and use its "chooseProduct" method to select an additional product
        psp = new ProductsPage(driver);
        psp.chooseProduct("Sauce Labs Bike Light");
        Thread.sleep(1000);

        // 5. ARRIVING AT THE SEC0ND PRODUCT'S PAGE AND ADDING IT TO CART
        // Re create a ProductPage type object and use its "addToCart" method + its "back" method
        pp = new ProductPage(driver);
        pp.addToCart();
        Thread.sleep(1000);

        // 6. ARRIVING BACK AT THE MAIN PRODUCTS PAGE AND OPENING THE CART
        // Re create a ProductsPage type object and use its "openCart" method that would take us to the cart page
        psp.openCart();
        Thread.sleep(1000);

        // 7. ARRIVING AT THE CART PAGE
        // Create an YourCartPage object and use its "checkout" method
        YourCartPage ycp = new YourCartPage(driver);
        ycp.checkout();
        Thread.sleep(4000);

        System.out.println("Test passed !");

        driver.quit();
    }

}
