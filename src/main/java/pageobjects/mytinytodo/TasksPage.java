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

    /*================= A METHOD TO ADD A TASK =================*/
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

    /*================= A METHOD TO DELETE A TASK =================*/
    public void deleteTask(String taskName) throws InterruptedException {
        // 1. Setup wait for dynamic elements
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 1. Locate all elements that represent a task row/tab in the list
        // We use 'li.mtt-tab' as the parent container for each individual task
        List<WebElement> rows = driver.findElements(By.cssSelector("li.mtt-tab"));

        // Initialize a flag to track if we found the specific task we are looking for
        boolean found = false;

        // 2. Iterate through each row found in the list to inspect its content
        for (WebElement row : rows) {

            // 3. Find the specific 'span' element that contains the task title text
            // We search relative to 'row' (the parent) to ensure we get the correct title
            WebElement titleElement = row.findElement(By.cssSelector("span.title"));

            // 4. Compare the actual text on the page with the 'taskName' we want to find
            // We use 'equalsIgnoreCase' to make the search robust against capital/small letters
            if (titleElement.getText().equalsIgnoreCase(taskName)) {

                // 5. If a match is found, mark the flag as true and proceed with the logic
                found = true;

                // 2. Click task to reveal menu
                click(titleElement);

                // 3. Click the dropdown menu icon
                WebElement menuBtn = row.findElement(By.cssSelector("div.list-action"));
                click(menuBtn);
                Thread.sleep(2000);


                // 4. Click 'Delete list' from the menu
                // We wait until the menu item is clickable
                wait.until(ExpectedConditions.elementToBeClickable(By.id("btnDeleteList")));
                click(driver.findElement(By.id("btnDeleteList")));
                Thread.sleep(2000);


                // 5. Handle the Modal Confirmation
                System.out.println("Confirmation modal appeared. Clicking OK...");
                Thread.sleep(2000);


                // Wait for the modal OK button to be visible and click it
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnModalOk")));
                click(driver.findElement(By.id("btnModalOk")));

                System.out.println("Success: Task list '" + taskName + "' has been deleted.");
                Thread.sleep(2000);

                break;
            }
        }

        if (!found) {
            System.out.println("Task not found: " + taskName);
        }
    }

}
