package Pages;

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

    public void waitVisibilityOf (WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitToBeClickable (WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void click(WebElement element){
        waitToBeClickable(element);
        element.click();
    }

    public void enterText (WebElement element, String text){
        waitVisibilityOf(element);
        element.sendKeys(text);
    }

    public String readText (WebElement element){
        waitVisibilityOf(element);
        return element.getText();
    }
}
