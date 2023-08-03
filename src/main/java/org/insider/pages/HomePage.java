package org.insider.pages;

import org.insider.utils.ConfigUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// HomePage class encapsulates the interactions with the home page of the website
public class HomePage extends BasePage {

    // URL of the Insider home page
    private static final String HOME_URL = ConfigUtil.getBaseURL();

    // WebElement representing the "Company" link on the home page
    @FindBy(linkText = "Company")
    private WebElement companyLink;

    // WebElement representing the "Careers" link under the "Company" section on the home page
    @FindBy(linkText = "Careers")
    private WebElement careersLink;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initializing web elements
    }

    // Method to navigate to the home page using the predefined URL
    public void navigateToHomePage() {
        driver.get(HOME_URL);
    }

    // Method to navigate to the careers page by clicking on the "Company" from menu followed by the "Careers"
    public void navigateToCareersPage() {
        this.companyLink.click(); // Clicking on the "Company" from menu
        this.careersLink.click(); // Clicking on the "Careers" from menu
    }

    // Method to verify if the URL of the current page matches the home page URL
    public void verifyPageURL() {
        super.verifyPageURL(HOME_URL); // Verifying the URL using the method from the BasePage class
    }
}
