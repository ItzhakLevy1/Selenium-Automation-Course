package tests.theInternetHerokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.common.BaseTest;

public class PromptAlertTestNG extends BaseTest {

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

        // Strings of the actual result
        String actualResult = resultMessage.getText();

        /*
         * Logical Validation: Convert both strings to lowercase to perform a case-insensitive comparison.
         * This ensures the test passes even if the UI text casing changes (e.g., from CSS or backend updates).
         * We use assertTrue with contains() to verify that our input string is part of the final result message.
         * This way even if the site's text message changes the test will still pass since we are mainly looking for the presence of the text entered by the user (textToInsert)
         */
        Assert.assertTrue(actualResult.toLowerCase().contains(textToInsert.toLowerCase()),
                "Expected the result to contain: '" + textToInsert + "' but actual message was: '" + actualResult + "'");

        System.out.println("Assertion passed: " + actualResult);
        Thread.sleep(2000);
    }
}
