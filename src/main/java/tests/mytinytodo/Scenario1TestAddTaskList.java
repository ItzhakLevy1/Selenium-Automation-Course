package tests.mytinytodo;

import org.openqa.selenium.WebDriver;
import pageobjects.mytinytodo.TasksPage;

public class Scenario1TestAddTaskList {
    public static void main(String[] args) throws InterruptedException {

        // Setup
        WebDriver driver = tests.common.BaseTest.initDriver();
        driver.get("https://www.mytinytodo.net/demo/#alltasks");
        Thread.sleep(2000);

        // Create a new task list
        TasksPage tp = new TasksPage(driver);
        tp.addTasklistIfDoesNotExist("Be Great !");
        Thread.sleep(3000);
        driver.quit();
    }
}
