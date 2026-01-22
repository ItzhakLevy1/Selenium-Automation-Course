package pageobjects.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageobjects.common.BasePage;

import java.util.List;

/**
 * This class represents the Products page.
 * It contains methods to interact with the list of items available in the store.
 */
public class ProductsPage extends BasePage {

    // Passes the WebDriver instance to the parent BasePage constructor
    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Iterates through a list of products and clicks on the one that matches the given name.
     * @param name The exact name (case-insensitive) of the product to select.
     */
    public void chooseProduct(String name) {
        // Find all product elements using a CSS Selector
        List<WebElement> list = driver.findElements(By.cssSelector(".inventory_item_name"));

        // Loop through the list of found elements
        for (WebElement element : list) {

            // Extract the text from the current element and compare it with the desired name
            // 'equalsIgnoreCase' ensures it matches even if capitalization differs (e.g., "NIKE" vs "Nike")
            if (element.getText().equalsIgnoreCase(name)) {

                // If a match is found, click the element
                click(element);

                // Exit the loop immediately after the click to save time
                break;
            }
        }
    }

    // A method that will open the cart ( probably be used after a product has been added to the cart using addToCart() )
    public void openCart() {
        click(driver.findElement(By.cssSelector("#shopping_cart_container a")));
    }
}