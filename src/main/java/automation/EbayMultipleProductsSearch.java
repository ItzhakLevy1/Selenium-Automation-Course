package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class EbayMultipleProductsSearch {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.ebay.com/sch/ebayadvsearch");
        Thread.sleep(4000);

        // The search button
        WebElement searchBtn = driver.findElement(By.cssSelector("div.adv-form__actions > button"));

        // Check if it's displayed
        boolean searchBtnDisplayed = searchBtn.isDisplayed();

        // Check if it's enabled
        boolean searchBtnEnabled = searchBtn.isEnabled();

        // Get its text
        String textOnSearchBtn = searchBtn.getText();

        // Get its class value
        String searchBtnClassValue = searchBtn.getAttribute("class");

        // Get its tag name
        String searchBtnTagName = searchBtn.getTagName();



        System.out.println("Is the search button displayed ? : " + searchBtnDisplayed);
        System.out.println("Is the search button enabled ? : " + searchBtnEnabled);
        System.out.println("The text on the search button is: " + textOnSearchBtn);
        System.out.println("The class of the search button is: " + searchBtnClassValue);
        System.out.println("The tag name of the search button is: " + searchBtnTagName);
        Thread.sleep(5000);


        driver.quit();
    }
}