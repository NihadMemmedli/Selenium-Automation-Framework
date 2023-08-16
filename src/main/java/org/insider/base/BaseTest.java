package org.insider.base;

import org.insider.driver.WebDriverFactory;
import org.insider.manager.PageObjectManager;
import org.insider.utils.ConfigUtil;
import org.insider.utils.LoggerUtil;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.ITestContext;

public class BaseTest {

    protected WebDriver driver;
    protected PageObjectManager pageObjectManager;

    @BeforeMethod
    public void setup(ITestContext context) {
        LoggerUtil.logInfo("Setting up WebDriver");

        String browser = ConfigUtil.getBrowser();
        WebDriverFactory.createDriver(browser);
        driver = WebDriverFactory.getDriver();
        pageObjectManager = new PageObjectManager(driver);
        context.setAttribute("pageObjectManager", pageObjectManager);
        context.setAttribute("driver", driver);
        driver.manage().window().maximize();

        LoggerUtil.logInfo("WebDriver setup complete");
    }

    @AfterMethod
    public void teardown() {
        LoggerUtil.logInfo("Tearing down WebDriver");

        if (driver != null) {
            WebDriverFactory.quitDriver();
        }
        LoggerUtil.logInfo("WebDriver teardown complete");
    }
}
