package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MyFirstAutomation {
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("https://automation.co.il/tutorials/selenium/demo1/indexID.html");
		Thread.sleep(2000);
		
		driver.findElement(By.id("firstname")).sendKeys("Itzhak");
		driver.findElement(By.id("lastname")).sendKeys("Levy");
		driver.findElement(By.id("email")).sendKeys("myEmail@gmail.com");
		Thread.sleep(2000);

		driver.findElement(By.id("next")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.id("Beginner")).click();
		
		driver.findElement(By.id("next")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.id("streetname")).clear();
		driver.findElement(By.id("streetname")).sendKeys("Sheshet");
		
		driver.findElement(By.id("streetnumber")).sendKeys("5");
		
		driver.findElement(By.id("city")).sendKeys("Netania");
		Thread.sleep(2000);
		
		driver.findElement(By.id("finish")).click();
		Thread.sleep(2000);
		
		driver.quit();
	}
}