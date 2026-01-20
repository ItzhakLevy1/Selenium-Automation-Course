package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class FilteredEbaySearch {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.ebay.com/sch/ebayadvsearch");
        Thread.sleep(2000);

        // Search the "Enter keywords or item number" input field
        driver.findElement(By.cssSelector("#_nkw")).sendKeys("guitar");
        Thread.sleep(2000);

        // Click the "In this category" drop down menu
        driver.findElement(By.cssSelector("[aria-label='In this category']")).click();
        Thread.sleep(2000);

        // Grab the dropdown menu
        Select selectCategory = new Select(driver.findElement(By.cssSelector("[aria-label='In this category']")));
        Thread.sleep(2000);

        // Select from the dropdown menu
        selectCategory.selectByVisibleText("Musical Instruments & Gear");
        Thread.sleep(1000);

        // Change selection using a different selection method
        selectCategory.selectByIndex(23);
        Thread.sleep(1000);

        // Change selection using a different selection method
        selectCategory.selectByValue("619");
        Thread.sleep(2000);

        // Insert value into the "Min price" input field
        driver.findElement(By.cssSelector("[name='_udlo']")).sendKeys("50");
        Thread.sleep(1000);

        // Insert value into the "Min price" input field
        driver.findElement(By.cssSelector("[name='_udhi']")).sendKeys("60");
        Thread.sleep(2000);


        // Check the "Auction" check box
        driver.findElement(By.cssSelector("[value='LH_Auction']")).click();
        Thread.sleep(2000);

        // Press the Search button on the bottom of the page
        driver.findElement(By.cssSelector(".adv-form__actions>button")).click();
        Thread.sleep(5000);

        driver.quit();
    }
}
