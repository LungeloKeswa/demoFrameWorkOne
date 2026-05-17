package tests;

import base.BaseTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void testValidLogin() {
        /// login class object
        LoginPage loginPage = new LoginPage(driver);
        loginPage.usernameTextBox("Admin");
        loginPage.passwordTextBox("admin123");
        loginPage.loginButton();

        Assert.assertEquals(driver.getTitle(),"OrangeHRM");
        System.out.println("This is the page Title :"+driver.getTitle());
    }

}
