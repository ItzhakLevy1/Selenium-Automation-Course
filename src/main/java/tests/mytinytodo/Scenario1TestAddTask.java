package tests.mytinytodo;

import org.openqa.selenium.WebDriver;
import pageobjects.mytinytodo.TasksPage;

public class Scenario1TestAddTask {
    public static void main(String[] args) throws InterruptedException {

        /*================= SETUP =================*/
        //
        WebDriver driver = tests.common.BaseTest.initDriver();
        driver.get("https://www.mytinytodo.net/demo/#alltasks");
        Thread.sleep(2000);

        // Create a new task
        TasksPage tp = new TasksPage(driver);
        tp.addTaskIfDoentExist("Be Great !");
        Thread.sleep(3000);
        driver.quit();
    }
}
