package pageobjects.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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
}