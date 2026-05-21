package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Log;

import java.time.Duration;

public class LoginPage {

    //private By username = By.name("username");
    //private By password = By.name("password");
    //private By loginBtn = By.xpath("//button[normalize-space()='Login']");

    // page factory
    /// makes code clear and improves the performance
    /// built in class

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(name="username")
    WebElement usernameTextbox;

    @FindBy(name="password")
    WebElement passwordTextbox;

    @FindBy(xpath="//button[normalize-space()='Login']")
    WebElement loginButtonBox;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void usernameTextBox(String username) {
        usernameTextbox.sendKeys(username);
    }

    public void passwordTextBox(String pass) {
        passwordTextbox.sendKeys(pass);
    }

    public void loginButton() {
        Log.info("Clicking login button");
        loginButtonBox.sendKeys(Keys.ENTER);
    }
}