package tests.mytinytodo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.mytinytodo.TasksPage;

public class TasksTest {
    public static void main(String[] args) throws InterruptedException {

        /*================= SETUP =================*/
        //
        WebDriver driver = tests.common.BaseTest.initDriver();
        driver.get("https://www.mytinytodo.net/demo/#alltasks");
        Thread.sleep(2000);

        // Create a new task
        TasksPage tp = new TasksPage(driver);
        tp.addSimpleTask();
        Thread.sleep(2000);
        System.out.println("Task Added, Test passed");
        Thread.sleep(2000);


        driver.quit();

    }
}
