package Pages;

import Locators.AssignmentsPageConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class AssignmentsPage extends BasePage {

    @FindBy(css = AssignmentsPageConstants.newAssignmentButton)
    private WebElement newAssignmentButton;

    @FindBy(xpath = AssignmentsPageConstants.filterAll)
    private WebElement filterAll;

    @FindBy(xpath = AssignmentsPageConstants.filterActive)
    private WebElement filterActive;

    @FindBy(xpath = AssignmentsPageConstants.filterExpired)
    private WebElement filterExpired;

    @FindBy(id = AssignmentsPageConstants.courseDDMenu)
    private WebElement courseDDMenu;

    @FindBy(css = AssignmentsPageConstants.courseList)
    private WebElement courseList;

    @FindBy(css = AssignmentsPageConstants.assignmentName)
    private WebElement assignmentName;

    @FindBy(css = AssignmentsPageConstants.courses)
    private WebElement courses;

    @FindBy(css = AssignmentsPageConstants.endDate)
    private WebElement endDate;

    @FindBy(css = AssignmentsPageConstants.manualGrading)
    private WebElement manualGrading;

    @FindBy(css = AssignmentsPageConstants.dataRowListPerPage)
    private List<WebElement> assignmentsListPerPage;

    @FindBy(css = AssignmentsPageConstants.availablePageList)
    private List<WebElement> availablePageList;

    @FindBy(css = AssignmentsPageConstants.perPageSelector)
    private WebElement perPageSelector;

    @FindBy(css = AssignmentsPageConstants.perPageSelectorOptions)
    private List<WebElement> perPageSelectorOptions;

    public AssignmentsPage(WebDriver driver) {
        super(driver);
    }

    public void displayAllAssignments() {
        click(filterAll);
    }

    public void selectPerPageOption(int perPageOption) {
        click(perPageSelector);
        perPageSelectorOptions.get(perPageOption / 10 - 1).click();
    }

    public int amountOfPages() {
        return availablePageList.size();
    }

    public void navigatePage(int number) {
        if (number <= amountOfPages()) {
            click(availablePageList.get(number - 1));
        } else {
            System.out.println("Entered page is not found");
        }
    }

    public List<WebElement> listOfAllAssignments() {
        List<WebElement> allAssignmentsList = new ArrayList<>();
        for (int i = 0; i < amountOfPages(); i++) {
            allAssignmentsList.addAll(assignmentsListPerPage);
        }
        return allAssignmentsList;
    }

    public int allAssignmentsCount(){
        selectPerPageOption(50);
        click(availablePageList.get(amountOfPages() - 1));
        int assignmentsAmountOnLastPage = assignmentsListPerPage.size();
        return amountOfPages()*10 + assignmentsAmountOnLastPage;
    }

}
