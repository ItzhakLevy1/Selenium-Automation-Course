package tests.common;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    // Protected driver allows all test classes that extend BaseTest to use it directly
    protected static WebDriver driver;

    public static WebDriver initDriver() {
        ChromeOptions options = new ChromeOptions();

        // Suppression flags & Automation environment settings
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-features=PasswordLeakDetection");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);

        // Password manager preferences
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        // Assign the new driver to the class-level variable
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        return driver;
    }

    /**
     * This method runs automatically after EACH test method.
     * It captures a screenshot only if the test failed, including a timestamp for uniqueness.
     */
    @AfterMethod
    public void failedTest(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            // Cast driver to TakesScreenshot interface
            TakesScreenshot ts = (TakesScreenshot) driver;

            // Capture the screenshot as a temporary file
            File srcFile = ts.getScreenshotAs(OutputType.FILE);

            // Generate a unique timestamp for the filename
            String timeStamp = new java.text.SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new java.util.Date());

            try {
                // Define the file path using the test name and the generated timestamp
                String fileName = result.getName() + "_" + timeStamp + ".jpg";
                File destFile = new File("./ScreenShots/" + fileName);

                // Copy the temporary file to the permanent ScreenShots folder
                FileUtils.copyFile(srcFile, destFile);

                System.out.println("Screenshot taken for failed test: " + fileName);
            } catch (IOException e) {
                // Log error if the screenshot could not be saved
                System.err.println("Failed to save screenshot: " + e.getMessage());
            }
        }
    }

    /**
     * This method runs after all tests in the current class are finished.
     * It ensures the browser is closed properly to free up system resources.
     */
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed and session ended.");
        }
    }
}

/*

Provided benefits of initDriver() :
Incognito Mode: Prevents the site from saving "cookies" between runs, which ensures that the test always starts "clean".

Maximize Window: Saves you from having to write driver.manage().window().maximize() every time.

Automated Info-bar: It hides the line "Chrome is being controlled by automated software", which sometimes moves elements on the site..

 */