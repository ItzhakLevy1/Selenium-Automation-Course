package pageobjects.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.common.BasePage;
import java.util.List;

/**
 * This class represents the Products page.
 * It contains methods to interact with the list of items available in the store.
 */
public class ProductsPage extends BasePage {

    // PageFactory: List of all product containers (cards)
    @FindBy(css = ".inventory_item")
    private List<WebElement> productContainers;

    // PageFactory: List of all product names (titles)
    @FindBy(css = ".inventory_item_name")
    private List<WebElement> productNames;

    // PageFactory: The shopping cart button
    @FindBy(css = "#shopping_cart_container a")
    private WebElement cartButton;

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Iterates through a list of products and clicks on the one that matches the given name.
     * @param name The exact name (case-insensitive) of the product to select.
     */
    public void chooseProduct(String name) {
        // We use the productNames list initialized by PageFactory
        for (WebElement element : productNames) {
            if (element.getText().equalsIgnoreCase(name)) {
                click(element);
                break;
            }
        }
    }

    /**
     * Opens the shopping cart view.
     */
    public void openCart() {
        // Using the WebElement initialized by PageFactory
        click(cartButton);
    }

    /**
     * Locates a product card by name and clicks its 'Add to Cart' button.
     * @param name The name of the product to add.
     */
    public void addProductFromMainProductPage(String name) {
        // Iterate through the containers found by PageFactory
        for (WebElement el : productContainers) {

            // Search for the title WITHIN the current container
            WebElement elTitle = el.findElement(By.cssSelector(".inventory_item_name"));

            if (elTitle.getText().equalsIgnoreCase(name)) {
                // Find and click the 'Add to Cart' button WITHIN this specific container
                WebElement elBuyBtn = el.findElement(By.cssSelector(".btn_inventory"));
                elBuyBtn.click();

                System.out.println("Product found and added: " + name);
                break;
            } else {
                System.out.println("Product '" + elTitle.getText() + "' is not a match.");
            }
        }
    }
}