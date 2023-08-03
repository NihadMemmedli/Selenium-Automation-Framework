package org.insider.pages;

import org.insider.enums.Department;
import org.insider.enums.Location;
import org.insider.utils.JSUtil;
import org.insider.utils.WaitUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import java.util.List;

public class OpenPositionsPage extends BasePage {
    @FindBy(xpath = "//span[@id='select2-filter-by-location-container']")
    private WebElement filterByLocationContainer;

    @FindBy(xpath = "//span[@id='select2-filter-by-department-container']")
    private WebElement filterByDepartmentContainer;

    @FindBy(xpath = "//*[@id='jobs-list']")
    private WebElement jobsList;

    public OpenPositionsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Select a location from the location filter
    public void selectLocation(Location location) {
        WaitUtil.waitForElementLocated(driver, By.xpath("//*[@id='filter-by-location']/option[2]"));
        clickElement(filterByLocationContainer);
        WebElement locationOption = driver.findElement(By.xpath(createFilterOptionXpath("select2-filter-by-location-result", location.getValue())));
        locationOption.click();
        JSUtil.scrollDown(driver);
    }

    // Select a department from the department filter
    public void selectDepartment(Department department) {
        WaitUtil.waitForElementLocated(driver, By.xpath("//*[@id='filter-by-department']/option[2]"));
        clickElement(filterByDepartmentContainer);
        WebElement departmentOption = driver.findElement(By.xpath(createFilterOptionXpath("select2-filter-by-department-result", department.getValue())));
        departmentOption.click();
    }

    // Verify that the application form for the lever matches the expected department and location
    public void verifyLeverApplicationForm(Department expectedDepartment, Location expectedLocation) {
        selectDepartment(expectedDepartment);
        selectLocation(expectedLocation);
        List<WebElement> viewRoleLinks = jobsList.findElements(By.tagName("a"));

        for (WebElement viewRoleLink : viewRoleLinks) {
            selectLocation(expectedLocation);
            checkIndividualJob(viewRoleLink, expectedDepartment, expectedLocation);
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

    // Create the dynamic XPath for location or department option
    private String createFilterOptionXpath(String dynamicValue, String value) {
        return "//li[contains(@id, '" + dynamicValue + "') and text()='" + value + "']";
    }

    // Check individual job details including department and location
    private void checkIndividualJob(WebElement viewRoleLink, Department expectedDepartment, Location expectedLocation) {
        JSUtil.removeTargetAttribute(driver, viewRoleLink);
        checkJobDetails(expectedDepartment, expectedLocation);
        driver.navigate().back();
        WaitUtil.waitForElementToBeVisible(driver, jobsList);
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
