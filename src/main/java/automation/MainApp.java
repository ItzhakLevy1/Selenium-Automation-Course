package automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class MainApp {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.wix.com/jobs/locations/tel-aviv");
        Thread.sleep(5000);

        // Create a job instance of the Jobs class
        Jobs jobs = new Jobs(driver);
        jobs.printAllJobs("Engineer");
        Thread.sleep(5000);

        driver.quit();
    }
}