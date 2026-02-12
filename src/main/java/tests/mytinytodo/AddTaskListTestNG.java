package tests.mytinytodo;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.mytinytodo.TasksPage;
import tests.common.BaseTest;

// By extending BaseTest, we inherit driver management, screenshots, and teardown logic
public class AddTaskListTestNG extends BaseTest {

    @BeforeClass
    public void setup() throws InterruptedException {
        // Initialize the inherited driver variable
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
    }
}