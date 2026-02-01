package tests.mytinytodo;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.mytinytodo.TasksPage;
import tests.common.BaseTest;


public class DeleteTaskByFirstHoveringTestNG {

    // A global driver
    WebDriver driver;

    // A method that runs before any other method
    @BeforeClass
    public void setup()  throws InterruptedException{
        driver = BaseTest.initDriver();
        driver.get("https://www.mytinytodo.net/demo");
        Thread.sleep(2000);
    }

    @Test
    public void tc01_deleteTaskFromTaskRow() throws InterruptedException{
        TasksPage tp = new TasksPage(driver);
        tp.deleteTaskFromTaskRow("Be great !", "Be great 1");
        Thread.sleep(2000);
    }

    // A method that runs last
    @AfterClass
    public void teardown() {
        if(driver != null) {
            driver.quit();
        }
    }

}