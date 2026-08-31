package base;

import com.microsoft.playwright.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import factory.BrowserFactory;
import utils.ConfigReader;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.ITestResult;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BaseTest {

    private static ThreadLocal<Playwright> playwright =
            new ThreadLocal<>();

    private static ThreadLocal<Browser> browser =
            new ThreadLocal<>();

    private static ThreadLocal<BrowserContext> context =
            new ThreadLocal<>();

    private static ThreadLocal<Page> page =
            new ThreadLocal<>();

    private static ThreadLocal<String> browserNameThreadLocal =
            new ThreadLocal<>();


    private static final Logger logger =
            LogManager.getLogger(BaseTest.class);


    public static Page getPage() {
        return page.get();
    }


    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("chromium") String browserName) {

        // Store browser name for this thread
        browserNameThreadLocal.set(
                browserName.trim().toLowerCase()
        );

        logger.info(
                "Test started | Thread: {} | Browser: {}",
                Thread.currentThread().getName(),
                browserName
        );

        logger.info("Creating Playwright instance");

        playwright.set(
                Playwright.create()
        );

        logger.info(
                "Browser selected: {}",
                browserName
        );

        browser.set(
                BrowserFactory.launchBrowser(
                        playwright.get(),
                        browserName,
                        Boolean.parseBoolean(
                                ConfigReader.getProperty("headless")
                        )
                )
        );

        context.set(
                browser.get().newContext()
        );


        // Start Playwright tracing
        context.get().tracing().start(
                new Tracing.StartOptions()
                        .setScreenshots(true)
                        .setSnapshots(true)
                        .setSources(true)
        );


        page.set(
                context.get().newPage()
        );


        page.get().navigate(
                ConfigReader.getProperty("url")
        );


        logger.info(
                "Launching browser: {}",
                browserName
        );
    }


    @AfterMethod
    public void tearDown(ITestResult result) {

        String testName =
                result.getMethod().getMethodName();

        String browserName =
                browserNameThreadLocal.get();


        logger.info(
                "Test finished | Thread: {} | Browser: {}",
                Thread.currentThread().getName(),
                browserName
        );


        // =====================================================
        // 1. Screenshot on failure
        // =====================================================

        if (result.getStatus() == ITestResult.FAILURE
                && page.get() != null) {

            try {

                Path screenshotDir =
                        Paths.get(
                                "screenshots",
                                browserName
                        );

                Files.createDirectories(
                        screenshotDir
                );


                Path screenshotPath =
                        screenshotDir.resolve(
                                testName + ".png"
                        );


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


        // =====================================================
        // 2. Stop tracing
        // =====================================================

        if (context.get() != null) {

            try {

                Path traceDir =
                        Paths.get(
                                "traces",
                                browserName
                        );

                Files.createDirectories(
                        traceDir
                );


                Path tracePath =
                        traceDir.resolve(
                                testName + ".zip"
                        );


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


        // =====================================================
        // 3. Close Page
        // =====================================================

        if (page.get() != null) {

            page.get().close();
            page.remove();
        }


        // =====================================================
        // 4. Close Browser Context
        // =====================================================

        if (context.get() != null) {

            context.get().close();
            context.remove();
        }


        // =====================================================
        // 5. Close Browser
        // =====================================================

        if (browser.get() != null) {

            browser.get().close();
            browser.remove();
        }


        // =====================================================
        // 6. Close Playwright
        // =====================================================

        if (playwright.get() != null) {

            playwright.get().close();
            playwright.remove();
        }


        // =====================================================
        // 7. Remove Browser Name
        // =====================================================

        browserNameThreadLocal.remove();


        logger.info(
                "Browser closed successfully."
        );
    }
}