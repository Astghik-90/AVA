package Pages;

import Locators.AssignmentsPageConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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

    private By assignmentRowCourse = By.cssSelector(AssignmentsPageConstants.assignmentRowCourse);

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

    public void filterAllAssignments(){
        click(filterAll);
        wait(3000);
    }

    public void filterActiveAssignments(){
        click(filterActive);
        wait(3000);
    }

    public void filterExpiredAssignments(){
        click(filterExpired);
        wait(3000);
    }

    public List<WebElement> assignmentsOnThePage() throws InterruptedException {
        waitElementToBeLocated(assignmentsListPerPage);
        List<WebElement> assignmentsOnThePage = driver.findElements(assignmentsListPerPage);
        return assignmentsOnThePage;
    }

    public boolean isAssignmentActive(WebElement assignmentRow) throws ParseException {
        boolean isActive;
        WebElement endDateOfAssignment = assignmentRow.findElement(assignmentRowEndDate);
        String endDateText = readText(assignmentRowEndDate);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy, HH:mm");
        if(endDateText == "") {
            isActive = true;
        }else{
            Date endDate = formatter.parse(endDateText);
            Date today = new Date();
            if(endDate.before(today)){
                isActive = false;
            }else{
                isActive = true;
            }
        }
        return isActive;
    }

    public boolean areAssignmentsActive(List<WebElement> assignmentsList) throws ParseException {
        boolean areActive=true;
        for (int i = 0; i < assignmentsList.size(); i++) {
            if (!isAssignmentActive(assignmentsList.get(i))){
                areActive = false;
                break;
            }
        }
        return areActive;
    }

    public boolean areAssignmentsExpired(List<WebElement> assignmentsList) throws ParseException {
        boolean areExpired=true;
        for (int i = 0; i < assignmentsList.size(); i++) {
            if (isAssignmentActive(assignmentsList.get(i))){
                areExpired = false;
                break;
            }
        }
        return areExpired;
    }

    // in case there are more than 1 page available in Pagination.
    public boolean areAllFilteredAssignmentsActive() throws InterruptedException, ParseException {
        if(!areAssignmentsActive(assignmentsOnThePage())) return false;

        for (int i = 2; i < amountOfPages()+1; i++) {
            navigatePage(i);
            if(!areAssignmentsActive(assignmentsOnThePage())){
                return false;
            }
        }
       return true;
    }

    public boolean areAllFilteredAssignmentsExpired() throws InterruptedException, ParseException {
        if(!areAssignmentsExpired(assignmentsOnThePage())) return false;

        for (int i = 2; i < amountOfPages()+1; i++) {
            navigatePage(i);
            if(!areAssignmentsExpired(assignmentsOnThePage())){
                return false;
            }
        }
        return true;
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
            wait(3000);
        } else {
            System.out.println("Entered page is not found");
        }
    }

    public int allFilteredAssignmentsCount() throws InterruptedException {
        int lastPagesNum = amountOfPages();
        navigatePage(lastPagesNum);
        int assignmentsAmountOnLastPage = assignmentsOnThePage().size();
        int num = (lastPagesNum-1)*10 + assignmentsAmountOnLastPage;
        return num;
    }

    public String selectCourse(int index){
        click(courseDDMenu);
        WebElement course = driver.findElements(courseList).get(index);
        String courseName = course.getText();
        course.click();
        wait(2000);
        return courseName;
    }

    public void selectAllCourses(){
        click(courseDDMenu);
        WebElement allCourses = driver.findElements(courseList).get(0);
        allCourses.click();
        wait(2000);
    }

    public List<String> courseNames() throws InterruptedException {
        List<String> courseNames = new ArrayList<>();
        for (int i = 0; i < assignmentsOnThePage().size(); i++) {
            WebElement assignment = assignmentsOnThePage().get(i);
            String assignmentCourseName = assignment.findElement(assignmentRowCourse).getText();
            courseNames.add(assignmentCourseName);
        }
        return courseNames;
    }
}
