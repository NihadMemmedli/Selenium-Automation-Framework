package org.insider.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {

    // Using ThreadLocal to store WebDriver instances, allowing parallel execution
    private final static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    /**
     * Returns the WebDriver instance associated with the current thread.
     *
     * @return WebDriver instance for the current thread.
     */
    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    /**
     * Creates a WebDriver instance based on the provided browser name.
     * The WebDriver is set up for the current thread.
     *
     * @param browser Name of the browser. Supported values are "chrome" and "firefox".
     * @throws IllegalArgumentException if the provided browser name is unsupported.
     */
    public static void createDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                // Using WebDriverManager to handle the ChromeDriver setup
                io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
                driverThreadLocal.set(new ChromeDriver());
                break;
            case "firefox":
                // Using WebDriverManager to handle the FirefoxDriver setup
                io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver().setup();
                driverThreadLocal.set(new FirefoxDriver());
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    /**
     * Quits the WebDriver instance associated with the current thread and removes the reference from ThreadLocal.
     */
    public static void quitDriver() {
        driverThreadLocal.get().quit();
        driverThreadLocal.remove();
    }
}
