package pageobjects.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageobjects.common.BasePage;

/**
 * This class follows the Page Object Model (POM) design pattern.
 * It represents the Login Page of the application and contains its elements and actions.
 */
public class LoginPage extends BasePage {

    // Passes the WebDriver instance to the parent BasePage constructor
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Performs a login sequence by entering credentials and clicking the submit button.
     * @param user The username or email to enter.
     * @param password The password to enter.
     */
    public void login(String user, String password) {

        // Locate the username + password fields, and use the "fillText" method which clears the value and inserts new value instead
        fillText(driver.findElement(By.cssSelector("#user-name")), user);
        fillText(driver.findElement(By.cssSelector("#password")), password);

        // Locate and click the login/submit button to finalize the process
        click(driver.findElement(By.cssSelector("#login-button")));
    }

    public String getErrorMessage() {
        WebElement errorMessage = driver.findElement(By.cssSelector("h3[data-test='error']"));
        return errorMessage.getText();
    }
}