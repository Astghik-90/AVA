package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Locators.HomePageConstants;

public class HomePage extends BasePage{

    @FindBy(css = HomePageConstants.cookiePopUp )
    private WebElement cookiePopUpGotItButton;

    @FindBy(css = HomePageConstants.signInButton)
    private WebElement signInButton;

    @FindBy(css = HomePageConstants.username)
    private WebElement username;

    @FindBy(css = HomePageConstants.password)
    private WebElement password;

    @FindBy(css = HomePageConstants.submit)
    private WebElement submitSignInButton;

    public HomePage(WebDriver driver) {
        super(driver);
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
}
