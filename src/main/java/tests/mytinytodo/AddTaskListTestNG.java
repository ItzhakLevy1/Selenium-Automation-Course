package tests.mytinytodo;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.mytinytodo.TasksPage;
import tests.common.BaseTest;

public class AddTaskListTestNG {

    WebDriver driver;

    @BeforeClass
    public void setup() throws InterruptedException {
        driver = BaseTest.initDriver();
        driver.get("https://www.mytinytodo.net/demo");
        Thread.sleep(1000);
    }

    @Test
    public void addTaskList() throws InterruptedException {

        // Create a new task list
        TasksPage tp = new TasksPage(driver);
        tp.addTasklistIfDoesNotExist("Be Great !");
        Thread.sleep(1000);
        driver.quit();
    }

    @AfterClass
    public void teardown() throws InterruptedException {
        if (driver != null) {
            driver.quit();
        }
    }
}
