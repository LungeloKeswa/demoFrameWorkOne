package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.CaptureScreenShot;
import utils.ExtentReportManager;
import utils.Log;

import java.io.IOException;

@Listeners(utils.TestListener.class)
public class LoginTest extends BaseTest {

    @Test
    public void testValidLogin() throws IOException {

        Log.info("Starting Login Test...");

        test = ExtentReportManager.createTest("Login Test Report");

        LoginPage loginPage = new LoginPage(driver);

        test.info("Entering username and password");

        loginPage.usernameTextBox("Admin");
        loginPage.passwordTextBox("admin123");

        test.info("Clicking login button");
        loginPage.loginButton();

        String actualTitle = driver.getTitle();
        String expectedTitle = "OrangeHRM@@@";

        Log.info("Verifying page title...");

        try {
            Assert.assertEquals(actualTitle, expectedTitle);

            test.pass("Login successful. Title matched: " + actualTitle);
            Log.info("Login test passed");

        } catch (AssertionError e) {

            String path = CaptureScreenShot.captureScreenShot(driver, "LoginFailure");

            test.fail("Login failed. Screenshot captured: " + path);
            Log.error("Login test failed");

            throw e;
        }

        System.out.println("Page Title: " + actualTitle);
    }
}