package org.insider.listeners;

import org.insider.utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IAnnotationTransformer;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

// TestListener class implements the ITestListener interface to provide custom behavior on test failure.
// This class is used to log the failure and take a screenshot when a test fails.
public class TestListener implements ITestListener, IAnnotationTransformer {

    // Logger instance to enable logging.
    private static final Logger logger = LogManager.getLogger(TestListener.class);

    // Callback method that will be invoked when a test fails.
    @Override
    public void onTestFailure(ITestResult result) {
        // Retrieve the WebDriver instance from the test context.
        WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");

        // Create a ScreenshotUtil instance to take a screenshot.
        ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver);

        // Log the failure message.
        logger.error("Test failed: " + result.getName());

        // Take a screenshot, naming it with the failed test's name.
        screenshotUtil.takeScreenshot(result.getName());
    }

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        // add retry mechanism for tests
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }
}
