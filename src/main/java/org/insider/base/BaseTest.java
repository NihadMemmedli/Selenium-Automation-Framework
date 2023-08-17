package org.insider.base;

import com.aventstack.extentreports.ExtentTest;
import org.insider.driver.WebDriverFactory;
import org.insider.manager.PageObjectManager;
import org.insider.utils.ConfigUtil;
import org.insider.utils.LoggerUtil;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.ITestContext;

import java.lang.reflect.Method;

/**
 * This class serves as a foundation for all test cases. It provides setup and teardown methods
 * that are executed before and after each test method respectively. It initializes the WebDriver
 * and associated utilities, and ensures cleanup post-test execution.
 */
public class BaseTest {

    // Instance of WebDriver for browser interactions.
    protected WebDriver driver;

    // Utility to manage page objects and avoid re-initializing them.
    protected PageObjectManager pageObjectManager;

    // ExtentTest instance for logging test steps in the report.
    protected ExtentTest extentTest;

    /**
     * This method runs before each test method.
     * It sets up the WebDriver, maximizes the browser window,
     * and initializes necessary utilities and managers.
     *
     * @param m        The test method that's about to be executed.
     * @param context  The test context for sharing attributes and parameters.
     */
    @BeforeMethod
    public void setup(Method m, ITestContext context) {
        // Log that the WebDriver setup is starting.
        LoggerUtil.logInfo("Setting up WebDriver");

        // Fetch the extentTest instance from the test context to log test steps.
        extentTest = (ExtentTest) context.getAttribute("extentTest");

        // Retrieve the browser configuration and initialize the driver.
        String browser = ConfigUtil.getBrowser();
        WebDriverFactory.createDriver(browser);
        driver = WebDriverFactory.getDriver();

        // Initialize the PageObjectManager to manage page object instances.
        pageObjectManager = new PageObjectManager(driver);

        // Set attributes in the test context for further use in tests or listeners.
        context.setAttribute("pageObjectManager", pageObjectManager);
        context.setAttribute("driver", driver);

        // Maximize the browser window.
        driver.manage().window().maximize();
    }

    /**
     * This method runs after each test method.
     * It ensures that the browser and its associated session are closed properly.
     *
     * @param m      The test method that was executed.
     * @param result The result of the test method execution.
     */
    @AfterMethod
    public void teardown(Method m, ITestResult result) {
        // Check if the driver is initialized and if so, quit the driver.
        if (driver != null) {
            WebDriverFactory.quitDriver();
        }
    }
}
