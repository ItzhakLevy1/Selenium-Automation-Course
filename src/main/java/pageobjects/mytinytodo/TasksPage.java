package pageobjects.mytinytodo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.common.BasePage;

import java.time.Duration;
import java.util.List;

public class TasksPage extends BasePage {

    @FindBy(css = "li.mtt-tab .title")
    private List<WebElement> tabs;


    // Passes the WebDriver instance to the parent BasePage constructor
    public TasksPage(WebDriver driver) {
        super(driver);
    }

    /*================= A METHOD TO ADD A TASK LIST =================*/
    public void addTasklistIfDoesNotExist(String newTaskListName) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Grab all existing taskLists
        boolean taskListAlreadyExists = false;

        // 1. Check if the task list already exists
        for (WebElement element : tabs) {
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

            fillText(driver.findElement(By.cssSelector("#modalTextInput")), newTaskListName);
            click(driver.findElement(By.cssSelector("#btnModalOk")));
        }
    }

    /*================= A METHOD TO DELETE A LIST =================*/
    public void deleteList(String listToDelete) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean found = false;

        for (WebElement element : tabs) {
            /* * Since 'tabs' is already defined as 'li.mtt-tab .title' via @FindBy,
             * 'element' IS the title span. No need to call findElement(By.cssSelector(".title")) again.
             */
            if (element.getText().equalsIgnoreCase(listToDelete)) {
                found = true;

                // Click the list to ensure it is active
                element.click();
                System.out.println("List selected for deletion: " + listToDelete);

                /* * We use ancestor::li to find the parent container (the list item),
                 * Meaning: from the current location (.) which is a span of the current iterated list,
                 * go up until you find an li that contains the specified class
                 * regardless of how many nested levels exist between the title and the li.
                 */
                WebElement parentLi = element.findElement(By.xpath("./ancestor::li[contains(@class, 'mtt-tab')]"));
                WebElement listCardDeleteButton = parentLi.findElement(By.cssSelector(".list-action"));

                listCardDeleteButton.click();

                // Select the delete option from the dropdown menu
                WebElement optionFromDropDown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#btnDeleteList")));
                optionFromDropDown.click();

                // Confirm deletion in the modal popup
                WebElement deleteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#btnModalOk")));
                deleteButton.click();

                System.out.println("List '" + listToDelete + "' deleted successfully.");
                break;
            }
        }

        if (!found) {
            System.out.println("List deletion failed: '" + listToDelete + "' was not found.");
        }
    }

    /*================= A METHOD TO ADD A TASK =================*/
    public void addTask(String listToAddTaskTo, String taskToAdd) throws InterruptedException {
        // 1. First, navigate to the correct list using our helper method
        chooseListAndClickIt(listToAddTaskTo);

        // 2. Check if the task already exists in the current list
        List<WebElement> listOfTasks = driver.findElements(By.cssSelector(".task-title"));
        boolean taskAlreadyExists = false;

        for (WebElement existingTask : listOfTasks) {
            if (existingTask.getText().equalsIgnoreCase(taskToAdd)) {
                taskAlreadyExists = true;
                break;
            }
        }

        // 3. Add the task only if it's unique
        if (!taskAlreadyExists) {
            driver.findElement(By.cssSelector("#task")).sendKeys(taskToAdd);
            driver.findElement(By.cssSelector("#newtask_submit")).click();
            System.out.println("Task added successfully: " + taskToAdd);
        } else {
            System.out.println("Task '" + taskToAdd + "' already exists! Aborting...");
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

    // A method that looks for a list and clicks it to get its value
    public void chooseListAndClickIt(String listName) {

        boolean found = false;

        for (WebElement task : tabs) {
            if (task.getText().equalsIgnoreCase(listName)) {
                task.click();
                found = true;
            }
        }

        if (!found) {
            System.out.println("List " + listName + " was not found.");
        }
    }

    /*================= VALIDATION METHODS =================*/

    public int getNumOfTasks() {
        String s = driver.findElement(By.cssSelector("#total")).getText();
        System.out.println("Number of tasks are : " + s);
        int numOfTasks = Integer.parseInt(s);   // Convert type String into an int
        return numOfTasks;
    }
}
