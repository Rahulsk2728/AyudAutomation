package listeners;

import base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reports.ExtentManager;
import utils.AllureUtils;
import utils.ScreenshotUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.io.File;

public class TestListener implements ITestListener {

    private ExtentReports extent = ExtentManager.getExtentReports();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        test.set(extent.createTest(result.getMethod().getMethodName()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test Passed");
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("Test Skipped");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        if (test.get() == null) {
            test.set(extent.createTest(result.getMethod().getMethodName()));
        }

        int retryCount = result.getMethod().getCurrentInvocationCount();

        test.get().info("Retry Attempt : " + retryCount + " of 3");

        test.get().fail(result.getThrowable());

        try {

            String path = ScreenshotUtils.captureScreenshot(
                    BaseTest.getPage(),
                    result.getMethod().getMethodName());

            test.get().addScreenCaptureFromPath(path);

        } catch (Exception e) {

            test.get().fail("Unable to capture screenshot");
            AllureUtils.attachScreenshot(BaseTest.getPage());

        }
    }
    @Override
    public void onFinish(ITestContext context) {

        extent.flush();

        try {

            File report = new File(
                    System.getProperty("user.dir")
                            + "\\reports\\ExtentReport.html");

            if (report.exists()) {

                Runtime.getRuntime().exec(
                        new String[]{
                                "cmd",
                                "/c",
                                "start",
                                "",
                                report.getAbsolutePath()
                        });

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}