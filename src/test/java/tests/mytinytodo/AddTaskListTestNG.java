package tests.mytinytodo;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.mytinytodo.TasksPage;
import tests.common.BaseTest;

/* Uses BDD (Behavior-Driven Development) hierarchy to organize reports by business value rather than code structure ( @Epic, @Feature ) */
// Groups tests under a high-level hierarchy in the Behaviors tab (can be seen under the "Behaviors" section in the allur report)
@Epic("Tasks and task lists / tab section")
// Sub-categorizes tests into specific features within the Epic (can be seen under the "Behaviors" section in the allur report)
@Feature("Add a task list / tab")

// By extending BaseTest, we inherit driver management, screenshots, and teardown logic
public class AddTaskListTestNG extends BaseTest {

    @BeforeClass
    public void setup() throws InterruptedException {
        // Initialize the inherited driver variable
        driver = BaseTest.initDriver();
        driver.get("https://www.mytinytodo.net/demo");
        Thread.sleep(1000);
    }

    // Describes the specific business user story being tested (can be seen under the "Suites" section in the allur report, under "Description")
    @Story("As a user I should be able to add a task list / tab")
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