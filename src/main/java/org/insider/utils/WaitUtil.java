package org.insider.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// WaitUtil class provides utility methods to perform various wait operations in WebDriver.
public class WaitUtil {
    // Define a default wait duration, which will be used in the wait conditions.
    private static final Duration DEFAULT_WAIT_SECONDS = ConfigUtil.getDefaultWaitTimeout();

    // Waits for an element to be clickable.
    public static void waitForElementToBeClickable(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, DEFAULT_WAIT_SECONDS).until(ExpectedConditions.elementToBeClickable(element));
    }

    // Waits for an element to be visible.
    public static void waitForElementToBeVisible(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, DEFAULT_WAIT_SECONDS).until(ExpectedConditions.visibilityOf(element));
    }

    // Waits for an element to be visible by its locator.
    public static void waitForElementToBeVisible(WebDriver driver, By by) {
        new WebDriverWait(driver, DEFAULT_WAIT_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    // Waits for an element to be present in the DOM, not necessarily visible.
    public static void waitForElementLocated(WebDriver driver, By by) {
        new WebDriverWait(driver, DEFAULT_WAIT_SECONDS).until(ExpectedConditions.presenceOfElementLocated(by));
    }
}
