package automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class NavigateAndCheckTitle {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Navigate to  https://www.selenium.dev/
        driver.get("https://www.selenium.dev/");

        //Check if the title of this site is equal to “selenium web site” or contains “SeleniumHQ Browser Automation”
        String currentTitle1 = driver.getTitle();

        String titleLower = currentTitle1.toLowerCase();

        System.out.println("Title is : " + currentTitle1);
        Thread.sleep(3000);

        if(titleLower.contains("selenium")) {
            System.out.println("V - Logic passed: 'selenium' was found in title.");
        } else {
            System.out.println("X - Logic failed: 'selenium' not found.");
        }

        Thread.sleep(3000);

        // Navigate to https://www.google.com
        driver.get("https://www.google.com");

        // Check if the title of this site is equal to “Google” or contains “google”
        String currentTitle2 = driver.getTitle();
        String titleLower2 = currentTitle2.toLowerCase();
        System.out.println("Title is : " + currentTitle2);
        Thread.sleep(3000);
        if(titleLower2.contains("google")) {
            System.out.println("V - Logic passed: 'google' was found in title.");
        } else {
            System.out.println("X - Logic failed: 'google' not found.");
        }

        Thread.sleep(3000);

        // Navigate back
        driver.navigate().back();

        Thread.sleep(3000);


        driver.quit();
    }
}
