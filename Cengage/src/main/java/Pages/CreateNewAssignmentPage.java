package Pages;

import Locators.NewAssignmentPageConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CreateNewAssignmentPage extends BasePage{

    //Step1: Assignment information
     private By title = By.cssSelector(NewAssignmentPageConstants.title);
     private By continueCreateButton = By.cssSelector(NewAssignmentPageConstants.continueCreateButton);

    //Step 2: Content selection
    private By addCourseButton = By.cssSelector(NewAssignmentPageConstants.addCourseButton);
    private By groupDDMenuItems = By.cssSelector(NewAssignmentPageConstants.groupDDMenuItems);
    private By expandCourseUnitsButtons = By.cssSelector(NewAssignmentPageConstants.expandCourseUnitsButtons);
    private By courseTitleInDDMenu = By.cssSelector(NewAssignmentPageConstants.courseTitleInDDMenu);
    private By selectedCourses = By.className(NewAssignmentPageConstants.selectedCourses);
    private By selectUnitToggles = By.id(NewAssignmentPageConstants.selectUnitToggles);

    //Step 2: Content selection
    private By allStudentsCheckbox = By.id(NewAssignmentPageConstants.allStudentsCheckbox);
    private By selectStudentCheckboxes = By.cssSelector(NewAssignmentPageConstants.selectStudentCheckboxes);


    public CreateNewAssignmentPage(WebDriver driver) {
        super(driver);
    }

    public void enterAssignmentTitle(String text){
        enterText(title, text);
    }

    public void continueCreate(){
        click(continueCreateButton);
    }

    public List<WebElement> clickAddCourseButton(){
        click(addCourseButton);
        return driver.findElements(groupDDMenuItems);
    }

    public boolean selectCourseFromDDMenu(List<WebElement> list, int courseNumber){
        WebElement course = list.get(courseNumber-1);
        String courseTitle = course.getText();
        course.click();

        waitToBeVisible(selectedCourses);

        return isCourseSelected(courseTitle);
    }

    public boolean isCourseSelected(String courseTitle){
        List<WebElement> courses = driver.findElements(selectedCourses);
        List<String> selectedCourseTitles = new ArrayList<>();
        for (int i = 0; i < courses.size(); i++) {
            selectedCourseTitles.add(courses.get(i).getText());
        }
        return selectedCourseTitles.contains(courseTitle);
    }

    public void expandUnits(int courseNumber){
        List<WebElement> courses = driver.findElements(expandCourseUnitsButtons);
        courses.get(courseNumber-1).click();
    }

    public boolean selectUnit(int unitNum){
        List<WebElement> unitsCheckboxes = driver.findElements(selectUnitToggles);
        unitsCheckboxes.get(unitNum-1).click();
        return unitsCheckboxes.get(unitNum-1).isSelected();
    }

    public boolean isContinueCreateButtonActive(){
        WebElement button = driver.findElement(continueCreateButton);
        return button.isEnabled();
    }

    public boolean selectStudent(int studentNum){
        List<WebElement> studentList = driver.findElements(selectStudentCheckboxes);
        studentList.get(studentNum-1).click();
        return studentList.get(studentNum-1).isSelected();
    }
}
