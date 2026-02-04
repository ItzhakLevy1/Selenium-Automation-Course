package tests.theInternetHerokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import tests.common.BaseTest;

public class ConfirmTestNG {

    private WebDriver driver;

    // A setup class (runs prior to all other methods)
    @BeforeClass
    public void setUp() throws InterruptedException {
        driver = BaseTest.initDriver();
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        Thread.sleep(2000);
    }

    // A method to click and trigger the "Confirm" button
    @Test (priority = 1)
    public void tc01_triggerConfirmMessage() throws InterruptedException {
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        System.out.println("The confirmation button that triggers the alert message has been clicked!");
        Thread.sleep(3000);
    }

    // A method to accept and close the confirm modal
    @Test (priority = 2, dependsOnMethods = "tc01_triggerConfirmMessage")
    public void tc02_acceptConfirmMessage() throws InterruptedException {
//        driver.switchTo().alert().accept();
        driver.switchTo().alert().dismiss();
        System.out.println("The 'Cancel' button within the alert message has been clicked!");
        Thread.sleep(3000);
    }

    // A tear down class (runs last - after all other methods in the class ran)
    @AfterClass
    public void teardown() {
        if(driver != null) {
            driver.quit();
        }
    }
}
