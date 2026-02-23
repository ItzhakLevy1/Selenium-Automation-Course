package tests.w3schools;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.w3school.SmartWindowsSwitchByUrlAndTitleW3SchoolPage;
import tests.common.BaseTest;

public class SmartWindowsSwitchByUrlAndTitleW3SchoolTest extends BaseTest {

    private SmartWindowsSwitchByUrlAndTitleW3SchoolPage sws; // Defined at class level

    @BeforeClass
    public void setup() {
        driver = BaseTest.initDriver(); // Initialize the driver
        driver.get("https://www.w3schools.com/js/default.asp#gsc.tab=0");
        sws = new SmartWindowsSwitchByUrlAndTitleW3SchoolPage(driver);  // Initialize the page object once
    }

    @Test(priority = 1)
    public void tc01_openAllWindows() throws InterruptedException {
        // Open Page 2 (Try it page)
        sws.onPage1ClickBtnToOpenPage2();

        // Open Page 3 (Examples page)
        sws.onPage1ClickBtnToOpenPage3();

        // Give the browser a moment to register all handles
        Thread.sleep(2000);
    }

    @Test(priority = 2, dependsOnMethods = "tc01_openAllWindows")
    public void tc02_interactWithWindow1() throws InterruptedException {
        // Use part of the Title: "Tutorial"
        sws.goToPage("Tutorial");

        // Get the current URL
        String currentUrl = driver.getCurrentUrl();
        // Assertion: Verify URL contains 'js/default'
        Assert.assertTrue(currentUrl.contains("js/default"), "Error: Not on Page 1!");

        sws.interactWithPage1();
        Thread.sleep(2000);
    }

    @Test(priority = 3, dependsOnMethods = "tc01_openAllWindows")
    public void tc03_interactWithWindow2() throws InterruptedException {
        // Use part of the Title: "Tryit"
        sws.goToPage("Tryit");

        // Assertion: Verify Title is correct
        Assert.assertEquals(driver.getTitle(), "W3Schools Tryit Editor");

        sws.interactWithPage2();
        Thread.sleep(2000);

    }

    @Test(priority = 4, dependsOnMethods = "tc01_openAllWindows")
    public void tc04_interactWithWindow3() throws InterruptedException {
        // Use part of the URL: "js_examples"
        sws.goToPage("js_examples");

        // Assertion: Verify we are on the Examples page
        Assert.assertTrue(driver.getTitle().contains("Examples"), "Error: Not on Page 3!");

        sws.interactWithPage3();
        Thread.sleep(2000);

    }
}