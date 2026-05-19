package utils;

import base.BaseTest;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener extends BaseTest implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        try {
            String path = CaptureScreenShot.captureScreenShot(driver, result.getName());

            test.fail("Test Failed: " + result.getName())
                    .addScreenCaptureFromPath(path);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}