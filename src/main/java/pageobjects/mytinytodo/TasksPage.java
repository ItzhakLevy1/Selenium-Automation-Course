package pageobjects.mytinytodo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageobjects.common.BasePage;

public class TasksPage extends BasePage {

    // Passes the WebDriver instance to the parent BasePage constructor
    public TasksPage(WebDriver driver) {
        super(driver);
    }

    public void addSimpleTask(String newTaskName) throws InterruptedException {
        // Click on the add task + icon
        click(driver.findElement(By.cssSelector(".tab-height-wrapper > span")));
        Thread.sleep(2000);
        // Clear the input and insert new task name text
        fillText(driver.findElement(By.cssSelector("#modalTextInput")), newTaskName);
        click(driver.findElement(By.cssSelector("#btnModalOk")));
    }

}
