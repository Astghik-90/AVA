package Pages;

import Locators.DashboardConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TeacherDashboard extends BasePage{

    @FindBy(css = DashboardConstants.assignmentsTab)
    private WebElement assignmentsTab;

    @FindBy(css = DashboardConstants.profileIcon)
    private WebElement profileIcon;

    @FindBy(css = DashboardConstants.myAccount)
    private WebElement myAccount;

    @FindBy(css = DashboardConstants.logOut)
    private WebElement logOut;


    public TeacherDashboard(WebDriver driver) {
        super(driver);
    }

    public AssignmentsPage navigateAssignmentsPage(){
        click(assignmentsTab);
        return new AssignmentsPage(driver);
    }

    public HomePage logOut(){
        click(profileIcon);
        click(logOut);
        return new HomePage(driver);
    }

}
