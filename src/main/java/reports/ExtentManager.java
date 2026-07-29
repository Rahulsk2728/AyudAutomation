package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getExtentReports() {

        if (extent == null) {

            String reportPath = System.getProperty("user.dir")
                    + "/reports/AutomationReport.html";

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

            sparkReporter.config().setReportName("Ayud Automation Report");
            sparkReporter.config().setDocumentTitle("Playwright Test Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            extent.setSystemInfo("Application", "Ayud");
            extent.setSystemInfo("Automation", "Playwright Java");
            extent.setSystemInfo("Tester", "Rahul");
            extent.setSystemInfo("Environment", "QA");
        }

        return extent;
    }
}