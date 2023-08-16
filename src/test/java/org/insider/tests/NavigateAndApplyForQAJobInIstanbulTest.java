package org.insider.tests;

import org.insider.actions.CareersActions;
import org.insider.actions.CommonActions;
import org.insider.actions.OpenPositionsAction;
import org.insider.base.BaseTest;
import org.insider.enums.Department;
import org.insider.enums.Location;
import org.insider.pages.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NavigateAndApplyForQAJobInIstanbulTest extends BaseTest {

    private CareersPage careersPage;
    private HomePage homePage;
    private TeamPage teamPage;
    private OpenPositionsAction openPositionsAction;
    private CommonActions commonActions;
    private CareersActions careersActions;


    @BeforeMethod
    public void setUpTest() {
        careersPage = pageObjectManager.getCareersPage();
        homePage = pageObjectManager.getHomePage();
        teamPage = pageObjectManager.getTeamPage();
        openPositionsAction = pageObjectManager.getOpenPositionsAction();
        commonActions = pageObjectManager.getCommonActions();
        careersActions = pageObjectManager.getCareersActions();
    }

    @Test()
    public void testNavigateAndApplyForQAJobInIstanbul() {
        // Navigate to the home page and verify the URL
        homePage.navigateToHomePage();
        commonActions.verifyHomePageURL();
        homePage.acceptAllCookies();

        // Navigate to the careers page and verify the page details
        homePage.navigateToCareersPage();
        careersActions.verifyCareersPage();

        // Navigate to the Quality Assurance team page
        careersPage.navigateToSpecificTeamPage(Department.QUALITY_ASSURANCE);
        commonActions.verifyPageURL("https://useinsider.com/careers/quality-assurance/");

        // Open all jobs for the Quality Assurance team
        teamPage.openAllJobs();
        commonActions.verifyPageURL("https://useinsider.com/careers/open-positions/?department=qualityassurance");

        // Verify position details for QA in Istanbul
        openPositionsAction.verifyPositionDetails(Department.QUALITY_ASSURANCE, Location.ISTANBUL);

        // Verify the application form for QA in Istanbul
        openPositionsAction.verifyLeverApplicationForm(Department.QUALITY_ASSURANCE, Location.ISTANBUL);
    }

    @Test(groups = {"fast"})
    public void testNavigateAndApplyForQAJobInIstanbul1() {
        // Navigate to the home page and verify the URL
        homePage.navigateToHomePage();
        commonActions.verifyHomePageURL();
        homePage.acceptAllCookies();

        // Navigate to the careers page and verify the page details
        homePage.navigateToCareersPage();
        careersActions.verifyCareersPage();
    }

    @Test(groups = {"fast"})
    public void testNaavigateAndApplyForQAJobInIstanbul1() {
        // Navigate to the home page and verify the URL
        homePage.navigateToHomePage();
        commonActions.verifyHomePageURL();
        homePage.acceptAllCookies();

        // Navigate to the careers page and verify the page details
        homePage.navigateToCareersPage();
        careersActions.verifyCareersPage();
    }
}