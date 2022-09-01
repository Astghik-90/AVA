package Pages;

import Locators.AssignmentsPageConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.text.SimpleDateFormat;

public class AssignmentsPage extends BasePage {

    private By newAssignmentButton = By.cssSelector(AssignmentsPageConstants.newAssignmentButton);

    private By filter = By.xpath(AssignmentsPageConstants.filter);

    private By filterAll = By.xpath(AssignmentsPageConstants.filterAll);

    private By filterActive = By.xpath(AssignmentsPageConstants.filterActive);

    private By filterExpired = By.xpath(AssignmentsPageConstants.filterExpired);

    private By selectedFilter = By.className(AssignmentsPageConstants.selectedFilter);

    private By courseDDMenu = By.id(AssignmentsPageConstants.courseDDMenu);

    private By courseList = By.cssSelector(AssignmentsPageConstants.courseList);

    private By assignmentName = By.cssSelector(AssignmentsPageConstants.assignmentName);

    private By courses = By.cssSelector(AssignmentsPageConstants.courses);

    private By endDate = By.cssSelector(AssignmentsPageConstants.endDate);

    private By manualGrading = By.cssSelector(AssignmentsPageConstants.manualGrading);

    private By assignmentsListPerPage = By.cssSelector(AssignmentsPageConstants.dataRowListPerPage);

    private By assignmentRowEndDate = By.cssSelector(AssignmentsPageConstants.assignmentRowEndDate);

    private By availablePageList = By.cssSelector(AssignmentsPageConstants.availablePageList);

    private By perPageSelector = By.cssSelector(AssignmentsPageConstants.perPageSelector);

    private By perPageSelectorOptions = By.cssSelector(AssignmentsPageConstants.perPageSelectorOptions);


    public AssignmentsPage(WebDriver driver) {
        super(driver);
    }


    public String getTextOfAllTab(){
        return readText(filterAll);
    }

    public String getTextOfActiveTab(){
        return readText(filterActive);
    }

    public String getTextOfExpiredTab(){
        return readText(filterExpired);
    }

    public String getTextOfSelectedTab(){
        return readText(selectedFilter);
    }

    public boolean isFilterPresent(){
        return isElementPresent(filter);
    }

    public void selectAllFromFilter(){
        click(filterAll);
    }

    public void selectActiveFromFilter(){
        click(filterActive);
    }

    public void selectExpiredFromFilter(){
        click(filterExpired);
    }

    public boolean isAssignmentActive() throws ParseException {
        boolean isActive;
        String endDateText = readText(assignmentRowEndDate);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy, HH:mm");
        Date endDate = formatter.parse(endDateText);
        Date today = new Date();
        if(readText(assignmentRowEndDate)=="")
            isActive = true;
            if(endDate.before(today)){
                isActive = true;
            }else{
                isActive = false;
            }
            return isActive;
    }

    public void selectPerPageOption(int perPageOption) {
        if(perPageOption==10 || perPageOption==20 || perPageOption==30 || perPageOption==40 || perPageOption==50){
            click(perPageSelector);
            driver.findElements(perPageSelectorOptions).get(perPageOption / 10 - 1).click();
        }else{
            System.out.println("Invalid option");
        }
    }

    public int amountOfPages() {
        return driver.findElements(availablePageList).size();
    }

    public void navigatePage(int number) {
        if (number <= amountOfPages()) {
            driver.findElements(availablePageList).get(number - 1).click();
        } else {
            System.out.println("Entered page is not found");
        }
    }

    public List<WebElement> listOfAllFilteredAssignments() throws InterruptedException {
        waitElementToBeLocated(assignmentsListPerPage);
        List<WebElement> allFilteredAssignmentsList = driver.findElements(assignmentsListPerPage);
        for (int i = 1; i < amountOfPages(); i++) {
            navigatePage(i+1);
            wait(3000);
            allFilteredAssignmentsList.addAll(driver.findElements(assignmentsListPerPage));

        }
        return allFilteredAssignmentsList;
    }

    public int allFilteredAssignmentsCount(){
        selectPerPageOption(50);
        driver.findElements(availablePageList).get(amountOfPages() - 1).click();
        int assignmentsAmountOnLastPage = driver.findElements(assignmentsListPerPage).size();
        return amountOfPages()*10 + assignmentsAmountOnLastPage;
    }

    public void findChildElement(){
//        List<WebElement> listOfAllFilteredAssignments = listOfAllFilteredAssignments();
        System.out.println(driver.findElements(assignmentsListPerPage).get(0).findElement(assignmentRowEndDate).getText());
    }

}
