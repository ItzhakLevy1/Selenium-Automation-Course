package utils;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ListenerClass implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("DEBUG: Test failed: " + result.getName());

        // Retrieve the driver from the Test Context (set in BaseTest)
        WebDriver driver = (WebDriver) result.getTestContext().getAttribute("WebDriver");

        if (driver != null) {
            // Capture screenshot as bytes for Allure
            byte[] screenshotContents = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            // Attach the Screenshot to Allure
            Allure.addAttachment("Failure Screenshot", new java.io.ByteArrayInputStream(screenshotContents));

            // Attach the Page Source to Allure (HTML structure)
            AllureAttachment.getPageSource(driver);

            // NEW: Attach the Browser Console Logs (JavaScript/Network errors)
            AllureAttachment.attachConsoleLogs(driver);

            // Save a local copy for IntelliJ
            saveScreenshotLocally(result, driver);
        }
    }

    private void saveScreenshotLocally(ITestResult result, WebDriver driver) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
        String fileName = result.getName() + "_" + timeStamp + ".jpg";
        try {
            FileUtils.copyFile(srcFile, new File("./ScreenShots/" + fileName));
        } catch (IOException e) {
            System.err.println("Failed to save local screenshot: " + e.getMessage());
        }
    }
}