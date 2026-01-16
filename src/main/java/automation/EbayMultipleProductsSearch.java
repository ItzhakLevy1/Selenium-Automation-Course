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
        Thread.sleep(2000);

        /* Search for a product */
        driver.findElement(By.cssSelector("#_nkw")).sendKeys("tent");
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("[data-testid='s0-1-20-6[3]-[2]-LH_BIN']")).click();
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("div.adv-form__actions > button")).click();
        Thread.sleep(5000);

        // In the next page, get a list of all results (there are about 50 results) and store it in a variable named list
        List<WebElement> list = driver.findElements(By.cssSelector(".su-card-container__header > .s-card__link"));

        System.out.println("Number of elements : " + list.size());

        /* Loop through all elements and print each one ( using a foreach loop or a for loop ) */

        for (WebElement el : list) {
            System.out.println(el.getText());
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getText());
        }

        // Click the second element from the list
        list.get(2).click();
        Thread.sleep(5000);

        driver.quit();
    }
}