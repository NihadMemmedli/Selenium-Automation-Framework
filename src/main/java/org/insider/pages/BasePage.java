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
     * Waits for the given element to be clickable and then clicks on it.
     * @param element The WebElement to be clicked.
     */
    public void clickElement(WebElement element) {
        WaitUtil.waitForElementToBeClickable(driver, element);
        element.click();
    }
}
