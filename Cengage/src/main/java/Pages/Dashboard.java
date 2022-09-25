package Pages;

import Locators.DashboardConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Dashboard extends BasePage {

    private By assignmentsTab = By.cssSelector(DashboardConstants.assignmentsTab);

    private By profileIcon = By.cssSelector(DashboardConstants.profileIcon);

    private By myAccount = By.cssSelector(DashboardConstants.myAccount);

    private By logOut = By.cssSelector(DashboardConstants.logOut);

    public Dashboard(WebDriver driver) {
        super(driver);
    }

    public AssignmentsPage navigateAssignmentsPage() {
        click(assignmentsTab);
        wait(3000);
        return new AssignmentsPage(driver);
    }

    public HomePage logOut() {
        click(profileIcon);
        click(logOut);
        return new HomePage(driver);
    }

}
