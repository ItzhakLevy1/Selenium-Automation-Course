package pageobjects.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageobjects.common.BasePage;

public class YourCartPage extends BasePage {

    // Passes the WebDriver instance to the parent BasePage constructor
    public YourCartPage(WebDriver driver) {
        super(driver);
    }

    // A method for checking out
    public void checkout() {
        click(driver.findElement(By.cssSelector(".shopping_cart_link")));
    }

    // A method to complete checkout by clicking the "Checkout" button
    public void midCheckout() {
        click(driver.findElement(By.cssSelector("#checkout")));
    }

    // A method that fills all 3 input fields on the final check out page and clicks the "continue" button
    public void completeCheckout() {
        fillText(driver.findElement(By.cssSelector("#first-name")), "Ron");
        fillText(driver.findElement(By.cssSelector("#last-name")), "Burgundy");
        fillText(driver.findElement(By.cssSelector("#postal-code")), "1234567");
        click(driver.findElement(By.cssSelector("#continue")));
    }

    public void finishCheckout() {
        click(driver.findElement(By.cssSelector("#finish")));
    }
}
