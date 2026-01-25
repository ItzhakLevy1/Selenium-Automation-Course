package pageobjects.mytinytodo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.common.BasePage;

import java.time.Duration;
import java.util.List;

public class TasksPage extends BasePage {

    // Passes the WebDriver instance to the parent BasePage constructor
    public TasksPage(WebDriver driver) {
        super(driver);
    }

    public void addTaskIfDoentExist(String newTaskName) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Grab all existing tasks
        List<WebElement> listOfTasks = driver.findElements(By.cssSelector("div.title-block > span.title"));
        boolean taskAlreadyExists = false;

        // 1. Check if the task already exists
        for (WebElement element : listOfTasks) {
            if (element.getText().equalsIgnoreCase(newTaskName)) {
                taskAlreadyExists = true;
                System.out.println("Task already exists! aborting...");
                break; // Since the task already exists exit and stop the search loop
            }
        }

        // Making a desition outside of the search loop
        if (taskAlreadyExists) {
            System.out.println("Task name is already in use!");
        } else {
            System.out.println("Task name is not yet in use, I'll add it for you.");

            // Logic for adding a new task
            click(driver.findElement(By.cssSelector(".tab-height-wrapper > span")));

            // Wait until the element is visible, in case it does not exist - after 10 seconds (dynamic) display an informative error
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#modalTextInput")));

            // Wait for 2 seconds regardless
            Thread.sleep(2000);

            fillText(driver.findElement(By.cssSelector("#modalTextInput")), newTaskName);
            click(driver.findElement(By.cssSelector("#btnModalOk")));
        }
    }


}
