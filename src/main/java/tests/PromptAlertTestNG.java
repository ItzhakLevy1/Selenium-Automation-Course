package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.common.BaseTest;

public class PromptAlertTestNG {

    // Initialize a driver
    WebDriver driver;
    WebElement resultMessage;

    // A method that runs prior to any other method in this class
    @BeforeClass
    public void setUp() throws InterruptedException {
        driver = BaseTest.initDriver();
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        Thread.sleep(2000);
    }

    // A method that triggers the "Prompt" alert and inserts text into its input field
    @Test(priority = 1)
    public void tc01_promptTriggeringAndHandling() throws InterruptedException {

        resultMessage = driver.findElement(By.cssSelector("#result"));

        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        // The inserted text will not be visible, the indication of it being inserted is the confirmation message
        driver.switchTo().alert().sendKeys("Random text to be inserted into the prompt alert");
        driver.switchTo().alert().accept();
        System.out.println("Text inserted into the prompt alert and confirmation button has been pressed");
        System.out.println("Result message is: " + resultMessage.getText());
        Thread.sleep(4000);
    }

    // A method that runs last - after all other methods in this class has already ran
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
