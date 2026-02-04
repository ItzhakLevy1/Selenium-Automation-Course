package automation;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.w3school.FrameInW3SchoolPage;
import tests.common.BaseTest;

public class FrameInW3SchoolTestNG {

    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = BaseTest.initDriver();
        driver.get("https://www.w3schools.com/js/tryit.asp?filename=tryjs_myfirst");
    }

    @Test
    public void frameInW3SchoolTestNG() throws InterruptedException {

        FrameInW3SchoolPage fp = new FrameInW3SchoolPage(driver);

        // Toggle theme mode
        fp.clickToggleThemeMode();
        // Switch to the iframe
        fp.switchToFrame();
        // Click the iframe button
        fp.clickIframeButton();
        // Switch to the default content
        fp.switchToDefaultFrame();
        // Click the theme mode
        fp.clickToggleThemeMode();
        // Switch to the iframe
        fp.switchToFrame();
        // Click the iframe button
        fp.clickIframeButton();
        // Switch to the default content
        fp.switchToDefaultFrame();
        // Click the "save code" button
        fp.clickSaveCodeButton();
        Thread.sleep(2000);
        System.out.println("Switching to the modal window");
        // Click the close button on the modal
        fp.closeSaveCodeModal();
        System.out.println("Successfully switched to the modal window");

        // Click the theme mode
        fp.clickToggleThemeMode();
        // Click the hamburger menu
        fp.clickHamburgerMenuButton();
    }

    @AfterClass
    public void teardown(){
        if (driver != null) {
            driver.quit();
        }
    }

}
