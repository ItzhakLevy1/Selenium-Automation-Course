package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SalesforcePasswordReset {
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		Thread.sleep(2000);
		
		driver.get("https://login.salesforce.com/");
		Thread.sleep(2000);
		
		driver.findElement(By.cssSelector("#forgot_password_link")).click();
		Thread.sleep(2000);
		
		String pageTitle = driver.getTitle();
		System.out.println("The page title is : " + pageTitle);
		Thread.sleep(2000);
		
		if(pageTitle.toLowerCase().contains("Reset Your Password")) {	// Get everything in lower case and only then compare to avoid any case sensitivity issues
			driver.findElement(By.cssSelector("#un")).sendKeys("Itzhak");
			Thread.sleep(2000);
			
			driver.findElement(By.cssSelector("#continue")).click();
			Thread.sleep(2000);
			
			String errorMessage = driver.findElement(By.cssSelector("#username-error")).getText();
			System.out.println("The error message is : " + errorMessage);
			Thread.sleep(4000);
			
		}
		
		driver.quit();
	}
}


/*
Multiple ways of targeting the same element : 

	Preferred 3 ways:
	driver.findElement(By.id("username"));  // By id
	driver.findElement(By.cssSelector(".username")); // By a single class and a CSS Selector
	driver.findElement(By.cssSelector("[name='username']")); // By attribute name

	Additional ways:
	driver.findElement(By.name("username")); // By name
	driver.findElement(By.className("username")); // By a single class
	driver.findElement(By.cssSelector("#username")); // By css selector and an id
	driver.findElement(By.cssSelector(".input.wide.username")); // By multiple classes
	driver.findElement(By.xpath("/html/body/div[1]/form/input[2]")); // By absolute xPath
	driver.findElement(By.xpath("//input[@id='username']")); // By relative xPath
	driver.findElement(By.xpath("//input[contains(@class, 'username')]")); // By xPath and a class
	driver.findElement(By.xpath("//input[@type='email']")); // By xPath and any other attribute
	driver.findElement(By.tagName("input")); // By tag name
	driver.findElement(By.cssSelector("input[name='username']")); // By a combination of a tag and an attribute
	driver.findElement(By.cssSelector("[aria-describedby='error']")); // By another characteristic - if exist within the element
	driver.findElement(By.cssSelector("#forgotPassForm > p")); // By a partial hierarchy ( Not recommended since the hierarchy may change in the future )

*/