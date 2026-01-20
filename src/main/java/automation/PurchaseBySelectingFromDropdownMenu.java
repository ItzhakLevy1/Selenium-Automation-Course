package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PurchaseBySelectingFromDropdownMenu {
    public  static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.asos.com/");
        Thread.sleep(3000);

        // Search for Nike
        driver.findElement(By.cssSelector("#chrome-search")).sendKeys("Nike");
        Thread.sleep(3000);

        // Select the first result from the dropdown menu (Nike Air Jordan)
        driver.findElement(By.cssSelector("#search-result-0")).click();
        Thread.sleep(3000);

        // On the next page select the first product option
        driver.findElement(By.cssSelector(".productHeroContainer_dVvdX>img")).click();
        Thread.sleep(3000);

        // On the next page click the "ADD TO BAG" button
        driver.findElement(By.cssSelector("[data-testid='add-button']")).click();
        Thread.sleep(3000);

        String failureMessage = driver.findElement(By.cssSelector(".weeij")).getText();
        System.out.println("The failure message due to not selecting shoe size is : " + failureMessage);
        Thread.sleep(3000);

        driver.quit();

    }
}
