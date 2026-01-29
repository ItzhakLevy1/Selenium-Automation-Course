package pageobjects.mytinytodo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

    /*================= A METHOD TO ADD A TASK LIST =================*/
    public void addTasklistIfDoesNotExist(String newTaskListName) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Grab all existing taskLists
        List<WebElement> listOfTaskLists = driver.findElements(By.cssSelector("div.title-block > span.title"));
        boolean taskListAlreadyExists = false;

        // 1. Check if the task list already exists
        for (WebElement element : listOfTaskLists) {
            if (element.getText().equalsIgnoreCase(newTaskListName)) {
                taskListAlreadyExists = true;
                System.out.println("Task list already exists! aborting...");
                break; // Since the task list already exists exit and stop the search loop
            }
        }

        // Making a desition outside the search loop
        if (taskListAlreadyExists) {
            System.out.println("Task list name is already in use!");
        } else {
            System.out.println("Task list name is not yet in use, I'll add it for you.");

            // Logic for adding a new taskList
            click(driver.findElement(By.cssSelector(".tab-height-wrapper > span")));

            // Wait until the element is visible, in case it does not exist - after 10 seconds (dynamic) display an informative error
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#modalTextInput")));

            // Wait for 2 seconds regardless
            Thread.sleep(2000);

            fillText(driver.findElement(By.cssSelector("#modalTextInput")), newTaskListName);
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

    /*================= A METHOD TO DELETE A TASK BY HOVERING FIRST =================*/
    public void deleteTaskFromTaskRow(String taskTabName, String taskName) throws InterruptedException {

        // A setup to wait (for a maximum of 10 seconds) for an elements to show
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // A WebElement to place all of the tasks containers in ( as tabs )
        List<WebElement> tabs =  driver.findElements(By.cssSelector("li.mtt-tab"));

        // A boolean flag for a found task
        boolean taskTabNameFound = false;
        boolean taskNameFound = false;

        // Loop through all tabs and in them look for a matching task - to - row name
        for(WebElement tab: tabs){
            if (tab.getText().equalsIgnoreCase(taskTabName)) {
                taskTabNameFound =  true;
                tab.click();
                break;
            }
        }
        if(!taskTabNameFound){
            System.out.println("Task tab has not been found ! : " + taskTabName);
        }

        // (Wait until they appear and then) Grab all tasks within the found task row and loop through them
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".task-block")));
        List<WebElement> tasksInGroup = driver.findElements(By.cssSelector(".task-block"));

        // Loop all tasks and print them
        for (WebElement task : tasksInGroup) {
            WebElement titleElement = task.findElement(By.cssSelector(".task-title"));

            if (titleElement.getText().equalsIgnoreCase(taskName)) {
                // If a match is found mark it as a true flag and click it
                taskNameFound =  true;

                // Use the moveMouseToElement method from BasePage to hover to the desired element
                moveMouseToElement(titleElement);

                // In the found row element target the hidden menu element and click it
                WebElement menuBtn = task.findElement(By.cssSelector(".taskactionbtn"));
                click(menuBtn);

                WebElement taskDeleteOption = driver.findElement(By.cssSelector("#cmenu_delete"));
                wait.until(ExpectedConditions.elementToBeClickable(taskDeleteOption)).click();

                // Once the alert is reviled, click it's OK / confirm button
                WebElement alertOkBtn =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#btnModalOk")));
                click(alertOkBtn);
            }
        }
        if(!taskNameFound){
            System.out.println("Task name has not been found ! : " + taskName);
        }
    }

    /*================= A METHOD TO DRAG AND DROP A TASK =================*/
    public void dragAndDropTask(String taskName, int targetPosition) {
        List<WebElement> tasks = driver.findElements(By.cssSelector(".task-block"));
        boolean found = false; // Using a flag to track success

        for (WebElement task : tasks) {
            WebElement titleElement = task.findElement(By.cssSelector(".task-title"));

            if (titleElement.getText().equalsIgnoreCase(taskName)) {
                found = true;
                /*
                 * Identify the target location for the drop operation.
                 * We retrieve the WebElement at the specific index (position) from our list
                 * to act as the destination for the drag action.
                 */
                WebElement targetTask = tasks.get(targetPosition);
                Actions action = new Actions(driver);

                action.clickAndHold(task)
                        .pause(Duration.ofMillis(1000))
                        .moveToElement(targetTask)
                        .pause(Duration.ofMillis(1000))
                        .release()
                        .pause(Duration.ofMillis(1000))
                        .build()
                        .perform();

                System.out.println("Dragged task '" + taskName + "' to position " + targetPosition);
                break; // Exit ONLY when the task is found and moved
            }
        }

        // Check after the loop if the task was ever found
        if (!found) {
            System.out.println("Task '" + taskName + "' was not found in the list.");
        }
    }













}
