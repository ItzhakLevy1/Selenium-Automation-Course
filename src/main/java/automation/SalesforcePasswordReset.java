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

	Preferred 5 ways:

	1. driver.findElement(By.cssSelector("[data-testid='123asd']")); // By a QA designated attribute name
	2. driver.findElement(By.id("username"));  // By id
	3. driver.findElement(By.cssSelector(".username")); // By a single class and a CSS Selector
	4. driver.findElement(By.cssSelector("[name='username']")); // By attribute name


	5. By a partial hierarchy, target the desired element's father or grandfather and use the > sign to look inside of it.
	   ( Use carefully since the hierarchy may change in the future ),

	   the space surrounding the > sign means to look until you find the first p tag - even if it is not the first element inside the father element,
	   No space around it would indicate the exact elements hierarchy and would require every element in between to be mentioned:
	   driver.findElement(By.cssSelector("#forgotPassForm > p"));

	   In case there are several similar elements inside the father element, we can select the desired one by its number in the hierarchy ( without any spaces ):
	   driver.findElement(By.cssSelector("#forgotPassForm>p:nth-child(4)"));

	   If there is no other choice, go up in the hierarchy and select as many grandfathers as needed:
	   driver.findElement(By.cssSelector("#forgotPassForm_3>#forgotPassForm_2:nth-child(2)>#forgotPassForm_1>p:nth-child(4)"));

	   ( Inspect > copy selector - should give the desired element's hierarchy)

	* Using only a part of an attribute's value ( in case any of its parts are dynamic and may change ) :
		[id='A12-B34-C56']	this uses all the value
		[id^='A12'] this uses the "starts with" part
		[id*='B34'] this uses the "contains anywhere" part
		[id$='C56'] this uses the "ends with" part


Additional ways:
	driver.findElement(By.tagName("input")); // By tag name
	driver.findElement(By.name("username")); // By name
	driver.findElement(By.className("username")); // By a single class
	driver.findElement(By.cssSelector("#username")); // By css selector and an id
	driver.findElement(By.cssSelector(".input.wide.username")); // By multiple classes
	driver.findElement(By.xpath("/html/body/div[1]/form/input[2]")); // By absolute xPath
	driver.findElement(By.xpath("//input[@id='username']")); // By relative xPath
	driver.findElement(By.xpath("//input[contains(@class, 'username')]")); // By xPath and a class
	driver.findElement(By.xpath("//input[@type='email']")); // By xPath and any other attribute
	driver.findElement(By.cssSelector("input[name='username']")); // By a combination of a tag and an attribute
	driver.findElement(By.cssSelector("[aria-describedby='error']")); // By another characteristic - if exist within the element

*/