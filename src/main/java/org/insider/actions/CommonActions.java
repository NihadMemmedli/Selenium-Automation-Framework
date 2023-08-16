package org.insider.actions;

import org.insider.pages.BasePage;
import org.insider.utils.ConfigUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class CommonActions extends BasePage {

    public CommonActions(WebDriver driver) {
        super(driver);
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
     * Checks if the given element's text contains the expected text.
     * @param element The WebElement whose text is to be checked.
     * @param expectedText The text that should be contained within the element's text.
     */
    public void checkContainsText(WebElement element, String expectedText) {
        Assert.assertTrue(element.getText().contains(expectedText), "Actual text: " + element.getText() + " is not equal to expectedText: " + expectedText);
    }

    /**
     * Verifies if the current URL of the page matches the expected URL.
     * @param url The expected URL to verify against the current URL.
     */
    public void verifyPageURL(String url) {
        Assert.assertEquals(driver.getCurrentUrl(), url);
    }

    public void verifyHomePageURL() {
        String HOME_URL = ConfigUtil.getBaseURL();
        Assert.assertEquals(driver.getCurrentUrl(), HOME_URL);
    }
}
