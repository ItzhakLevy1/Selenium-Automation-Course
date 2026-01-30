package tests.mytinytodo;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.mytinytodo.TasksPage;
import tests.common.BaseTest;

public class AddTaskTestNG {

    WebDriver driver;

    @BeforeClass
    public void setup() throws InterruptedException {
        driver = BaseTest.initDriver();
        driver.get("https://www.mytinytodo.net/demo");
        Thread.sleep(2000);
    }

    @Test
    public void testAddTask() throws InterruptedException {
        TasksPage tp = new TasksPage(driver);
        tp.addTask("Be great !", "Be great 1");
    }

    @AfterClass
    public void teardown() throws InterruptedException {
        if (driver != null) {
            driver.quit();
        }
    }

}
