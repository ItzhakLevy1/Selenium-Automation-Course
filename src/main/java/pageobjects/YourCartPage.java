package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class YourCartPage extends BasePage{

    // Passes the WebDriver instance to the parent BasePage constructor
    public YourCartPage(WebDriver driver) {
        super(driver);
    }

    // A method for checking out
    public void checkout() {
        click(driver.findElement(By.cssSelector(".shopping_cart_link")));
    }
}
