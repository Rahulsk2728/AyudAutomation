package base;

import com.microsoft.playwright.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import factory.BrowserFactory;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utils.ConfigReader;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.nio.file.Paths;
import org.testng.ITestResult;

import java.nio.file.Files;
import java.nio.file.Path;

public class BaseTest {

    protected Playwright playwright;
    private static ThreadLocal<Browser> browser = new ThreadLocal<>();

    private static ThreadLocal<BrowserContext> context = new ThreadLocal<>();

    private static ThreadLocal<Page> page = new ThreadLocal<>();


    public static Page getPage() {
        return page.get();
    }

    private static final Logger logger =
            LogManager.getLogger(BaseTest.class);


    @BeforeMethod
    @Parameters("browser")
    public void  setUp(@Optional("chromium") String browserName) {

        logger.info("Creating Playwright instance");
        playwright = Playwright.create();


        browser.set(
                BrowserFactory.launchBrowser(
                        playwright,
                        ConfigReader.getProperty("browser"),
                        Boolean.parseBoolean(ConfigReader.getProperty("headless"))
                )
        );

        context.set(browser.get().newContext());

        context.get().tracing().start(
                new Tracing.StartOptions()
                        .setScreenshots(true)
                        .setSnapshots(true)
                        .setSources(true)
        );

        page.set(context.get().newPage());
        page.get().navigate(ConfigReader.getProperty("url"));

        logger.info("Launching browser");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {

        String testName = result.getMethod().getMethodName();

        // 1. Capture screenshot if test failed
        if (result.getStatus() == ITestResult.FAILURE && page.get() != null) {



            try {
                Path screenshotDir = Paths.get("screenshots");

                Files.createDirectories(screenshotDir);

                Path screenshotPath =
                        screenshotDir.resolve(testName + ".png");

                page.get().screenshot(
                        new Page.ScreenshotOptions()
                                .setPath(screenshotPath)
                                .setFullPage(true)
                );

                logger.info("Screenshot captured: {}", screenshotPath);

            } catch (Exception e) {
                logger.error("Failed to capture screenshot", e);
            }
        }

        // 1. Stop tracing
        if (context.get() != null) {
            context.get().tracing().stop(
                    new Tracing.StopOptions()
                            .setPath(Paths.get("traces/" + testName + ".zip"))
            );
        }

        // 2. Close page
        if (page.get() != null) {
            page.get().close();
            page.remove();
        }

        // 3. Close context
        if (context.get() != null) {
            context.get().close();
            context.remove();
        }

        // 4. Close browser
        if (browser.get() != null) {
            browser.get().close();
            browser.remove();
        }

        logger.info("Browser closed successfully.");
    }
}