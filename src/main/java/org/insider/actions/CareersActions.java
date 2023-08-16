package org.insider.actions;

import org.insider.pages.CareersPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static org.insider.pages.CareersPage.Careers_URL;

public class CareersActions extends CommonActions
{
    CareersPage careersPage = new CareersPage(driver);
    public CareersActions(WebDriver driver) {
        super(driver);
    }

    // Method to validate the correct loading of the Careers Page including its main sections
    public void verifyCareersPage() {
        verifyPageURL(Careers_URL);
        Assert.assertTrue(careersPage.isPageLoaded());
        Assert.assertTrue(careersPage.isTeamsSectionOpened());
        Assert.assertTrue(careersPage.isLocationsSectionOpened());
        Assert.assertTrue(careersPage.isLifeAtInsiderSectionOpened());
    }
}
