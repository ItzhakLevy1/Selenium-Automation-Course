package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * This class represents the Products page.
 * It contains methods to interact with the list of items available in the store.
 */
public class ProductsPage {

    // Global WebDriver instance to be used within this class
    WebDriver driver;

    /**
     * Constructor: Initializes the page object with a driver instance from the test.
     * @param driver The WebDriver instance passed from the Main/Test class.
     */
    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Iterates through a list of products and clicks on the one that matches the given name.
     * @param name The exact name (case-insensitive) of the product to select.
     */
    public void chooseProduct(String name) {
        // Find all product elements using a CSS Selector
        List<WebElement> list = driver.findElements(By.cssSelector(""));

        // Loop through the list of found elements
        for (WebElement element : list) {

            // Extract the text from the current element and compare it with the desired name
            // 'equalsIgnoreCase' ensures it matches even if capitalization differs (e.g., "NIKE" vs "Nike")
            if (element.getText().equalsIgnoreCase(name)) {

                // If a match is found, click the element
                element.click();

                // Exit the loop immediately after the click to save time
                break;
            }
        }
    }
}