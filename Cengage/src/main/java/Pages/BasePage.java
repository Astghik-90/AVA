package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //waiters
    public void waitElementToBeLocated (By elementBy){
        wait.until(ExpectedConditions.presenceOfElementLocated(elementBy));
    }
    public void waitToBeClickable (By elementBy){
        wait.until(ExpectedConditions.elementToBeClickable(elementBy));
    }

    public void waitToBeVisible (By elementBy){
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementBy));
    }

    public void wait(int delay){
        try{
            Thread.sleep(delay);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //click
    public void click(By elementBy){
        waitToBeClickable(elementBy);
        driver.findElement(elementBy).click();
    }

    //enter a text
    public void enterText (By elementBy, String text){
        waitElementToBeLocated(elementBy);
        driver.findElement(elementBy).sendKeys(text);
    }

    //get the text of the element
    public String readText (By elementBy){
        waitElementToBeLocated(elementBy);
        return driver.findElement(elementBy).getText();
    }

    //presence of element
    public boolean isElementPresent(By elementBy){
        boolean present;
        try{
            driver.findElement(elementBy);
            present = true;
        }catch(NoSuchElementException e){
            present = false;
        }
        return present;
    }
}
