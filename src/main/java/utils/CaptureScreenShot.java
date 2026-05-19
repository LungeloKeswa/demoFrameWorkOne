package utils;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class CaptureScreenShot {

    public static String captureScreenShot(WebDriver driver, String screenShotName) throws IOException {

        // Convert driver to TakesScreenshot
        TakesScreenshot ts = (TakesScreenshot) driver;

        // Capture screenshot source
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);

        // Screenshot path
        String destinationPath = System.getProperty("user.dir")
                        + "/Screenshots/"
                        + screenShotName
                        + "_"
                        + System.currentTimeMillis()
                        + ".png";

        // Destination file
        File destinationFile = new File(destinationPath);

        // Copy screenshot
        FileUtils.copyFile(sourceFile, destinationFile);

        // Return screenshot path
        return destinationPath;
    }
}