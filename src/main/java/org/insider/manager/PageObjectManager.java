package org.insider.manager;

import org.insider.actions.CareersActions;
import org.insider.actions.CommonActions;
import org.insider.actions.OpenPositionsAction;
import org.insider.pages.*;
import org.openqa.selenium.WebDriver;

// The PageObjectManager class acts as a factory for all the Page Objects used in the tests.
// It ensures that only one instance of a Page Object is created during the entire test execution.
public class PageObjectManager {

    private final WebDriver driver;
    private CareersPage careersPage;
    private HomePage homePage;
    private OpenPositionsPage openPositionsPage;
    private TeamPage teamPage;

    private CommonActions commonActions;
    private OpenPositionsAction openPositionsAction;
    private CareersActions careersActions;

    // Constructor that takes the WebDriver instance as an argument.
    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    // Getter method for the CareersPage. Creates the object if not already created.
    public CareersPage getCareersPage() {
        if (careersPage == null) {
            careersPage = new CareersPage(driver);
        }
        return careersPage;
    }

    // Getter method for the HomePage. Creates the object if not already created.
    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage(driver);
        }
        return homePage;
    }


    // Getter method for the OpenPositionsPage. Creates the object if not already created.
    public OpenPositionsPage getOpenPositionsPage() {
        if (openPositionsPage == null) {
            openPositionsPage = new OpenPositionsPage(driver);
        }
        return openPositionsPage;
    }

    // Getter method for the TeamPage. Creates the object if not already created.
    public TeamPage getTeamPage() {
        if (teamPage == null) {
            teamPage = new TeamPage(driver);
        }
        return teamPage;
    }

    public OpenPositionsAction getOpenPositionsAction() {
        if (openPositionsAction == null) {
            openPositionsAction = new OpenPositionsAction(driver);
        }
        return openPositionsAction;
    }

    public CommonActions getCommonActions() {
        if (commonActions == null) {
            commonActions = new CommonActions(driver);
        }
        return commonActions;
    }

    public CareersActions getCareersActions() {
        if (careersActions == null) {
            careersActions = new CareersActions(driver);
        }
        return careersActions;
    }
}
