package tests.w3schools;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.w3school.SwitchToSecondWindowW3SchoolPage;
import tests.common.BaseTest;

public class SwitchToSecondWindowW3SchoolTestNG extends BaseTest {

    @BeforeClass
    public void setup() {
        driver = BaseTest.initDriver();
        driver.get("https://www.w3schools.com/js/default.asp#gsc.tab=0");
    }

    @Test(priority = 1)
    public void tc01_fromPage1TriggerWindow2() throws InterruptedException {
        SwitchToSecondWindowW3SchoolPage sp = new SwitchToSecondWindowW3SchoolPage(driver);
        sp.openPage2();
        Thread.sleep(3000);
    }

    @Test(priority = 2, dependsOnMethods = "tc01_fromPage1TriggerWindow2")
    public void tc02_switchToWindow2()  throws InterruptedException {
        SwitchToSecondWindowW3SchoolPage sp = new SwitchToSecondWindowW3SchoolPage(driver);
        sp.switchToWindow2();
        Thread.sleep(3000);
    }
}
