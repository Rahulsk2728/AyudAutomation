package listeners;

import base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reports.ExtentManager;
import utils.ScreenshotUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.io.File;

public class TestListener implements ITestListener {

    private ExtentReports extent = ExtentManager.getExtentReports();
    private ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.pass("Test Passed");
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        test.skip("Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {

        // Generate the report
        extent.flush();

        // Automatically open the report
        try {
            File report = new File(System.getProperty("user.dir")
                    + "/reports/ExtentReport.html");

            Desktop.getDesktop().browse(report.toURI());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onTestFailure(ITestResult result) {

        test.fail(result.getThrowable());

        try {

            String path = ScreenshotUtils.captureScreenshot(
                    BaseTest.getPage(),
                    result.getMethod().getMethodName());

            test.addScreenCaptureFromPath(path);

        } catch (Exception e) {

            test.fail("Unable to capture screenshot");

        }

    }
    private static final Logger logger =
            LogManager.getLogger(TestListener.class);
}