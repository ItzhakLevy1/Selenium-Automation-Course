package tests.theInternetHerokuapp;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.common.BaseTest;

public class AlertTestNG extends BaseTest {

    @BeforeClass
    public void setup() {
        driver = BaseTest.initDriver();
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
    }

    @Test(priority = 1)
    public void clickBtnToTriggerAlert() throws InterruptedException {
        // Find the first button that triggers a simple JS Alert
        // The XPath targets a button that contains the text "Click for JS Alert"
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        Thread.sleep(2000);
    }

    @Test(priority = 2, dependsOnMethods = "clickBtnToTriggerAlert")
    public void alertConfirm() throws InterruptedException {
        // Switch to the alert and click the OK button
        driver.switchTo().alert().accept();

        // Validation: Check if the result text confirms the alert was accepted
        String result = driver.findElement(By.id("result")).getText();
        System.out.println("Browser says: " + result);

        Thread.sleep(2000);
    }
}