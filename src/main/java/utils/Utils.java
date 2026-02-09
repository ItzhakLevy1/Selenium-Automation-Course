package utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Utils {

    // "static" makes the method belong to the class and not to a specific instance,
    // allowing us to call it directly as: Utils.readProperty("url")
    public static String readProperty(String key) {

        // Initialize an empty string to store the value found in the properties file
        String value = "";

        // Use try-with-resources to automatically close the InputStream after use
        try (InputStream input = new FileInputStream("./src/main/java/data/configuration.properties")) {

            // Create a new Properties object which provides methods to manage key-value pairs
            Properties prop = new Properties();

            // Load the data from the file stream into the Properties object
            prop.load(input);

            // Retrieve the specific value associated with the provided key
            value = prop.getProperty(key);

        } catch (Exception e) {
            // Providing feedback if the file is missing, inaccessible, or the key doesn't exist
            System.err.println("Error reading key '" + key + "' from properties file: " + e.getMessage());
        }

        // Return the retrieved value (or an empty string if not found/error occurred)
        return value;
    }
}