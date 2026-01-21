package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {

    // Global WebDriver instance to be used within this class
    WebDriver driver;

    /**
     * Constructor: Initializes the page object with a driver instance from the test.
     * @param driver The WebDriver instance passed from the Main/Test class.
     */
    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    // A method that adds a product to the cart
    public void addToCart() {
        driver.findElement(By.cssSelector("")).click();
    }

    // A method that navigates back to the previous page ( back to the products page )
    public void back() {
        driver.findElement(By.cssSelector("")).click();
    }

}
