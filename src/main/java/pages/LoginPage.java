package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;

    private By usernameTextBox = By.name("username");
    private By passwordTextBox = By.name("password");
    private By loginButton = By.xpath("//button[normalize-space()='Login']");

    // Constructor is always called by default
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // first function
    public void usernameTextBox(String username) {
        driver.findElement(usernameTextBox).sendKeys(username);
    }

    // second function
    public void passwordTextBox(String password) {
        driver.findElement(passwordTextBox).sendKeys(password);
    }

    // third function
    public void loginButton() {
        driver.findElement(loginButton).click();
    }

}
