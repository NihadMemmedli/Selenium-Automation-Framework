package org.insider.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// TeamPage class encapsulates the interactions with the team page of the website
public class TeamPage extends BasePage {

    // WebElement representing the header section of the page
    @FindBy(id = "page-head")
    private WebElement pageHeadSection;

    // Constructor to initialize WebDriver and set up PageFactory for the web elements
    public TeamPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initializing web elements
    }

    // Method to click the "All Jobs" button located within the page head section
    public void openAllJobs(){
        // Finding the anchor element (link) within the page head section
        WebElement allJobsButton = pageHeadSection.findElement(By.tagName("a"));
        allJobsButton.click(); // Clicking the "All Jobs" button to open all jobs
    }

}
