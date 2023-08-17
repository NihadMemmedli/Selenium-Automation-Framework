package org.insider.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.insider.utils.ExtentReportUtils;
import org.insider.utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class TestListener implements ITestListener, IAnnotationTransformer {
    // ExtentReports and ExtentTest objects to generate and represent the report
    private ExtentReports extentReports;
    private ExtentTest extentTest;

    // On the start of test suite, initialize the ExtentReports and attach the reporter
    @Override
    public void onStart(ITestContext context) {
        extentReports = ExtentReportUtils.initialiseExtentReport();
    }

    // On the start of individual test, create a new test entry in the report
    @Override
    public void onTestStart(ITestResult result) {
        extentTest = extentReports.createTest(result.getMethod().getMethodName());
        result.getTestContext().setAttribute("extentTest", extentTest);
    }

    // On completion of the entire test suite, generate the report
    @Override
    public void onFinish(ITestContext context) {
        ExtentReportUtils.generateExtentReport();
    }

    // On skipping a test, log the skipped status in the report
    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.skip(result.getMethod().getMethodName() + " is skipped");
    }

    // On test success, log the success status in the report
    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.pass(result.getMethod().getMethodName() + " is passed");
    }

    // On test failure, capture the screenshot, add to report, and log the error
    @Override
    public void onTestFailure(ITestResult result) {
        // Retrieve the WebDriver instance from the test context
        WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");

        // Capture screenshot
        ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver);
        String screenshotPath = screenshotUtil.takeScreenshot(result.getName());

        // Attach screenshot and error to the report
        extentTest.addScreenCaptureFromPath(screenshotPath);
        extentTest.fail(result.getThrowable());
    }

    // Transform the test annotation to add retry mechanism for tests
    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }
}
