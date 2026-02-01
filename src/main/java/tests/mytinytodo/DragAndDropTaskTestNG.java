package tests.mytinytodo;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.mytinytodo.TasksPage;
import tests.common.BaseTest;

public class DragAndDropTaskTestNG {

    private final String taskName = "Be great 1";

    WebDriver driver;

    @BeforeClass
    public void setup() throws InterruptedException{
        driver = BaseTest.initDriver();
        driver.get("https://www.mytinytodo.net/demo");
        Thread.sleep(6000);
    }

    @Test
    public void tc01_dragAndDrop() {

        TasksPage tp = new TasksPage(driver);
        tp.dragAndDropTask(taskName, 0);

    }

    @AfterClass
    public void teardown(){
        if(driver != null){
            driver.quit();
        }
    }
}
