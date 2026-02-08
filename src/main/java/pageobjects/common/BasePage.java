package pageobjects.common;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        element.clear();
        element.sendKeys(text);
    }

    /* * Updated click to ensure the element is clickable before performing the action.
     */
    public void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
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
        wait.until(ExpectedConditions.visibilityOf(element));
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
}