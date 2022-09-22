package Assignments;

import Pages.AssignmentsPage;
import Pages.BasePage;
import Pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.text.ParseException;
import java.time.Duration;
import java.util.List;

public class AssignmentsPageTest extends BaseTest {

    SoftAssert softAssert = new SoftAssert();

    @BeforeTest
    public void goToAssignmentsPage() {
        dashboard = homePage.logIn("ava_qa_teachy", "Password1");
        assignmentsPage = dashboard.navigateAssignmentsPage();
    }

    @Test(description = "Verify default filter on the 'Assignments' page.", priority = 1)
    public void testFilters() throws InterruptedException, ParseException {
        String filterAllOptionText = assignmentsPage.getTextOfAllTab();
        String selectedFilterOptionText = assignmentsPage.getTextOfSelectedTab();
        softAssert.assertEquals(filterAllOptionText, selectedFilterOptionText, "Default filter is the wrong one.");

    }

    @Test(description = "Verify filtering on the 'Assignments' page.", priority = 2)
    public void testActiveFilter() throws InterruptedException, ParseException {
        int allAssignmentsNum = assignmentsPage.allFilteredAssignmentsCount();

        assignmentsPage.filterActiveAssignments();
        boolean isActive = assignmentsPage.areAllFilteredAssignmentsActive();
        softAssert.assertTrue(isActive, "'Active' filter is not working correctly.");

        assignmentsPage.filterExpiredAssignments();
        boolean isExpired = assignmentsPage.areAllFilteredAssignmentsExpired();
        softAssert.assertTrue(isExpired, "'Expired' filter is not working correctly");

        assignmentsPage.filterAllAssignments();
        int allAssignmentsNumAfterChangingFilters = assignmentsPage.allFilteredAssignmentsCount();
        softAssert.assertEquals(allAssignmentsNum, allAssignmentsNumAfterChangingFilters, "'All' filter doesn't work when navigating to another filter and back.");
        softAssert.assertAll();
    }

    @Test(description = "Verify 'Cousre' DD menu on the 'Assignments' page.", priority = 3)
    public void testCourseDDMenu() throws InterruptedException {
        int assignmentsNumForAllCourses = assignmentsPage.allFilteredAssignmentsCount();

        String selectedCourseName = assignmentsPage.selectCourse(1);
        List<String> assignmentsCourseNames = assignmentsPage.courseNames();
        for (int i = 0; i < assignmentsCourseNames.size(); i++) {
//            System.out.println(assignmentsCourseNames.get(i));
            softAssert.assertEquals(selectedCourseName, assignmentsCourseNames.get(i), "Course doesn't match to the selected on for " + (i + 1) + " assignment");
        }

        assignmentsPage.selectAllCourses();
        int assignmentsNum = assignmentsPage.allFilteredAssignmentsCount();
        softAssert.assertEquals(assignmentsNumForAllCourses, assignmentsNum, "'All courses' filter doesn't work as expected");
        softAssert.assertAll();
    }
}

