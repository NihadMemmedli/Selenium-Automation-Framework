package org.insider.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

// JSUtil class provides utility methods to interact with web elements using JavaScript execution
public class JSUtil {

    // Scrolls the page to bring the specified element into view
    public static void scrollIntoView(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // Overloaded method to scroll into view by locating the element using a By object
    public static void scrollIntoView(WebDriver driver, By by) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(by);
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // Clicks the specified element using JavaScript
    public static void clickElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", element);
    }

    // Overloaded method to click an element by locating it using a By object
    public static void clickElement(WebDriver driver, By by) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(by);
        js.executeScript("arguments[0].click()", element);
    }

    // Removes the 'target' attribute from the specified element and clicks it
    public static void removeTargetAttribute(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].removeAttribute('target'); arguments[0].click();", element);
    }

    // Scrolls down to the bottom of the page
    public static void scrollDown(WebDriver driver) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            Thread.sleep(1000); // Adding a delay to allow the scroll action to complete
        } catch (InterruptedException e) {
            LoggerUtil.logError(e.toString());
        }
    }
}
