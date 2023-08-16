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
    public WebElement jobsList;

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

    // Create the dynamic XPath for location or department option
    private String createFilterOptionXpath(String dynamicValue, String value) {
        return "//li[contains(@id, '" + dynamicValue + "') and text()='" + value + "']";
    }
}
