package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {

    // Passes the WebDriver instance to the parent BasePage constructor
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    // A method that adds a product to the cart
    public void addToCart() {
        click(driver.findElement(By.cssSelector("#add-to-cart")));
    }

    // A method that navigates back to the previous page ( back to the products page )
    public void back() {
        driver.findElement(By.cssSelector("#back-to-products")).click();
    }

}
