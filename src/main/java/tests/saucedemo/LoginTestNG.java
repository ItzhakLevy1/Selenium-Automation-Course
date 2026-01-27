package tests.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.saucedemo.LoginPage;
import tests.common.BaseTest;

public class LoginTestNG {

    WebDriver driver;

    @BeforeClass
    public void beforeClass() throws InterruptedException {
        driver = BaseTest.initDriver();
        driver.get("https://www.saucedemo.com/");
        Thread.sleep(2000);
    }

    // A method that gets the error message that appears when trying to log in without Username and Password
    @Test
    public void tc01_getMessagePriorToInsertingUsernameAndPasswordLoginTestNG() throws InterruptedException {
        // Click the Login button without inserting Username & Password
        WebElement loginButton = driver.findElement(By.cssSelector("#login-button"));
        loginButton.click();
        Thread.sleep(1000);

        WebElement errorMessage = driver.findElement(By.cssSelector("h3[data-test='error']"));

        String expectedMessage = "Epic sadface: Username is required";
        String actualMessage = errorMessage.getText();

        System.out.println("The actual message ( prior to inserting a Username or Password ) is = \"" + actualMessage + "\"");
        Thread.sleep(1000);

        Assert.assertEquals(actualMessage, expectedMessage, "The error message text is incorrect!");


    }

    // A method that gets the error message that appears when trying to log in without a Password
    @Test
    public void tc02_loginWithMissingPassword() throws InterruptedException {
        LoginPage lp = new LoginPage(driver);
        lp.login("standard_user", "");
        Thread.sleep(1000);

        WebElement errorMessage = driver.findElement(By.cssSelector("h3[data-test='error']"));

        String expected = "Epic sadface: Password is required";
        String actual = errorMessage.getText();

        System.out.println("The actual message ( after entering only a Username ) is = " + actual);
        Thread.sleep(2000);

        /*
         * Logical Validation: Verify that the error message displayed matches the requirement.
         * The assertion ensures that our validation logic is correct and the user is notified.
         * The 3rd argument - the text, will be displayed in the terminal in case of a miss match.
         */
        Assert.assertEquals(actual, expected, "The error message text is incorrect!");
    }

    @AfterClass
    public void afterClass() {
        if (driver != null) {
            driver.quit();
        }
    }
}