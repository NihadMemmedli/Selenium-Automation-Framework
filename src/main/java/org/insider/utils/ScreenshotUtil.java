package org.insider.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import org.apache.commons.io.FileUtils;
import java.io.IOException;

public class ScreenshotUtil {

    private final WebDriver driver;

    public ScreenshotUtil(WebDriver driver) {
        this.driver = driver;
    }

    public void takeScreenshot(String testName) {
        // Taking screenshot
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Define the destination path with a unique name using the test name
        String path = System.getProperty("user.dir") + "/screenshots/" + testName + "_" + System.currentTimeMillis() + ".png";

        try {
            // Copy the screenshot to the destination
            FileUtils.copyFile(scrFile, new File(path));
            System.out.println("Screenshot saved at " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
