package tests.mytinytodo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.mytinytodo.TasksPage;
import tests.common.BaseTest;

public class CheckNumOfTasksTestNG extends BaseTest {

    WebDriverWait wait;

    WebDriver driver;

    @BeforeClass
    public void setup() throws InterruptedException{
        driver = BaseTest.initDriver();
        driver.get("https://www.mytinytodo.net/demo");
        Thread.sleep(2000);
    }

    @Test
    public void tc01_CheckNumOfTasks() throws InterruptedException {
        TasksPage tp = new TasksPage(driver);

        tp.chooseListAndClickIt("Be Great !");
        Thread.sleep(2000);

        int before = tp.getNumOfTasks();
        System.out.println("Before : " + before);

        tp.addTask("Be Great !", "Be great 3");

        // Allow the added task to be created and detected
        Thread.sleep(1000);

        int after = tp.getNumOfTasks();
        System.out.println("After : " + after);

        Assert.assertEquals(after, before + 1, "The task count did not increment correctly!");
    }
    @AfterClass
    public void teardown(){
        if(driver != null){
            driver.quit();
        }
    }
}
