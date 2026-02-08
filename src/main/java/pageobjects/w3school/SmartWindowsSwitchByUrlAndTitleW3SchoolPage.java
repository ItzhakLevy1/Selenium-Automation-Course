package pageobjects.w3school;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.common.BasePage;

public class SmartWindowsSwitchByUrlAndTitleW3SchoolPage extends BasePage {

    public SmartWindowsSwitchByUrlAndTitleW3SchoolPage(WebDriver driver) {
        super(driver);
    }

    /*===== Page Factory =====*/

    // Page 1
    @FindBy(css = "#tnb-menu-toggle-wrapper")
    private WebElement menuButtonOnPage1;

    @FindBy(css = "#navbtn_exercises")
    private WebElement exercisesDropDownMenuOnPage1;

    @FindBy(css = ".w3-example a")
    private WebElement buttonToPage2;

    @FindBy(css = ".ws-btn[href='js_examples.asp']")
    private WebElement buttonToPage3;

    // Page 2
    @FindBy(css = ".fa-adjust.ga-tryit")
    private WebElement onlyButtonOnPage2;

    // Page 3
    @FindBy(css = "#tnb-menu-toggle-wrapper")
    private WebElement onlyButtonOnPage3;


    /*===== Page Methods =====*/

    // Navigation methods using the smart switch
    public void goToPage(String identifier) {
        // Switch focus using part of URL or Title
        smartSwitchToWindowByUrlOrTitle(identifier);
    }

    public void onPage1ClickBtnToOpenPage2() {
        openLinkInNewTab(buttonToPage2);
    }

    public void onPage1ClickBtnToOpenPage3() {
        // Using the custom method to open in new tab without auto-switching
        openLinkInNewTab(buttonToPage3);
    }

    public void interactWithPage1() throws InterruptedException {
        System.out.println("We are Interacting with Page 1 !");
        System.out.println("Clicking the menuButtonOnPage1 on Page 1 !");
        click(menuButtonOnPage1);

        System.out.println("Clicked the menuButtonOnPage1 on Page 1 !");

        click(menuButtonOnPage1); // Toggle
        click(exercisesDropDownMenuOnPage1);
        click(exercisesDropDownMenuOnPage1); // Toggle
        click(exercisesDropDownMenuOnPage1);
        click(exercisesDropDownMenuOnPage1); // Toggle
    }

    public void interactWithPage2() {
        System.out.println("We are Interacting with Page 2 !");
        click(onlyButtonOnPage2);
        click(onlyButtonOnPage2);
        click(onlyButtonOnPage2);
        click(onlyButtonOnPage2);
        click(onlyButtonOnPage2);
        click(onlyButtonOnPage2);

    }

    public void interactWithPage3() {
        System.out.println("We are Interacting with Page 3 !");
        click(onlyButtonOnPage3);
        click(onlyButtonOnPage3);
        click(onlyButtonOnPage3);
        click(onlyButtonOnPage3);
        click(onlyButtonOnPage3);
        click(onlyButtonOnPage3);
    }
}