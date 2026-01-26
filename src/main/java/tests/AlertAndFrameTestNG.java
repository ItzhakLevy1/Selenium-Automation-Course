package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.common.BaseTest;

public class AlertAndFrameTestNG {

    private WebDriver driver;

    @BeforeClass
    public void setup() throws InterruptedException {
        driver = BaseTest.initDriver();
        driver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_alert");
        Thread.sleep(2000);
    }

    @Test(priority = 1)
    public void clickBtnToTriggerAlert() throws InterruptedException {
        // 1. Switch to the iframe where the button actually exists
        driver.switchTo().frame("iframeResult");

        // 2. Locate the button inside the frame (using tag name as it's the only button there)
        WebElement alertBtn = driver.findElement(By.tagName("button"));
        alertBtn.click();
        Thread.sleep(2000);
    }

    @Test(priority = 2, dependsOnMethods = "clickBtnToTriggerAlert")
    public void alertConfirm() throws InterruptedException {
        // 3. Switch to alert and accept it
        driver.switchTo().alert().accept();
        Thread.sleep(2000);

        // 4. (Optional) Switch back to the main content if needed for next tests
        driver.switchTo().defaultContent();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}