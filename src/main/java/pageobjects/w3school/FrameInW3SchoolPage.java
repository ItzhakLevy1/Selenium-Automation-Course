package pageobjects.w3school;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.common.BasePage;

public class FrameInW3SchoolPage extends BasePage {

    public FrameInW3SchoolPage(WebDriver driver) {
        super(driver);
    }

    /*===== Page Factory  =====*/

    // The frame element
    @FindBy(css = "iframe#iframeResult")
    private WebElement iframe;

    // The "Click me to display Date and Time" button within the frame
    @FindBy(css = "[type='button']")
    private WebElement iframeButton;

    // The toggle theme button
    @FindBy(css = ".fa-adjust.ga-tryit")
    private WebElement toggleThemeButton;

    // The "save code" button
    @FindBy(css = "[title='Save code']")
    private WebElement saveCodeButton;

    // The modal element
    @FindBy(css = "#spaceItModal")
    WebElement modalOfSaveCode;

    // The modal closing button
    @FindBy(css = "#spaceItModalCloseButton a")
    WebElement closeModalOfSaveCodeButton;

    // The Hamburger menu
    @FindBy(css = "#menuButton")
    WebElement hamburgerMenuButton;


    /*===== Page Methods  =====*/

    // A method to switch into the frame
    public void switchToFrame(){

        driver.switchTo().frame(iframe);
    }

    // A method to switch out of the frame and into the default content
    public void switchToDefaultFrame(){

        driver.switchTo().defaultContent();
    }

    // A method to click the toggle theme button
    public void clickToggleThemeMode(){

        click(toggleThemeButton);
    }

    // A method to click button inside the frame
    public void clickIframeButton(){

        click(iframeButton);
    }

    // Click the "save code" icon
    public void clickSaveCodeButton() {
        click(saveCodeButton);
    }

    // A method to close the modal
    public void closeSaveCodeModal() {
        click(closeModalOfSaveCodeButton);
    }

    // Click the hamburger menu button
    public void clickHamburgerMenuButton() {
        click(hamburgerMenuButton);
    }
}
