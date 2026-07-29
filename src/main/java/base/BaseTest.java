package base;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import factory.BrowserFactory;
import utils.ConfigReader;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected static Page page;

    public static Page getPage() {
        return page;
    }

    private static final Logger logger =
            LogManager.getLogger(BaseTest.class);

    @BeforeMethod
    public void setUp() {

        logger.info("Creating Playwright instance");
        playwright = Playwright.create();


        browser = BrowserFactory.launchBrowser(
                playwright,
                ConfigReader.getProperty("browser"),
                Boolean.parseBoolean(ConfigReader.getProperty("headless"))
        );


        context = browser.newContext();

        page = context.newPage();

        page.navigate(ConfigReader.getProperty("url"));

        logger.info("Launching browser");
    }

    @AfterMethod
    public void tearDown() {

        if (page != null) {
            page.close();
        }

        if (context != null) {
            context.close();
        }

        if (browser != null) {
            browser.close();
        }

        if (playwright != null) {
            playwright.close();
        }

        logger.info("Browser closed successfully.");
    }
}