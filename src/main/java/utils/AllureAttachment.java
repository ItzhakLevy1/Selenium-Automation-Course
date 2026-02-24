package utils;

import io.qameta.allure.Attachment;

public class AllureAttachment {

    // Specifies that the returned value should be attached to the Allure report.
    // {0} refers to the first parameter of the method (message) which will be used as the attachment name.
    @Attachment(value = "{0}", type = "text/plain")
    public static String addTextAttachment(String message) {
        // The returned string is what actually gets written into the attachment file in the report
        return message;
    }
}
