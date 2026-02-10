package pageobjects.common;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

public class BasePage {

    protected WebDriver driver;

    /* * Declaring wait at the class level so all inheriting Page Objects
     * can access it directly without re-initializing.
     */
    protected WebDriverWait wait;

    /**
     * Constructor: Initializes the WebDriver, WebDriverWait, and PageFactory.
     * @param driver The WebDriver instance.
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        /* * Initialize WebDriverWait once here.
         * All pages will now inherit this 10-second timeout configuration.
         */
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    /* * Updated fillText to use Explicit Wait for better stability.
     * It waits for the element to be visible before interacting.
     */
    public void fillText(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));

        // Highlighting the element before interaction
        highlightElement(element, "blue");

        element.clear();
        element.sendKeys(text);
    }

    /* * Updated click to ensure the element is clickable before performing the action.
     */
    public void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));

        // Highlighting the element before clicking
        highlightElement(element, "red");

        element.click();
    }

    public void logout() throws InterruptedException {
        // Find element directly using wait for better reliability
        WebElement menuBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".bm-burger-button")));
        click(menuBtn);

        // Replaced Thread.sleep with visibility check
        WebElement logoutLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#logout_sidebar_link")));
        click(logoutLink);

        System.out.println("Logout performed successfully via BasePage.");
    }

    public void moveMouseToElement(WebElement element) {
        // Ensure the element is visible in the DOM before attempting any interaction
        wait.until(ExpectedConditions.visibilityOf(element));

        // Get the current inline style so we don't overwrite important layout properties
        String originalStyle = element.getAttribute("style");

        // Concatenate the new highlight styles with the original ones
        String highlightedStyle = originalStyle + " border: 5px solid blue; box-shadow: 0px 0px 15px;";

        // Cast driver to JavascriptExecutor to modify the element's visual style directly in the browser
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Apply the combined style string
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, highlightedStyle);

        // Initialize Actions class and perform a mouse move operation to the center of the element
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    /**
     * A smart window switcher that tries to find a window by URL first,
     * and falls back to Title if no URL match is found.
     * @param identifier Part of the URL or Title to search for.
     */
    public void smartSwitchToWindowByUrlOrTitle(String identifier) {

        // Grab all windows, Using Set instead of List since it prevents duplicates, it's faster, and does not require any specified order
        Set<String> allWindows = driver.getWindowHandles();
        // Grab first/main/current window
        String currentHandle = driver.getWindowHandle();

        // Loop through all windows
        for (String handle : allWindows) {
            // On each iteration switch to its currant window
            driver.switchTo().window(handle);
            // On each iteration get the URL of each window
            String currentUrl = driver.getCurrentUrl().toLowerCase();
            // On each iteration get the title of each window
            String currentTitle = driver.getTitle().toLowerCase();
            // On each iteration get the identifier String parameter that the method accepts and convert it to all lower case
            String target = identifier.toLowerCase();

            // On each iteration check if identifier matches URL OR Title
            if (currentUrl.contains(target) || currentTitle.contains(target)) {
                System.out.println("Smart Switch: Found window with identifier [" + identifier + "]");
                return; // Mission accomplished
            }
        }

        // If we reached here, we didn't find anything. Switch back to original window.
        driver.switchTo().window(currentHandle);
        throw new RuntimeException("Smart Switch failed: No window found with URL or Title containing: " + identifier);
    }

    // Open link in a new tab without navigating to it
    public void openLinkInNewTab(WebElement link) {
        Actions actions = new Actions(driver);
        String originalWindow = driver.getWindowHandle();

        // Simulate Ctrl + Click to open link in a new tab
        actions.keyDown(Keys.CONTROL).click(link).keyUp(Keys.CONTROL).build().perform();

        // Depending on browser settings, focus might stay on the original page.
        // To be safe, always explicitly switch back if needed.
        driver.switchTo().window(originalWindow);
    }

    // A method that paints elements as they become active
    private void highlightElement(WebElement element, String color) {
        // Store the current style to revert back to it later
        String originalStyle = element.getAttribute("style");

        // Construct a new style string with the desired border color and background
        String newStyle = "border: 3px solid " + color + "; " +
                          "box-shadow: 0px 0px 15px " + color + "; " +
                          "transform: scale(1.05); " +
                          "transition: all 0.2s ease-in-out; " +
                          originalStyle;

        // Cast the WebDriver instance to JavascriptExecutor to enable running JS code within the browser
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Apply the new highlighted style to the element
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, newStyle);

        try {
            // Pause execution for a moment to make the highlight visible to the eye
            Thread.sleep(400);
            // Execute the script to update the element's style attribute back to the original state
            js.executeScript("arguments[0].setAttribute('style', 'transition: all 0.2s ease-in-out; ' + arguments[1]);", element, originalStyle);
        } catch (InterruptedException e) {
            // Print the error stack trace if the sleep thread is interrupted
            e.printStackTrace();
        }
    }

    /**
     * Captures a manual screenshot with a unique timestamp to prevent file overwriting.
     * @param fileName The base name for the screenshot.
     */
    public void takeManualScreenshot(String fileName) {
        // Cast the driver to TakesScreenshot
        TakesScreenshot ts = (TakesScreenshot) driver;

        // Capture the screenshot as a temporary file
        File srcFile = ts.getScreenshotAs(OutputType.FILE);

        // Create a timestamp string (Format: Year_Month_Day_Hour_Minute_Second)
        String timeStamp = new java.text.SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new java.util.Date());

        // Ensure the destination directory exists
        File directory = new File("./ManualScreenshots/");
        if (!directory.exists()) {
            directory.mkdir();
        }

        try {
            // Construct the final file path including the timestamp
            String finalName = fileName + "_" + timeStamp + ".jpg";
            FileUtils.copyFile(srcFile, new File(directory + "/" + finalName));

            System.out.println("Manual screenshot saved: " + finalName);
        } catch (IOException e) {
            System.err.println("Failed to save manual screenshot: " + e.getMessage());
        }
    }
}