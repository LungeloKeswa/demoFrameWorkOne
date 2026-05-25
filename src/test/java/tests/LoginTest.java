package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ExcelUtils;
import utils.ExtentReportManager;
import utils.Log;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @DataProvider(name="LoginData")
    public Object[][] LoginData() throws IOException {
       String filePath = System.getProperty("user.dir")+"/testdata/TestData.xlsx";
        ExcelUtils.loadExcel(filePath, "DataOne");
        int rowCount = ExcelUtils.getRowCount();
        Object[][] data = new Object[rowCount-1][2];

        for (int i = 1; i<rowCount; i++) {
            data[i-1][0] = ExcelUtils.getCellValue(i, 0); // get username
            data[i-1][1] = ExcelUtils.getCellValue(i, 1); // get password
        }
        ExcelUtils.closeExcel();
        return data;
    }

    /// if there is not much data and there won't be to many changes
    @DataProvider(name="LoginData2")
    public Object[][] getData() {

        return new Object[][]{
                {"user1", "pass1"},
                {"user2", "pass2"},
                {"user3", "pass3"},
                {"Admin", "admin123"}
        };
    }

    /////@Test(dataProvider = "LoginData2")
    @Test
   // @Parameters({"username", "password"})
   // public void testValidLogin( String username, String password) {
    public void testValidLogin() {
        Log.info("Starting Login Test...");
        //test = ExtentReportManager.createTest("Login Test - "+username);
        test = ExtentReportManager.createTest("Login Test - ");

        LoginPage loginPage = new LoginPage(driver);
        test.info("Entering username and password");

//        loginPage.usernameTextBox(username);
//        loginPage.passwordTextBox(password);
        loginPage.usernameTextBox("Admin");
        loginPage.passwordTextBox("admin123");

        test.info("Clicking login button");
        loginPage.loginButton();

        String actualTitle = driver.getTitle();
        String expectedTitle = "OrangeHRM";

        Log.info("Verifying page title...");

        System.out.println("Page Title: " + actualTitle);

        Assert.assertEquals(actualTitle, expectedTitle, "Page title is incorrect.");

    }

}