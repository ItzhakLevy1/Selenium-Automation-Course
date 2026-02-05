package pageobjects.w3school;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.common.BasePage;

import java.util.Set;

public class SwitchWindowsW3SchoolPage extends BasePage {

    // A constructor
    public SwitchWindowsW3SchoolPage(WebDriver driver) {
        super(driver);
    }

    /*===== Page Factory  =====*/
    @FindBy(css = ".w3-example a")
    public WebElement buttonToPage2;


    /*===== Page Methods  =====*/

    // A method to open the second window
    public void openPage2(){
        click(buttonToPage2);
    }

    // A method to switch to second window
    public void switchToWindow2() {
        // Initialize the first window's variable
        String firstWindow = driver.getWindowHandle();

        // Get access to all windows
        Set<String> list = driver.getWindowHandles();

        // Loop all windows
        for (String window : list) {
            driver.switchTo().window(window);
        }
    }

}
