package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.LoginPage;

public class Scenario1Test {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.saucedemo.com/");
        Thread.sleep(3000);

        LoginPage lp = new LoginPage(driver);
        lp.login("standard_user", "secret_sauce");
        System.out.println("Login Successful");
        Thread.sleep(6000);


        driver.quit();
    }
}

