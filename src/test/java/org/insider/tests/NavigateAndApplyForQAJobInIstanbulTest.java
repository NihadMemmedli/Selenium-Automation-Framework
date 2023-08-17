package org.insider.tests;

import org.insider.actions.CareersActions;
import org.insider.actions.CommonActions;
import org.insider.actions.OpenPositionsAction;
import org.insider.base.BaseTest;
import org.insider.enums.Department;
import org.insider.enums.Location;
import org.insider.pages.*;
import org.testng.Assert;
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
        homePage.navigateToHomePage();
        extentTest.info("Navigated to the home page");

        commonActions.verifyHomePageURL();
        extentTest.info("Verified home page URL");

        homePage.acceptAllCookies();
        extentTest.info("Accepted all cookies");

        homePage.navigateToCareersPage();
        extentTest.info("Navigated to Careers page");

        careersActions.verifyCareersPage();
        extentTest.info("Verified Careers page details");

        careersPage.navigateToSpecificTeamPage(Department.QUALITY_ASSURANCE);
        extentTest.info("Navigated to the Quality Assurance team page");

        commonActions.verifyPageURL("https://useinsider.com/careers/quality-assurance/");
        extentTest.info("Verified QA team page URL");

        teamPage.openAllJobs();
        extentTest.info("Opened all jobs for Quality Assurance team");

        commonActions.verifyPageURL("https://useinsider.com/careers/open-positions/?department=qualityassurance");
        extentTest.info("Verified URL after opening all jobs");

        openPositionsAction.verifyPositionDetails(Department.QUALITY_ASSURANCE, Location.ISTANBUL);
        extentTest.info("Verified position details for QA in Istanbul");

        openPositionsAction.verifyLeverApplicationForm(Department.QUALITY_ASSURANCE, Location.ISTANBUL);
        extentTest.info("Verified the application form for QA in Istanbul");
    }

    @Test(groups = {"fail"})
    public void testWillBeFailed() {
        homePage.navigateToHomePage();
        extentTest.info("Navigated to the home page");

        commonActions.verifyHomePageURL();
        extentTest.info("Verified home page URL");

        homePage.acceptAllCookies();
        extentTest.info("Accepted all cookies");

        homePage.navigateToCareersPage();
        extentTest.info("Navigated to Careers page");

        careersActions.verifyCareersPage();
        extentTest.info("Verified Careers page details");

        Assert.assertEquals(true, false);
        extentTest.fail("Forced test failure");
    }

    @Test(groups = {"Tag1"})
    public void testWithTag() {
        homePage.navigateToHomePage();
        extentTest.info("Navigated to the home page");

        commonActions.verifyHomePageURL();
        extentTest.info("Verified home page URL");

        homePage.acceptAllCookies();
        extentTest.info("Accepted all cookies");

        homePage.navigateToCareersPage();
        extentTest.info("Navigated to Careers page");

        careersActions.verifyCareersPage();
        extentTest.info("Verified Careers page details");
    }
}