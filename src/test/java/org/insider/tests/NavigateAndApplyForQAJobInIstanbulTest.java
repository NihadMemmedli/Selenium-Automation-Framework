package org.insider.tests;

import org.insider.base.BaseTest;
import org.insider.enums.Department;
import org.insider.enums.Location;
import org.insider.pages.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NavigateAndApplyForQAJobInIstanbulTest extends BaseTest {

    private CareersPage careersPage;
    private HomePage homePage;
    private OpenPositionsPage openPositionsPage;
    private TeamPage teamPage;


    @BeforeMethod
    public void setUpTest() {
        careersPage = pageObjectManager.getCareersPage();
        homePage = pageObjectManager.getHomePage();
        openPositionsPage = pageObjectManager.getOpenPositionsPage();
        teamPage = pageObjectManager.getTeamPage();
    }

    @Test
    public void testNavigateAndApplyForQAJobInIstanbul() {
        // Navigate to the home page and verify the URL
        homePage.navigateToHomePage();
        homePage.verifyPageURL();
        homePage.acceptAllCookies();

        // Navigate to the careers page and verify the page details
        homePage.navigateToCareersPage();
        careersPage.verifyCareersPage();

        // Navigate to the Quality Assurance team page
        careersPage.navigateToSpecificTeamPage(Department.QUALITY_ASSURANCE);
        teamPage.verifyPageURL("https://useinsider.com/careers/quality-assurance/");

        // Open all jobs for the Quality Assurance team
        teamPage.openAllJobs();
        openPositionsPage.verifyPageURL("https://useinsider.com/careers/open-positions/?department=qualityassurance");

        // Verify position details for QA in Istanbul
        openPositionsPage.verifyPositionDetails(Department.QUALITY_ASSURANCE, Location.ISTANBUL);

        // Verify the application form for QA in Istanbul
        openPositionsPage.verifyLeverApplicationForm(Department.QUALITY_ASSURANCE, Location.ISTANBUL);
    }
}