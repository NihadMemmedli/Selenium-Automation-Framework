package org.insider.utils;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

// ConfigUtil class is used to read and manage the application configuration properties
public class ConfigUtil {
    private static final String CONFIG_FILE = "config.properties"; // Configuration file name
    private static final Properties properties; // Properties object to hold the key-value pairs from the configuration file

    // Static block to load the properties file when the class is first accessed
    static {
        properties = new Properties();
        try (InputStream input = ConfigUtil.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            properties.load(input); // Loading properties from the file
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file: " + CONFIG_FILE, e);
        }
    }

    // Method to get the value of a given property key
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    // Method to get the default wait timeout duration as specified in the properties file
    public static Duration getDefaultWaitTimeout() {
        return Duration.ofSeconds(Integer.parseInt(getProperty("defaultWaitTimeout")));
    }

    // Method to get the browser type as specified in the properties file
    public static String getBrowser() {
        return properties.getProperty("browser");
    }

    // Method to get the base URL as specified in the properties file
    public static String getBaseURL() {
        return properties.getProperty("baseUrl");
    }

    // Method to get the base retry value as specified in the properties file
    public static Integer getRetry() {
        return Integer.parseInt(properties.getProperty("retry"));
    }
}
