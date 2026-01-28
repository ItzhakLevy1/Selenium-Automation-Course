package pageobjects.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    // The driver instance used to interact with the browser
    public WebDriver driver;

    /**
     * Constructor: Used to initialize the page object with a WebDriver instance
     * passed from the test class.
     * @param driver The WebDriver instance.
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // A method that clears the input field's value and inserts a new value into it
    public void fillText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    // A method that clicks on an element
    public void click(WebElement element) {
        element.click();
    }

    // A Logout method
    public void logout() throws InterruptedException {
        // Open the side menu
        click(driver.findElement(By.cssSelector(".bm-burger-button")));
        // Wait for the animation to finish
        Thread.sleep(2000);
        // Click the logout link
        click(driver.findElement(By.cssSelector("#logout_sidebar_link")));
        System.out.println("Logout performed successfully via BasePage.");
    }

    // A method that simulates a hover by moving the mouse to an element
    public void moveMouseToElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }
}
