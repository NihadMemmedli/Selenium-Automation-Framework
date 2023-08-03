package org.insider.pages;

import org.insider.utils.WaitUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class BasePage {

    protected WebDriver driver;

    // Web element to handle cookie acceptance on the page
    @FindBy(linkText = "Accept All")
    private WebElement acceptCookiesButton;

    public BasePage(WebDriver driver) {
        // Initializing web elements within the page class
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Clicks on the "Accept All" button to accept cookies on the page.
     */
    public void acceptAllCookies() {
        acceptCookiesButton.click();
    }

    /**
     * Verifies if the current URL of the page matches the expected URL.
     * @param url The expected URL to verify against the current URL.
     */
    public void verifyPageURL(String url) {
        Assert.assertEquals(driver.getCurrentUrl(), url);
    }

    /**
     * Verifies if the current URL of the page starts with the expected base URL.
     * @param expectedBaseUrl The expected base URL to verify against the start of the current URL.
     */
    public void verifyBaseUrl(String expectedBaseUrl) {
        // Get the current URL from the browser
        String currentUrl = driver.getCurrentUrl();
        // Check if the current URL starts with the expected base URL
        if (!currentUrl.startsWith(expectedBaseUrl)) {
            Assert.fail("The current URL does not start with the expected base URL. Expected: " + expectedBaseUrl + ", but got: " + currentUrl);
        }
    }

    /**
     * Waits for the given element to be clickable and then clicks on it.
     * @param element The WebElement to be clicked.
     */
    public void clickElement(WebElement element) {
        WaitUtil.waitForElementToBeClickable(driver, element);
        element.click();
    }

    /**
     * Checks if the given element's text contains the expected text.
     * @param element The WebElement whose text is to be checked.
     * @param expectedText The text that should be contained within the element's text.
     */
    public void checkContainsText(WebElement element, String expectedText) {
        Assert.assertTrue(element.getText().contains(expectedText), "Actual text: " + element.getText() + " is not equal to expectedText: " + expectedText);
    }
}
