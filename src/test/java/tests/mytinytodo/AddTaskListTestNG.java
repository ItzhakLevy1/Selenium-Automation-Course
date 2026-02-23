package tests.mytinytodo;

import io.qameta.allure.Description;
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

    // A TestNG annotation which will be displayed as a title on the allure report on the top of the report page
    @Test(description = "Add a task list / tab")

    // An allure annotation which will be displayed as a title on the allure report at the middle / bottom of the report page
    @Description("An allure description - Creating and adding a new tab / title")

    public void addTaskList() throws InterruptedException {
        // Create a new task list
        TasksPage tp = new TasksPage(driver);
        tp.addTasklistIfDoesNotExist("Be Great !");

        Thread.sleep(1000);
    }
}