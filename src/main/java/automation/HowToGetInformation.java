package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HowToGetInformation {
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.get("https://prd.canvusapps.com/signup");
		
		// First way of using an id to get an element:
		driver.findElement(By.id("user_name")).sendKeys("Itzhak");
		Thread.sleep(2000);
		
		driver.findElement(By.id("user_name")).clear();
		Thread.sleep(2000);
		
		// Second way of using an id to get an element:
		driver.findElement(By.cssSelector("#user_name")).sendKeys("Itzhak");
		Thread.sleep(2000);
		
		// Intentionally create an error messate on the webpage
		driver.findElement(By.cssSelector(".submit.btn.btn-primary")).click();
		Thread.sleep(2000);
		
		// Get the text of the error message and log it
		String errorMessage = driver.findElement(By.cssSelector(".alert.alert-error.alert-block.error")).getText();
		System.out.println("The error message is : " + errorMessage);
		Thread.sleep(2000);
		
		// Get the value of the typed name from the name input field and log it
		String name = driver.findElement(By.cssSelector("#user_name")).getAttribute("value");
		System.out.println("The value from the name input field is : " + name);
		Thread.sleep(2000);
		
		driver.quit();
	}
	
}
