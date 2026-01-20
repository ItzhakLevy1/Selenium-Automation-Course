package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Jobs {
    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor: Receives the driver from the main class
    public Jobs(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Method to load all jobs by clicking "Load More" repeatedly
    public void expandAllJobs() throws InterruptedException {
        // 1. Wait until the FIRST job title is actually visible (not just the container)
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='comp-lr3n08ai6-container']//p/span")));
            System.out.println("First jobs detected. Starting expansion...");
        } catch (Exception e) {
            System.out.println("No jobs appeared within the timeout.");
            return;
        }

        boolean canScrollMore = true;
        while (canScrollMore) {
            try {
                // Scroll to bottom to trigger Wix dynamic elements
                ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
                Thread.sleep(2000);

                // Looking for the button
                List<WebElement> buttons = driver.findElements(By.cssSelector("[aria-label='Load More Positions']"));

                if (!buttons.isEmpty() && buttons.get(0).isDisplayed()) {
                    WebElement loadMoreBtn = buttons.get(0);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", loadMoreBtn);
                    Thread.sleep(1000);

                    loadMoreBtn.click();
                    System.out.println("Clicked 'Load More'...");
                    Thread.sleep(3000); // Give Wix more time to fetch data
                } else {
                    System.out.println("Load More button no longer visible.");
                    canScrollMore = false;
                }
            } catch (Exception e) {
                System.out.println("Expansion loop stopped: " + e.getMessage());
                canScrollMore = false;
            }
        }
    }

    // Method to filter and print specific jobs
    public void printEngineerJobs() {
        // Collect all job titles after expansion
        List<WebElement> list = driver.findElements(By.xpath("//*[@class='comp-lr3n08ai6-container']//p/span"));

        System.out.println("Total jobs found: " + list.size());
        System.out.println("--- Filtering for 'Engineer' roles ---");

        for (WebElement element : list) {
            String title = element.getText();
            if (title.toLowerCase().contains("engineer")) {
                System.out.println("Detected: " + title);
            }
        }
    }
}