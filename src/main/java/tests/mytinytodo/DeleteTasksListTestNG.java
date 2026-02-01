package tests.mytinytodo;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.mytinytodo.TasksPage;
import tests.common.BaseTest;

public class DeleteTasksListTestNG {
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = BaseTest.initDriver();
        driver.get("https://www.mytinytodo.net/demo");
    }

    @Test
    public void tc01_deleteTasksList() throws InterruptedException{
        TasksPage tp = new TasksPage(driver);
        tp.deleteList("Be Great !?$");
        Thread.sleep(3000);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
