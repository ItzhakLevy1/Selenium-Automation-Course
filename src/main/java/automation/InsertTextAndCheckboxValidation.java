package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InsertTextAndCheckboxValidation {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Create a wait object - wait a maximum of 10 seconds
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Go to URL
        driver.get("https://login.salesforce.com");
        Thread.sleep(2000);

        // Wait until the element exists in the DOM and is displayed, The wait.until operation returns the WebElement as soon as it is found
        WebElement userNameInputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#username")));

        try {
            // Add password to the password text box
            userNameInputField.sendKeys("Itzhak");
            System.out.println("Input field with user name");
        } catch (Exception e) {
            throw new RuntimeException("There has been an error while locating the input element : " + e);
        }

        // Checked the ‘remember me’ check box
        WebElement checkboxElement =  driver.findElement(By.cssSelector("#rememberUn"));
        checkboxElement.click();
        Thread.sleep(2000);


        // Check if the checkbox is checked, if yes Click the ‘Login’ button
        if(checkboxElement.isSelected()){
            driver.findElement(By.cssSelector("#Login")).click();
            System.out.println("The checkbox has been checked.");

        } else {
            System.out.println("The checkbox has not been checked yet !");
        }


        Thread.sleep(5000);

        driver.quit();
    }
}
