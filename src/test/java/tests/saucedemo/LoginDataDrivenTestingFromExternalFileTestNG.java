package tests.saucedemo;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.ExcelUtils;
import tests.common.BaseTest;
import pageobjects.saucedemo.LoginPage;
import utils.Utils;

public class LoginDataDrivenTestingFromExternalFileTestNG extends BaseTest {

    // This class now uses the 'driver' inherited from BaseTest.
    private LoginPage lp; // Class-level variable

    @BeforeClass
    public void setup() {
        // Assign the driver instance from the BaseTest factory method
        driver = BaseTest.initDriver();
        driver.get(Utils.readProperty("url"));

        // Initialize the Page Object once here instead of inside every test method.
        lp = new LoginPage(driver);
    }

    @DataProvider(name = "loginExcelData")
    public Object[][] getLoginData() {
        // Path to the Excel file in the data package
        String filePath = "src/main/java/data/LoginData.xlsx";
        // Call the utility method to get data from a specific sheet
        return ExcelUtils.getTableArray(filePath, "DataProvider");
    }

    @Test(dataProvider = "loginExcelData", description = "Login test using data from Excel")
    public void tc01_loginWithExcel(String username, String password) {
        driver.get("https://www.saucedemo.com");

        LoginPage lp = new LoginPage(driver);
        lp.login(username, password);
    }
}
