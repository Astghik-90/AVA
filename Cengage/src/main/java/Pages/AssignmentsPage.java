package Pages;

import Locators.AssignmentsPageConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class AssignmentsPage extends BasePage {

    private By newAssignmentButton = By.cssSelector(AssignmentsPageConstants.newAssignmentButton);

    private By filterAll = By.xpath(AssignmentsPageConstants.filterAll);

    private By filterActive = By.xpath(AssignmentsPageConstants.filterActive);

    private By filterExpired = By.xpath(AssignmentsPageConstants.filterExpired);

    private By selectedFilterOption = By.className(AssignmentsPageConstants.selectedFilterOption);

    private By courseDDMenu = By.id(AssignmentsPageConstants.courseDDMenu);

    private By courseList = By.cssSelector(AssignmentsPageConstants.courseList);

    private By assignmentName = By.cssSelector(AssignmentsPageConstants.assignmentName);

    private By courses = By.cssSelector(AssignmentsPageConstants.courses);

    private By endDate = By.cssSelector(AssignmentsPageConstants.endDate);

    private By manualGrading = By.cssSelector(AssignmentsPageConstants.manualGrading);

    private By assignmentsListPerPage = By.cssSelector(AssignmentsPageConstants.dataRowListPerPage);

    private By availablePageList = By.cssSelector(AssignmentsPageConstants.availablePageList);

    private By perPageSelector = By.cssSelector(AssignmentsPageConstants.perPageSelector);

    private By perPageSelectorOptions = By.cssSelector(AssignmentsPageConstants.perPageSelectorOptions);


    public AssignmentsPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getFilterAllOption(){
        return driver.findElement(filterAll);
    }

    public WebElement getSelectedFilterOption(){
        return driver.findElement(selectedFilterOption);
    }

    public void displayAllAssignments() {
        click(filterAll);
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

    public List<WebElement> listOfAllAssignments() {
        List<WebElement> allAssignmentsList = new ArrayList<>();
        for (int i = 0; i < amountOfPages(); i++) {
            allAssignmentsList.addAll(driver.findElements(assignmentsListPerPage));
        }
        return allAssignmentsList;
    }

    public int allAssignmentsCount(){
        selectPerPageOption(50);
        driver.findElements(availablePageList).get(amountOfPages() - 1).click();
        int assignmentsAmountOnLastPage = driver.findElements(assignmentsListPerPage).size();
        return amountOfPages()*10 + assignmentsAmountOnLastPage;
    }

}
