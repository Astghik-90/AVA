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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class AssignmentsPageTest extends BaseTest {

    @Test(priority = 0)
    public void testIfFilerExists(){
        homePage = new HomePage(driver);
        homePage.navigateHomePage();
//        homePage.cookiePopUpAccept();
        dashboard = homePage.logIn("teacher_reg", "Password1");
        assignmentsPage = dashboard.navigateAssignmentsPage();
        Assert.assertTrue(assignmentsPage.isFilterPresent(), "The filter is missing.");
    }

    @Test(priority = 1, dependsOnMethods = "testIfFilerExists")
    public void testDefaultValueOfFilter() {
        String filterAllOptionText = assignmentsPage.getTextOfAllTab();
        String selectedFilterOptionText = assignmentsPage.getTextOfSelectedTab();
        Assert.assertEquals(filterAllOptionText, selectedFilterOptionText);
    }

    @Test(priority = 2, dependsOnMethods = "testIfFilerExists")
    public void testActiveTab(){
        assignmentsPage.selectActiveFromFilter();

    }
    @Test(dependsOnMethods = "testIfFilerExists")
    public void test() throws InterruptedException {
        System.out.println("HI");
        System.out.println(assignmentsPage.listOfAllFilteredAssignments().size());
        System.out.println("bye");

    }

    @Test(dependsOnMethods = "testIfFilerExists")
    public void testT() throws InterruptedException {
        boolean isActive;
        for(WebElement assignmentRow: assignmentsPage.listOfAllFilteredAssignments()){
            WebElement endDate = assignmentRow.findElement(By.cssSelector("[class*='simple-row-cel']:nth-child(3)");
            if(assignmentsPage.isAssignmentActive(endDate)){

            }
        }
    }

//    @Test
//    public void clickTest() throws InterruptedException {
//        driver.get("https://web-cen-unity-stage.avallain.net");
//        try {
//            driver.findElement(By.cssSelector("[data-qa-id='button']")).click();
//        } catch (Exception exception) {
//            System.out.println("No cookie pop-up");
//        }
//        WebElement signIn = driver.findElement(By.cssSelector("[data-qa-id='login_sing-in_button']"));
//        signIn.click();
//        WebElement username = driver.findElement(By.cssSelector("[data-qa-id='login_username_input']"));
//        username.sendKeys("ava_qa_teachy");
//        WebElement pass = driver.findElement(By.cssSelector("[data-qa-id='login_password_input']"));
//        pass.sendKeys("Password1");
////        driver.findElement(By.cssSelector("[data-qa-id='login_remember-me_checkbox']")).click();
//        WebElement wait1 = new WebDriverWait(driver, Duration.ofSeconds(20))
//                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-qa-id='login_submit_button']")));
//        System.out.println(driver.findElement(By.cssSelector("[data-qa-id='login_submit_button']")).isEnabled());
//        WebElement signInSubmit = driver.findElement(By.cssSelector("[data-qa-id='login_submit_button']"));
//        signInSubmit.click();
//        WebElement wait2 = new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-qa-id='navigation_assignments_link']")));
//        WebElement assignments = driver.findElement(By.cssSelector("[data-qa-id='navigation_assignments_link']"));
//        assignments.click();
//        WebElement filter = driver.findElement(By.xpath("//div[@class='filter']/div/div[1][1]"));
//        driver.findElement(By.xpath("//div[@class='filter']/div/div[3]")).click();
//        Thread.sleep(5000);
//        driver.findElement(By.xpath("//div[@class='filter']/div/div[2]")).click();
//        Thread.sleep(3000);
//        WebElement courseSelector = driver.findElement(By.id("dropdown-container"));
////        courseSelector.click();
//        driver.findElement(By.cssSelector("[for='groupsName']")).click();
//
//    }
}

