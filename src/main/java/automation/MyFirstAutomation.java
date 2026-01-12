package automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MyFirstAutomation {
    public static void main(String[] args) throws InterruptedException {	// throws InterruptedException is to allow the delay
        WebDriver driver = new ChromeDriver();	// Opens up a chrome tab
        driver.manage().window().maximize();	// Maximize opened tab
        driver.get("https://www.automation.co.il");	// Navigate to this URL
        
        String url = driver.getCurrentUrl();
        if (url.contains("automation")) {
        	System.out.println("url contains 'automation' ");
        	
        	String title = driver.getTitle();
        	System.out.println("Title : " + title);
        	Thread.sleep(4000);
        	
        	System.out.println("Getting page source for url : " + url);
        	Thread.sleep(4000);
        	
        	String pageSource = driver.getPageSource();
        	System.out.println("Page Source : " + pageSource);
        	Thread.sleep(4000);
        	
        	System.out.println("Navitating to : http://www.google.com");
        	Thread.sleep(4000);
        	
        	driver.get("http://www.google.com");
        	Thread.sleep(4000);
        	
        	driver.navigate().back();
        	Thread.sleep(4000);
        	
        	driver.navigate().forward();
        	Thread.sleep(4000);
        	
        	driver.navigate().refresh();
        	Thread.sleep(4000);
        	
        	System.out.println("Closing browser...");
        }
        
        driver.quit();
    }
}