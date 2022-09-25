package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.time.Duration;
import java.util.List;
import java.util.Locale;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.text.SimpleDateFormat;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //waiters
    public void waitElementToBeLocated(By elementBy) {
        wait.until(ExpectedConditions.presenceOfElementLocated(elementBy));
    }

    public void waitToBeClickable(By elementBy) {
        wait.until(ExpectedConditions.elementToBeClickable(elementBy));
    }

    public void waitToBeVisible(By elementBy) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementBy));
    }

    public void wait(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //click
    public void click(By elementBy) {
        waitToBeClickable(elementBy);
        driver.findElement(elementBy).click();
    }

    //enter a text
    public void enterText(By elementBy, String text) {
        waitElementToBeLocated(elementBy);
        driver.findElement(elementBy).sendKeys(text);
    }

    //get the text of the element
    public String readText(By elementBy) {
        waitElementToBeLocated(elementBy);
        return driver.findElement(elementBy).getText();
    }

    //presence of element
    public boolean isElementPresent(By elementBy) {
        boolean present;
        try {
            driver.findElement(elementBy);
            present = true;
        } catch (NoSuchElementException e) {
            present = false;
        }
        return present;
    }

    public boolean isStringsInTheListSortedAZ(List<String> list) {
        if (list.isEmpty() || list.size() == 1) return true;

        for (int i = 0; i < list.size() - 1; i++) {
            String current = list.get(i).replace(" ", "").toLowerCase();
            String next = list.get(i + 1).replace(" ", "").toLowerCase();
            if (current.compareTo(next) > 0) {
                System.out.println("current: " + current + "\n" + "next: " + next);
                return false;
            }

        }
        return true;
    }

    public boolean isStringsInTheListSortedZA(List<String> list) {
        if (list.isEmpty() || list.size() == 1) return true;

        for (int i = 0; i < list.size() - 1; i++) {
            String current = list.get(i).replace(" ", "").toLowerCase();
            String next = list.get(i + 1).replace(" ", "").toLowerCase();
            if (current.compareTo(next) < 0) {
                System.out.println("current: " + current + "\n" + "next: " + next);
                return false;
            }
        }
        return true;
    }

    public boolean isDatesInTheListSortedAZ(List<String> list) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy, HH:mm");

        for (int i = 0; i < list.size() - 1; i++) {
            String current = list.get(i);
            String next = list.get(i + 1);

            Date currentRowDate = current.isEmpty() ? formatter.parse("01/Jan/3030, 00:00") : formatter.parse(current);
            Date nextRowDate = next.isEmpty() ? formatter.parse("01/Jan/3030, 00:00") : formatter.parse(next);

            if (currentRowDate.after(nextRowDate)) {
                System.out.println("current: " + current + "\n" + "next: " + next);
                return false;
            }
        }
        return true;
    }

    public boolean isDatesInTheListSortedZA(List<String> list) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy, HH:mm");

        for (int i = 0; i < list.size() - 1; i++) {
            String current = list.get(i);
            String next = list.get(i + 1);

            Date currentRowDate = current.isEmpty() ? formatter.parse("01/Jan/3030, 00:00") : formatter.parse(current);
            Date nextRowDate = next.isEmpty() ? formatter.parse("01/Jan/3030, 00:00") : formatter.parse(next);

            if (currentRowDate.before(nextRowDate)) {
                System.out.println("current: " + current + "\n" + "next: " + next);
                return false;
            }
        }
        return true;
    }

    public boolean isIntegersListSorted(List<Integer> list) {
        if (list.isEmpty() || list.size() == 1) return true;
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i + 1)) {
                System.out.println("Not sorted");
                System.out.println(list.get(i) + "\n" + list.get(i + 1));
                return false;
            }
        }
        return true;
    }

    public boolean isIntegersListReverseSorted(List<Integer> list) {
        if (list.isEmpty() || list.size() == 1) return true;
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) < list.get(i)) {
                System.out.println("Nor reverseSorted");
                System.out.println(list.get(i) + "\n" + list.get(i + 1));
                return false;
            }
        }
        return true;
    }
}
