package tests.saucedemo;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.saucedemo.LoginPage;
import tests.common.BaseTest;

public class LoginDataDrivenTestingTestNG {

    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = BaseTest.initDriver();
        driver.get("https://www.saucedemo.com/");

    }

    @Test (description="Test the login failed scenario - check that login failed, and you get the right message")
    public void LoginFailed() {
        //Initiate the login page object
        LoginPage loginPage = new LoginPage(driver);
        //call the login methods from the login page - the login should fail
        loginPage.login("standard_user","123");
        //actual - should get the error message we get after the login failed
        String actual = loginPage.getErrorMessage();
        //expected - the expected message we should get after login failed
        String expected = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertEquals(actual, expected);
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
