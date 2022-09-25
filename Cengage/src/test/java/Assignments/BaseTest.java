package Assignments;

import Pages.AssignmentsPage;
import Pages.Dashboard;
import Pages.HomePage;
import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Random;

public class BaseTest {
    protected static WebDriver driver;
    protected HomePage homePage;
    protected Dashboard dashboard;
    protected AssignmentsPage assignmentsPage;

    @BeforeClass
    public void initWebDriver() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Admin/WorkSpace/AVA/Cengage/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
        homePage.navigateHomePage();
//        homePage.cookiePopUpAccept();
    }

    @AfterMethod
    public void recordFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            var camera = (TakesScreenshot) driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            try {
                Random rn = new Random();
                Files.move(screenshot, new File("resources/screenshots/" + result.getName() + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @AfterClass
    public static void closeBrowser() {
        driver.quit();
    }

}
