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
     * A method that captures the full HTML source of the current page.
     * @param value: Defines the display name of the attachment in the Allure report.
     * @param type: Specifies the MIME/Media type (text/html) so the browser knows how to render it.
     * @param fileExtension: Ensures the downloaded file from the report has a .html extension.
     */
    @Attachment(value = "Html source", type = "text/html", fileExtension = ".html")
    public static byte[] getPageSource(WebDriver driver) {
        // Extracts the DOM as a string and converts it to a UTF-8 byte array for Allure.
        return driver.getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    /**
     *  A method that captures browser console logs (JavaScript errors, network issues, etc.).
     * @param driver: The WebDriver instance to retrieve logs from.
     * @return A formatted string of all log entries or a "not found" message.
     */
    @Attachment(value = "Console Logs", type = "text/plain", fileExtension = ".txt")
    public static String attachConsoleLogs(WebDriver driver) {
        /*
         * Access the browser logs, filter by BROWSER type, and join all entries
         * into a single string separated by new lines.
         */
        String consoleLogs = driver
                .manage()
                .logs()
                .get(org.openqa.selenium.logging.LogType.BROWSER)
                .getAll()
                .stream()
                .map(Object::toString)
                .collect(java.util.stream.Collectors.joining(System.lineSeparator()));

        // Check if logs are empty using a simple check (replaces StringUtils if not available)
        return (consoleLogs == null || consoleLogs.trim().isEmpty()) ? "No Console Logs Found" : consoleLogs;
    }
}
