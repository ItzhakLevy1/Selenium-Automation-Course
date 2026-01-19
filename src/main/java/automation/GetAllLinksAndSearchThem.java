package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class GetAllLinksAndSearchThem {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("http://www.wix.com/jobs/locations/tel-aviv");
        Thread.sleep(8000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Get all job links
        List<WebElement> list = driver.findElements(By.xpath("//*[@class='comp-lr3n08ai6-container']/div/div/p/span"));

        // Wait for the container element which contains all job titles to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='comp-lr3n08ai6-container']")));
        System.out.println("The container element has been detected");
        Thread.sleep(2000);

        System.out.println("Number of links is : " + list.size());

        for (WebElement element : list) {
            System.out.println(element.getText());
        }

        Thread.sleep(2000);
        System.out.println("Searching for titles which include the word 'Engineer'...");
        Thread.sleep(2000);

        for (WebElement element : list) {
            if(element.getText().contains("Engineer")){
                System.out.println("A title that includes 'Engineer' has been detected: " + element.getText());
            }
        }
        Thread.sleep(4000);

        driver.quit();
    }
}
