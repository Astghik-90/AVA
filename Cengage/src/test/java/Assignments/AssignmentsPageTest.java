package Assignments;

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
    public void testDefaultValueOfFilter() {
        homePage = new HomePage(driver);
        homePage.navigateHomePage();
//        homePage.cookiePopUpAccept();
        dashboard = homePage.logIn("teacher_reg", "Password1");
        assignmentsPage = dashboard.navigateAssignmentsPage();
        String filterAllOptionText = assignmentsPage.getFilterAllOption().getText();
        String selectedFilterOptionText = assignmentsPage.getSelectedFilterOption().getText();
        Assert.assertEquals(filterAllOptionText, selectedFilterOptionText);

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

