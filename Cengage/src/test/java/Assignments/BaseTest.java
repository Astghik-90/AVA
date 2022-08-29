package Assignments;

import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Random;

public class BaseTest {
    public static WebDriver driver;

    @BeforeClass
    public static void initWebDriver() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", "C:/Users/ayvaz/Downloads/chromedriver.exe");
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
