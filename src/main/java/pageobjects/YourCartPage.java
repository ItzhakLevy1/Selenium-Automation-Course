package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class YourCartPage {

    // Global WebDriver instance to be used within this class
    WebDriver driver;

    /**
     * Constructor: Initializes the page object with a driver instance from the test.
     * @param driver The WebDriver instance passed from the Main/Test class.
     */
    public YourCartPage(WebDriver driver) {
        this.driver = driver;
    }

    // A method for checking out
    public void checkout() {
        driver.findElement(By.cssSelector("#checkout")).click();
    }
}
