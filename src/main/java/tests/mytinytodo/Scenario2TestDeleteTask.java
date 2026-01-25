package tests.mytinytodo;

import org.openqa.selenium.WebDriver;
import pageobjects.mytinytodo.TasksPage;

public class Scenario2TestDeleteTask {

    public static void main(String[] args) throws InterruptedException {

        // Add a custom pre-prepared driver
        WebDriver driver = tests.common.BaseTest.initDriver();
        driver.get("https://www.mytinytodo.net/demo/#alltasks");
        Thread.sleep(2000);

        // Delete a task
        TasksPage tp = new TasksPage(driver);
        tp.deleteTask("Be Great !");
        Thread.sleep(2000);




        driver.quit();
    }
}
