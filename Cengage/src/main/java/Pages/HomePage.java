package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Locators.HomePageConstants;

public class HomePage extends BasePage {

    public static final String baseURL = "https://web-cen-unity-stage.avallain.net/";

    private By cookiePopUpGotItButton = By.cssSelector(HomePageConstants.cookiePopUp);

    private By signInButton = By.cssSelector(HomePageConstants.signInButton);

    private By username = By.cssSelector(HomePageConstants.username);

    private By password = By.cssSelector(HomePageConstants.password);

    private By submitSignInButton = By.cssSelector(HomePageConstants.submit);

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void navigateHomePage() {
        driver.get(baseURL);
    }

    public void cookiePopUpAccept() {
        click(cookiePopUpGotItButton);
    }

    public void clickSignInButton() {
        click(signInButton);
    }

    public void fillUsername(String username) {
        enterText(this.username, username);
    }

    public void fillPassword(String password) {
        enterText(this.password, password);
    }

    public void clickSubmitSignInButton() {
        click(submitSignInButton);
    }

    //log in
    public Dashboard logIn(String username, String password) {
        navigateHomePage();
        cookiePopUpAccept();
        clickSignInButton();
        fillUsername(username);
        fillPassword(password);
        clickSubmitSignInButton();
        return new Dashboard(driver);
    }


}
