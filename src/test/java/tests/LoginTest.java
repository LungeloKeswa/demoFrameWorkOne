package tests;

import base.BaseTest;
import org.testng.annotations.DataProvider;
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
            data[i-1][0] = ExcelUtils.getCellValue(i, 0); /// get username
            data[i-1][1] = ExcelUtils.getCellValue(i, 1); /// get password
        }
        ExcelUtils.closeExcel();
        return data;
    }

    @Test(dataProvider = "LoginData")
    public void testValidLogin( String username, String password) {

        Log.info("Starting Login Test...");
        test = ExtentReportManager.createTest("Login Test Report");

        LoginPage loginPage = new LoginPage(driver);
        test.info("Entering username and password");

        loginPage.usernameTextBox(username);
        loginPage.passwordTextBox(password);
        //loginPage.usernameTextBox("Admin");
        //loginPage.passwordTextBox("admin123");

        test.info("Clicking login button");
        loginPage.loginButton();

        String actualTitle = driver.getTitle();
        String expectedTitle = "OrangeHRM";

        Log.info("Verifying page title...");

        System.out.println("Page Title: " + actualTitle);
    }
}