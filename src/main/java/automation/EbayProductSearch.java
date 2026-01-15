package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class EbayProductSearch {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.ebay.com/sch/ebayadvsearch ");
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("#_nkw")).sendKeys("tent");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("#_ex_kw")).sendKeys("blue");
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("[data-testid='s0-1-20-6[3]-[2]-LH_BIN']")).click();
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("div.adv-form__actions > button")).click();
        Thread.sleep(5000);

        driver.navigate().back();
        Thread.sleep(5000);

        driver.quit();
    }
}


/*
Exercise 3:
Should be implemented with Chrome
Open http://www.ebay.com/sch/ebayadvsearch
Add ‘tent’ to the ‘Enter keywords or item number’ text box
Add some words to the ‘Exclude words from your search’ text box
Checked the ‘Buy It Now’ check box
Press the Search button on the bottom of the page
Navigate back
Press on the search button on the top of the page (*can you do it?)

*/
