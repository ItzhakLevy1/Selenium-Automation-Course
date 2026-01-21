package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * This class follows the Page Object Model (POM) design pattern.
 * It represents the Login Page of the application and contains its elements and actions.
 */
public class LoginPage {

    // The driver instance used to interact with the browser
    WebDriver driver;

    /**
     * Constructor: Used to initialize the page object with a WebDriver instance
     * passed from the test class.
     * @param driver The WebDriver instance.
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Performs a login sequence by entering credentials and clicking the submit button.
     * @param user The username or email to enter.
     * @param password The password to enter.
     */
    public void login(String user, String password) {
        // Locate the username field and type the provided username
        driver.findElement(By.cssSelector("#user-name")).clear();
        driver.findElement(By.cssSelector("#user-name")).sendKeys(user);

        // Locate the password field and type the provided password
        driver.findElement(By.cssSelector("#password")).clear();
        driver.findElement(By.cssSelector("#password")).sendKeys(password);

        // Locate and click the login/submit button to finalize the process
        driver.findElement(By.cssSelector("#login-button")).click();
    }
}