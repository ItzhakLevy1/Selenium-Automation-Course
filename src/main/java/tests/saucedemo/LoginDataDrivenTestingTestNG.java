package tests.saucedemo;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.saucedemo.LoginPage;
import tests.common.BaseTest;

public class LoginDataDrivenTestingTestNG {

    WebDriver driver;
    private LoginPage lp;

    @BeforeClass
    public void setup() {
        driver = BaseTest.initDriver();
        driver.get("https://www.saucedemo.com/");

    }

    @Test (description="Test the login failed scenario - check that login failed, and you get the right message")
    public void tc01_loginFail() {
        // Initiate the login page object
        lp = new LoginPage(driver);
        // Call the login methods from the login page - the login should fail
        lp.login("standard_user","123");
        // Actual - should get the error message we get after the login failed
        String actual = lp.getErrorMessage();
        // Expected - the expected message we should get after login failed
        String expected = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertEquals(actual, expected);
    }

    /*
     * In order to use DDT (Data Driven Testing) you should:
     * 1. Create a method to read from with @DataProvider annotation (in this example it's called getData())
     * 2. Add dataProvider="getData" parameter to the @Test
     */
    @Test(dataProvider="getData", description="use incorrect login information")
    public void tc02_loginFailDDT(String user,String password) {

        // Create a new LoginPage instance
        lp = new LoginPage(driver);
        // Using the variables we got from the @DataProvider method
        lp.login(user, password);

        // Should check that we get the right message
        String expected = "Epic sadface: Username and password do not match any user in this service";
        String actual = lp.getErrorMessage();
        Assert.assertEquals(actual, expected);
    }

    /*
     * This is an example of a method that returns a 2-dimensional object (like a table)
     * Using the @DataProvider the method above will get the parameters for each iteration.
     */
    @DataProvider
    public Object[][] getData(){
        Object[][] myData = {
                {"user1","123"},
                {"gal","123"},
                {"yonit","1#444"},
                {"gal","123456878"},
        };
        return myData;
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
