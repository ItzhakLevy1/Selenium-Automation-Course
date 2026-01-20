package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class NavigateAndPurchaseProducts {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(" https://www.saucedemo.com/ ");
        Thread.sleep(5000);

        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("#login-button")).click();
        Thread.sleep(5000);

        driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-bike-light")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("#shopping_cart_container a")).click();
        Thread.sleep(5000);

        driver.findElement(By.cssSelector("#checkout")).click();
        Thread.sleep(1000);

        driver.findElement(By.cssSelector("#first-name")).sendKeys("standard_user1");
        driver.findElement(By.cssSelector("#last-name")).sendKeys("standard_user2");
        driver.findElement(By.cssSelector("#postal-code")).sendKeys("123456");
        driver.findElement(By.cssSelector("#continue")).click();
        Thread.sleep(5000);

        driver.findElement(By.cssSelector("#finish")).click();
        Thread.sleep(3000);



        driver.quit();
    }
}
