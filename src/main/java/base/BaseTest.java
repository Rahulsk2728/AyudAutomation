package base;

import com.microsoft.playwright.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import factory.BrowserFactory;
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
    public void setUp() {

        String browserName =
                System.getProperty("browser", "chromium");
        logger.info(
                "Test started | Thread: {} | Browser: {}",
                Thread.currentThread().getName(),
                browserName
        );

        logger.info("Creating Playwright instance");
        logger.info("Browser selected: {}", browserName);

        playwright = Playwright.create();

        browser.set(
                BrowserFactory.launchBrowser(
                        playwright,
                        browserName,
                        Boolean.parseBoolean(
                                ConfigReader.getProperty("headless")
                        )
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

        page.get().navigate(
                ConfigReader.getProperty("url")
        );

        logger.info("Launching browser: {}", browserName);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {

        String testName = result.getMethod().getMethodName();

        String browserName =
                System.getProperty("browser", "chromium")
                        .trim()
                        .toLowerCase();

        logger.info(
                "Test finished | Thread: {} | Browser: {}",
                Thread.currentThread().getName(),
                browserName
        );

        // 1. Capture screenshot if test failed
        if (result.getStatus() == ITestResult.FAILURE
                && page.get() != null) {

            try {

                Path screenshotDir =
                        Paths.get("screenshots", browserName);

                Files.createDirectories(screenshotDir);

                Path screenshotPath =
                        screenshotDir.resolve(testName + ".png");

                page.get().screenshot(
                        new Page.ScreenshotOptions()
                                .setPath(screenshotPath)
                                .setFullPage(true)
                );

                logger.info(
                        "Screenshot captured: {}",
                        screenshotPath
                );

            } catch (Exception e) {

                logger.error(
                        "Failed to capture screenshot",
                        e
                );
            }
        }

        // 2. Stop tracing
        if (context.get() != null) {

            try {

                Path traceDir =
                        Paths.get("traces", browserName);

                Files.createDirectories(traceDir);

                Path tracePath =
                        traceDir.resolve(testName + ".zip");

                context.get().tracing().stop(
                        new Tracing.StopOptions()
                                .setPath(tracePath)
                );

                logger.info(
                        "Trace saved: {}",
                        tracePath
                );

            } catch (Exception e) {

                logger.error(
                        "Failed to save trace",
                        e
                );
            }
        }

        // 3. Close page
        if (page.get() != null) {

            page.get().close();
            page.remove();
        }

        // 4. Close context
        if (context.get() != null) {

            context.get().close();
            context.remove();
        }

        // 5. Close browser
        if (browser.get() != null) {

            browser.get().close();
            browser.remove();
        }

        logger.info("Browser closed successfully.");
    }
}