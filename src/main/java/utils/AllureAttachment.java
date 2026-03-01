package utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.WebDriver;

import java.nio.charset.StandardCharsets;

public class AllureAttachment {

    // Specifies that the returned value should be attached to the Allure report.
    // {0} refers to the first parameter of the method (message) which will be used as the attachment name.
    @Attachment(value = "{0}", type = "text/plain")
    public static String addTextAttachment(String message) {
        // The returned string is what actually gets written into the attachment file in the report
        return message;
    }

    /**
     * Captures the full HTML source of the current page.
     * @param value: Defines the display name of the attachment in the Allure report.
     * @param type: Specifies the MIME/Media type (text/html) so the browser knows how to render it.
     * @param fileExtension: Ensures the downloaded file from the report has a .html extension.
     */
    @Attachment(value = "Html source", type = "text/html", fileExtension = ".html")
    public static byte[] getPageSource(WebDriver driver) {
        // Extracts the DOM as a string and converts it to a UTF-8 byte array for Allure.
        return driver.getPageSource().getBytes(StandardCharsets.UTF_8);
    }
}
