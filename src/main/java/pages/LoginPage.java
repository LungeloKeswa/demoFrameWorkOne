package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By username = By.name("username");
    private By password = By.name("password");
    private By loginBtn = By.xpath("//button[normalize-space()='Login']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void usernameTextBox(String user) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(username))
                .sendKeys(user);
    }

    public void passwordTextBox(String pass) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(password))
                .sendKeys(pass);
    }

    public void loginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn))
                .click();
    }
}