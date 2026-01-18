package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class SelectFromDropDownMenu {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // First page
        driver.get("https://automation.co.il/tutorials/selenium/demo1/indexID.html");
        Thread.sleep(2000);

        WebElement firstName = driver.findElement(By.cssSelector("#firstname"));
        firstName.sendKeys("Itzhak");

        WebElement lastName = driver.findElement(By.cssSelector("#lastname"));
        lastName.sendKeys("Levy");

        WebElement email = driver.findElement(By.cssSelector("#email"));
        email.sendKeys("itzhak@gmail.com");

        WebElement nextBtn = driver.findElement(By.cssSelector("#next"));
        nextBtn.click();
        Thread.sleep(4000);

        // Second page
        WebElement beginnerIcon = driver.findElement(By.cssSelector("#Beginner"));
        beginnerIcon.click();
        Thread.sleep(2000);

        WebElement nextBtn2 = driver.findElement(By.cssSelector("#next"));
        nextBtn2.click();
        Thread.sleep(4000);

        WebElement dropDownMenu = driver.findElement(By.cssSelector("#country"));
        dropDownMenu.click();
        Thread.sleep(2000);

        Select selectCountry = new Select(driver.findElement(By.cssSelector("#country")));

        // 1 way - selectByVisibleText
        selectCountry.selectByVisibleText("Albania");
        Thread.sleep(2000);

        WebElement finishBtn = driver.findElement(By.cssSelector("#finish"));
        finishBtn.click();
        Thread.sleep(2000);

        // 2 way - selectByIndex
        selectCountry.selectByIndex(3);
        Thread.sleep(2000);
        finishBtn.click();
        Thread.sleep(2000);

        finishBtn.click();
        Thread.sleep(2000);
        finishBtn.click();
        Thread.sleep(2000);

        // 3 way - selectByValue
        selectCountry.selectByValue("Argentina");
        Thread.sleep(2000);
        finishBtn.click();
        Thread.sleep(2000);



        driver.quit();
    }
}
