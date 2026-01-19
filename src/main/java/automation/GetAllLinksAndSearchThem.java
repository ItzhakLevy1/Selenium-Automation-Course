package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

        // Check if the site is under maintenance or offline
        String pageSource = driver.getPageSource();
        if (pageSource.contains("We hope to be back online very soon")) {
            System.out.println("Site is offline/under maintenance. Closing driver...");
            driver.quit();
            return; // Exit the main method early
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for the container element which contains all job titles to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='comp-lr3n08ai6-container']")));
        System.out.println("The container element has been detected");
        Thread.sleep(2000);

        // Logic: Keep looking for the button inside the loop
        boolean canScrollMore = true;

        while (canScrollMore) {
            try {
                // 1. Scroll to the very bottom of the page first to trigger lazy loading of the button
                ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
                Thread.sleep(1500); // Wait a bit for the button to appear after scrolling

                // 2. Try to find the button
                WebElement loadMoreBtn = driver.findElement(By.cssSelector("[aria-label='Load More Positions']"));

                if (loadMoreBtn.isDisplayed()) {
                    // 3. Scroll specifically to the button to make sure it's "In View"
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", loadMoreBtn);
                    Thread.sleep(1000);

                    System.out.println("Status: Button detected after scroll. Clicking...");
                    loadMoreBtn.click();

                    // Wait for new jobs to load before the next loop iteration
                    Thread.sleep(2500);
                } else {
                    canScrollMore = false;
                }
            } catch (Exception e) {
                // If the button is not found after scrolling, we assume all jobs are loaded
                System.out.println("Status: Could not find 'Load More' button. Reached the end of the list.");
                canScrollMore = false;
            }
        }

        // Get all job links
        List<WebElement> list = driver.findElements(By.xpath("//*[@class='comp-lr3n08ai6-container']/div/div/p/span"));

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
