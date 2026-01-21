package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {

    // The driver instance used to interact with the browser
    WebDriver driver;

    /**
     * Constructor: Used to initialize the page object with a WebDriver instance
     * passed from the test class.
     * @param driver The WebDriver instance.
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
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
}
