package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
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
        String textToInsert = "Gemini is the best";

        // Trigger the prompt alert by clicking the specific button
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        Thread.sleep(2000);


        // Switch focus to the alert, type the text, and confirm
        driver.switchTo().alert().sendKeys(textToInsert);
        driver.switchTo().alert().accept();
        Thread.sleep(2000);

        // Comparison strings of the expected result and the actual result
        String expectedResult = "You entered: " + textToInsert;
        String actualResult = resultMessage.getText();

        /*
         * Logical Validation: Compare the actual text retrieved from the UI with the expected string.
         * If they are not identical, the test will immediately fail, stop the current execution,
         * and display the custom error message provided as the third parameter.
         */
        Assert.assertEquals(actualResult, expectedResult, "The result message does not match the entered text!");

        System.out.println("Assertion passed: " + actualResult);
        Thread.sleep(2000);
    }

    // A method that runs last - after all other methods in this class has already ran
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
