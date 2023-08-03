package org.insider.pages;

import org.insider.enums.Department;
import org.insider.utils.ConfigUtil;
import org.insider.utils.JSUtil;
import org.insider.utils.WaitUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

// The CareersPage class encapsulates the interactions with the careers page of the website.
public class CareersPage extends BasePage {

    private static final String Careers_URL = ConfigUtil.getBaseURL() + "careers/";

    // WebElements related to different sections on the Careers Page
    @FindBy(id = "page-head")
    private WebElement pageHeadSection;
    @FindBy(xpath = "//h1[contains(text(), 'Ready to disrupt?')]")
    private WebElement readyToDisruptTitle;
    @FindBy(id = "career-find-our-calling")
    private WebElement findYourCallingSection;
    @FindBy(xpath = "//h3[contains(text(), 'Find your calling')]")
    private WebElement findYourCallingTitle;
    @FindBy(id = "career-our-location")
    private WebElement ourLocationSectionID;
    @FindBy(xpath = "//h3[contains(text(), 'Our Locations')]")
    private WebElement ourLocationsTitle;
    @FindBy(xpath = "//h2[contains(text(), 'Life at Insider')]")
    private WebElement lifeAtInsiderTitle;
    @FindBy(linkText = "See all teams")
    private WebElement seeAllTeams;

    public CareersPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to check if the "Life at Insider" section is visible
    public boolean isLifeAtInsiderSectionOpened() {
        return lifeAtInsiderTitle.isDisplayed();
    }

    // Method to check if the "Locations" section is visible
    public boolean isLocationsSectionOpened() {
        return ourLocationSectionID.isDisplayed() && ourLocationsTitle.isDisplayed();
    }

    // Method to check if the "Teams" section is visible
    public boolean isTeamsSectionOpened() {
        return findYourCallingSection.isDisplayed() && findYourCallingTitle.isDisplayed();
    }

    // Method to check if the main elements of the Careers Page are visible, thus indicating that the page is loaded
    public boolean isPageLoaded() {
        return pageHeadSection.isDisplayed() && readyToDisruptTitle.isDisplayed();
    }

    // Method to click on the "See all teams" link
    public void clickSeeAllTeams() {
        WaitUtil.waitForElementToBeClickable(driver, seeAllTeams);
        JSUtil.scrollIntoView(driver, this.findYourCallingTitle);
        JSUtil.clickElement(driver, this.seeAllTeams);
    }

    // Method to select a specific team by title
    public void selectTeam(String title) {
        By teamName = By.xpath("//h3[contains(text(), '" + title + "')]");
        WaitUtil.waitForElementToBeVisible(driver, teamName);
        JSUtil.scrollIntoView(driver, teamName);
        JSUtil.clickElement(driver, teamName);
    }

    // Method to validate the correct loading of the Careers Page including its main sections
    public void verifyCareersPage() {
        verifyPageURL(Careers_URL);
        Assert.assertTrue(this.isPageLoaded());
        Assert.assertTrue(this.isTeamsSectionOpened());
        Assert.assertTrue(this.isLocationsSectionOpened());
        Assert.assertTrue(this.isLifeAtInsiderSectionOpened());
    }

    // Method to navigate to a specific team page, using the Department enum
    public void navigateToSpecificTeamPage(Department teamName) {
        this.clickSeeAllTeams();
        this.selectTeam(teamName.getValue());
    }
}
