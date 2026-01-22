package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogoutPage extends BasePage{

    public LogoutPage(WebDriver driver) {
        super(driver);
    }

    public void logout() throws InterruptedException {
        click(driver.findElement(By.cssSelector(".bm-burger-button")));
        Thread.sleep(2000);
        click(driver.findElement(By.cssSelector("#logout_sidebar_link")));
        System.out.println("Logout completed.");
    }

}
