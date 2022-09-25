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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
        Assert.assertEquals(filterAllOptionText, selectedFilterOptionText, "Default filter is the wrong one.");

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

    @Test(description = "Verify 'Course' DD menu on the 'Assignments' page.", priority = 3)
    public void testCourseDDMenu() throws InterruptedException {
        int assignmentsNumForAllCourses = assignmentsPage.allFilteredAssignmentsCount();
        assignmentsPage.navigatePage(1);

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

    @Test(description = "Verify sorting on the 'Assignments' page.", priority = 4)
    public void testSorting() throws InterruptedException, ParseException {
        assignmentsPage.selectPerPageOption(50);
        int assignmentsNumberBeforeSorting = assignmentsPage.getAssignmentsOnThePage().size();

        //sort by assignment name Az
        List<String> assignmentNameListAZ = assignmentsPage.sortByAssignmentName();
        int assignmentsNumberAfterSorting = assignmentNameListAZ.size();
        boolean isSortedByAssignmentNameAZ = assignmentsPage.isStringsInTheListSortedAZ(assignmentNameListAZ);
        softAssert.assertTrue(isSortedByAssignmentNameAZ, "The assignments aren't sorted by assignment name from 'A to Z'");
        softAssert.assertEquals(assignmentsNumberBeforeSorting, assignmentsNumberAfterSorting, "Assignments number doesn't match before anf after sorting.");

        //sort by assignment name Za
        List<String> assignmentNameListZA = assignmentsPage.sortByAssignmentName();
        boolean isSortedZA = assignmentsPage.isStringsInTheListSortedZA(assignmentNameListZA);
        softAssert.assertTrue(isSortedZA, "THe assignments aren't sorted by assignment name from 'Z to A'");

        //sort by course name Az
        List<String> courseNameListAz = assignmentsPage.sortByCourseName();
        boolean isSortedByCourseNameAz = assignmentsPage.isStringsInTheListSortedAZ(courseNameListAz);
        softAssert.assertTrue(isSortedByCourseNameAz, "The assignments aren't sorted by course name from 'A to Z'");

        //sort by course name Za
        List<String> courseNameListZa = assignmentsPage.sortByCourseName();
        boolean isSortedByCourseNameZa = assignmentsPage.isStringsInTheListSortedZA(courseNameListZa);
        softAssert.assertTrue(isSortedByCourseNameZa, "The assignments aren't sorted by course name from 'Z to a'");

        //sort by endDate Az
        List<String> endDateListAZ = assignmentsPage.sortByEndDate();
        boolean isSortedByEndDateAZ = assignmentsPage.isDatesInTheListSortedAZ(endDateListAZ);
        softAssert.assertTrue(isSortedByEndDateAZ, "The assignments aren't sorted by end date from 'A to Z'");

        //sort by course name Za
        List<String> endDateListZA = assignmentsPage.sortByEndDate();
        boolean isSortedByEndDateZA = assignmentsPage.isDatesInTheListSortedZA(endDateListZA);
        softAssert.assertTrue(isSortedByEndDateZA, "The assignments aren't sorted by end date from 'Z to A'");

        //sort by Manual Grading questions number Az
        List<Integer> mmListSorted = assignmentsPage.sortByManualGrading();
        boolean isSortedByManualGrading = assignmentsPage.isIntegersListSorted(mmListSorted);
        softAssert.assertTrue(isSortedByManualGrading, "The assignments aren't sorted by Manual Grading from 'A to Z'");

        //sort by Manual Grading questions number Za
        List<Integer> mmListReverseSorted = assignmentsPage.sortByManualGrading();
        boolean isReverseSortedByManualGrading = assignmentsPage.isIntegersListReverseSorted(mmListReverseSorted);
        softAssert.assertTrue(isReverseSortedByManualGrading, "The assignments aren't sorted by Manual Grading from 'Z to a'");

        softAssert.assertAll();
    }

}

