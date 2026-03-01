package tests.saucedemo;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestContext; // Added for context sharing
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.saucedemo.LoginPage;
import tests.common.BaseTest;
import utils.AllureAttachment;
import utils.Utils;

@Owner("Automation Team") // This owner will be applied to all tests within this class ( and will be shown on the allure report )
public class LoginDataDrivenTestingTestNG extends BaseTest {

    // This class now uses the 'driver' inherited from BaseTest.
    private LoginPage lp; // Class-level variable

    @BeforeClass
    // This setup method runs before the test execution begins.
    // ITestContext provides runtime information about the current TestNG execution,
    // such as test name, included groups, parameters, and shared test attributes.
    // It is commonly used to initialize resources (e.g., WebDriver, configuration,
    // test data, reporting context) that should be available for all tests.
    public void setup(ITestContext testContext) {
        // Initialize the driver
        driver = BaseTest.initDriver();

        // CRITICAL: Share the driver instance with the TestNG Context so the Listener can find it
        testContext.setAttribute("WebDriver", driver);

        driver.get(Utils.readProperty("url"));

        // Initialize the Page Object once here instead of inside every test method.
        lp = new LoginPage(driver);
    }

    @Owner("Itzhak Levy") // Defines the person responsible for this specific test case, this overrides the class-level owner for this specific test
    @Test(priority = 1, description = "Test the login failed scenario with hardcoded data")
    public void tc01_loginFail() {

        // Call the login method from the login page
        lp.login("standard_user", "123");

        // Actual - the error message we get after the login fails
        String actual = lp.getErrorMessage();

        // Expected - the expected message we should get after login fails
        String expected = "Epic sadface: Username and password do not match any user in this service";

        // Comparing both messages to varify the outcome
        Assert.assertEquals(actual, expected);
    }

    /*
     * In order to use DDT (Data Driven Testing) you should:
     * 1. Create a method to read from with @DataProvider annotation (in this example it's called getData())
     * 2. Add dataProvider="getData" parameter to the @Test
     */
    @Test(priority = 2, dataProvider = "getData", description = "DDT login failure with various incorrect credentials")
    public void tc02_loginFailDDT(String user, String password) {

        // Refreshing the page before each iteration to clear old error messages and states.
        driver.navigate().refresh();

        lp.login(user, password);

        // Check that we got the right message
        String expected = "Epic sadface: Username and password do not match any user in this service";
        String actual = lp.getErrorMessage();

        // Forcing a failure on the first iteration to trigger the screenshot in Allure
        if (user.equals("user1")) {
            Assert.assertEquals(actual, "FORCED FAILURE TO TEST SCREENSHOT", "Testing Allure Attachment!");
        } else {
            Assert.assertEquals(actual, expected);
        }
    }

    /*
     * This is an example of a method that returns a 2-dimensional object (like a table)
     * Using the @DataProvider the method above will get the parameters for each iteration.
     */
    @DataProvider
    public Object[][] getData() {
        return new Object[][]{
                {"user1", "123"},
                {"gal", "123"},
                {"yonit", "1#444"},
                {"gal", "123456878"}
        };
    }

    @Test(priority = 3, description = "Login using credentials from an external properties file")
    public void tc03_loginFromProperties() {

        // Fetching the data using the keys defined in the properties file
        String userFromProp = Utils.readProperty("username");
        String passFromProp = Utils.readProperty("password");

        // Refreshing the page before each iteration to clear old error messages and states.
        driver.navigate().refresh();
        lp.login(userFromProp, passFromProp);

        if (driver.getCurrentUrl().contains("inventory.html")) {
            lp.takeManualScreenshot("tc03_Successful_Login_Dashboard");
        }

        // Verification - since we used valid credentials, we expect to see the 'Products' title after a successful login
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("inventory.html"), "Login failed using properties file credentials!");
    }

    @Test(description = "Verify logo element visual appearance")
    @Description("Captured a specific screenshot of the login logo element to ensure it renders correctly.")
    public void tc04_checkLogoScreenshot() {
        // Find a specific element, for example the logo
        WebElement logo = driver.findElement(By.cssSelector(".login_logo"));

        // Attach only the logo's image to the Allure report
        AllureAttachment.attachElementScreenshot(logo);

        Assert.assertTrue(logo.isDisplayed());
    }

    @Test(description = "Validate JSON API response formatting")
    @Description("Simulates an API call by attaching a raw JSON string and verifying its formatted output in Allure.")
    public void tc05_testApiResponse() {
        // Sample raw JSON response used to simulate an API response payload
        String rawJson = "{\"status\":\"success\",\"user\":\"admin\",\"id\":101}";

        // This will show up in Allure as a nicely formatted .json file
        AllureAttachment.attachJson(rawJson);
    }

    @Test(description = "Validate XML payload formatting")
    @Description("Tests the XML attachment utility by formatting a raw XML string into a readable structure.")
    public void tc06_testXmlAttachment() {
        String xmlPayload = "<note>\n" +
                                "<to>Tove</to>\n" +
                                "<from>Jani</from>\n" +
                                "<heading>Reminder</heading>\n" +
                                "<body>Don't forget me this weekend!</body>\n" +
                                "</note>";

        // This will format the XML and attach it to the Allure report
        AllureAttachment.attachXml(xmlPayload);
    }

    @Test(description = "Validate CSV data attachment")
    @Description("Demonstrates how to attach comma-separated values (CSV) to the report for data-driven testing visibility.")
    public void tc07_testCsvAttachment() {
        String csvData = "John,Doe,120 jefferson st.,Riverside, NJ, 08075\n" +
                "Jack,McGinnis,220 hobo Av.,Phila, PA,09119\n" +
                "\"John \"\"Da Man\"\"\",Repici,120 Jefferson St.,Riverside, NJ,08075\n" +
                "Stephen,Tyler,\"7452 Terrace \"\"At the Plaza\"\" road\",SomeTown,SD, 91234\n" +
                ",Blankman,,SomeTown, SD, 00298\n" +
                "\"Joan \"\"the bone\"\", Anne\",Jet,\"9th, at Terrace plc\",Desert City,CO,00123";

        // Attach the CSV string to the report
        AllureAttachment.attachCSV(csvData);
    }

    @Test(description = "Verify external link attachment")
    @Description("Attaches a specific URL to the report to provide a direct link to the tested environment or documentation.")
    public void tc08_testUrlAttachment() {
        String website = "https://www.google.com/";

        // Attach the URL to the Allure report
        AllureAttachment.attachURL(website);

        Assert.assertNotNull(website);
    }
}