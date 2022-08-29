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
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
    public static void initWebDriver() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Admin/WorkSpace/AVA/Cengage/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void recordFailure (ITestResult result){
        if(ITestResult.FAILURE == result.getStatus())
        {
            var camera = (TakesScreenshot)driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            try{
                Random rn =new Random();
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
                System.out.println("lllllll"+ LocalDateTime.now());
                Files.move(screenshot, new File("resources/screenshots/" + result.getName() + timeStamp + ".png"));
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

//    @AfterClass
//    public static void closeBrowser() {
//        WebElement username = driver.findElement(By.className("username"));
//        username.click();
//        WebElement logOut = driver.findElement(By.className("logout-button"));
//        logOut.click();
//        WebElement wait = new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#menu-item-2887>a")));
//        driver.quit();
//    }

}
