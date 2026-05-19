package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.ExtentReportManager;
import utils.Log;

import java.time.Duration;

public class BaseTest {

    /// web driver instance
    /// protected means this only can be used here

    protected WebDriver driver;
    protected static ExtentReports extent;
    protected static ExtentTest test;

    // report functions
    @BeforeSuite
    public void setupRemote() {
        extent = ExtentReportManager.getReportInstance();
    }

    @AfterSuite
    public void teardownRemote() {
        extent.flush();
    }

    /// function
    @BeforeMethod
    public void setUp() {
        Log.info("Starting WebDriver...");
        driver = new ChromeDriver();

        //Critical for screenshot rendering stability
        driver.manage().window().setSize(new Dimension(1920, 1080));
        // Better stability than only implicit wait
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

        Log.info("Driver initialized");
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

    }

    @AfterMethod
    public void tearDown(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = ExtentReportManager.captureScreenShot(driver, "LoginFailure");
            System.out.println("ScreenShot Captues, PATH :" +screenshotPath);
            test.fail("Test Failed Screenshot attached: ",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        }

        if (driver != null) {
            Log.info("Closing WebDriver...");
            //driver.quit();
        }

    }

}
