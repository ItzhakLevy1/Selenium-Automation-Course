package automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MainApp {
    public static void main(String[] args) {
        // 1. Initialize Driver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        try {
            // 2. Navigate to URL
            System.out.println("Navigating to Wix Careers...");
            driver.get("http://www.wix.com/jobs/locations/tel-aviv");

            // 3. Maintenance Check, If the site is under maintenance - stop and close the program
            if (driver.getPageSource().contains("We hope to be back online very soon")) {
                System.out.println("CRITICAL: Maintenance page detected. Exiting now.");
                return; // Ends the execution
            }

            // 4. Initialize the Jobs Page Object
            Jobs jobsPage = new Jobs(driver);

            // 5. Execute Business Logic
            System.out.println("Starting job expansion...");
            jobsPage.expandAllJobs();

            System.out.println("Filtering results...");
            jobsPage.printEngineerJobs();

            System.out.println("Automation finished successfully.");

        } catch (Exception e) {
            // 6. Error Handling: This will tell you EXACTLY why it closed early
            System.err.println("An error occurred during execution:");
            System.err.println("Error Message: " + e.getMessage());
            e.printStackTrace(); // This prints the line number where it failed
        } finally {
            // 7. Cleanup: Keep browser open for 5 seconds to inspect results before closing
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // To debug properly, you can temporarily comment out the next line:
            driver.quit();
            System.out.println("Driver closed.");
        }
    }
}