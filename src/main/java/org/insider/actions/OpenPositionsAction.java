package org.insider.actions;

import org.insider.enums.Department;
import org.insider.enums.Location;
import org.insider.pages.OpenPositionsPage;
import org.insider.utils.JSUtil;
import org.insider.utils.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class OpenPositionsAction extends CommonActions {
    OpenPositionsPage openPositionsPage = new OpenPositionsPage(driver);
    public OpenPositionsAction(WebDriver driver) {
        super(driver);
    }

    /**
     * Verifies the Lever application form for the specified department and location.
     * The method first selects the expected department and location, then iterates
     * through all the links within the jobs list. For each link, it reselects the
     * location (to avoid stale errors) and checks the individual job for compliance
     * with the expected department and location.
     *
     * @param expectedDepartment The expected department to be selected in the form.
     * @param expectedLocation   The expected location to be selected in the form.
     */
    public void verifyLeverApplicationForm(Department expectedDepartment, Location expectedLocation) {
        openPositionsPage.selectDepartment(expectedDepartment);
        openPositionsPage.selectLocation(expectedLocation);
        int numberOfLinks = openPositionsPage.jobsList.findElements(By.tagName("a")).size();

        for (int i=0; i < numberOfLinks; i++) {
            openPositionsPage.selectLocation(expectedLocation);
            List<WebElement> viewRoleLinks = openPositionsPage.jobsList.findElements(By.tagName("a"));
            checkIndividualJob(viewRoleLinks.get(i), expectedDepartment, expectedLocation);
        }
    }

    // Verify that position details match the expected department and location
    public void verifyPositionDetails(Department expectedDepartment, Location expectedLocation) {
        List<WebElement> positionItems = driver.findElements(By.className("position-list-item"));

        for (WebElement positionItem : positionItems) {
            String department = positionItem.findElement(By.className("position-department")).getText();
            String location = positionItem.findElement(By.className("position-location")).getText();
            Assert.assertEquals(department, expectedDepartment.getValue(), "Department does not match the expected value.");
            Assert.assertEquals(location, expectedLocation.getValue(), "Location does not match the expected value.");
        }
    }

    // Check individual job details including department and location
    public void checkIndividualJob(WebElement viewRoleLink, Department expectedDepartment, Location expectedLocation) {
        JSUtil.removeTargetAttribute(driver, viewRoleLink);
        checkJobDetails(expectedDepartment, expectedLocation);
        driver.navigate().back();
        WaitUtil.waitForElementToBeVisible(driver, openPositionsPage.jobsList);
    }

    // Verify the details of a specific job, such as department and location
    private void checkJobDetails(Department expectedDepartment, Location expectedLocation) {
        verifyBaseUrl("https://jobs.lever.co/useinsider/");
        WebElement postingCategories = driver.findElement(By.className("posting-categories"));
        checkContainsText(postingCategories.findElement(By.className("department")), expectedDepartment.getValue().toUpperCase());
        checkContainsText(postingCategories.findElement(By.className("location")), expectedLocation.getValue().toUpperCase());
        Assert.assertNotNull(driver.findElement(By.linkText("APPLY FOR THIS JOB")));
    }
}
