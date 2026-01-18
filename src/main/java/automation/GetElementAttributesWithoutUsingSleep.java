package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GetElementAttributesWithoutUsingSleep {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // 1. Create a wait object - wait a maximum of 10 seconds
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("http://www.ebay.com/sch/ebayadvsearch");

        // 2. Instead of Thread.sleep, wait until the element exists in the DOM and is displayed
        // The wait.until operation returns the WebElement as soon as it is found
        WebElement searchBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.adv-form__actions > button")));

        // Now you can work with the element safely
        if(searchBtn != null) {
            System.out.println("Button found!");
        } else {
            System.out.println("Button not found...");
        }

        driver.quit();
    }
}