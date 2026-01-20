package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * This class represents the Jobs page and contains methods
 * to interact with and filter job positions.
 */
public class Jobs {

    // Global WebDriver instance to be used within this class
    private WebDriver driver;

    /**
     * Constructor: Initializes the Jobs class with a driver instance passed from the main class.
     * @param driver The WebDriver instance to be used.
     */
    public Jobs(WebDriver driver) {
        this.driver = driver;
    }

    // Standard Getter for the WebDriver
    public WebDriver getDriver() {
        return driver;
    }

    // Standard Setter for the WebDriver
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Finds all job elements, filters them by the provided text, and prints the results.
     * @param text The keyword to search for in the job titles (e.g., "Engineer").
     */
    public void printAllJobs(String text) {
        // Locate all job title elements using a relative XPath from the jobs container
        List<WebElement> list = driver.findElements(By.xpath("//*[@class='comp-lr3n08ai6-container']//p/span"));

        // Output the total number of jobs found
        System.out.println("Total jobs found on page: " + list.size());

        // Iterate through each job element to check for the keyword match
        for (WebElement element : list) {
            String jobTitle = element.getText();

            // Check if the title contains the text (case-insensitive for better reliability)
            if (jobTitle.toLowerCase().contains(text.toLowerCase())) {
                System.out.println("Matching Job Detected: " + jobTitle);
            }
        }
    }
}