package base;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import factory.BrowserFactory;
import utils.ConfigReader;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeMethod
    public void setUp() {

        System.out.println("Step 1");
        playwright = Playwright.create();

        System.out.println("Step 2");
        browser = BrowserFactory.launchBrowser(
                playwright,
                ConfigReader.getProperty("browser"),
                Boolean.parseBoolean(ConfigReader.getProperty("headless"))
        );

        System.out.println("Step 3");
        context = browser.newContext();

        System.out.println("Step 4");
        page = context.newPage();

        System.out.println("Step 5");
        page.navigate(ConfigReader.getProperty("url"));

        System.out.println("Step 6");
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

        System.out.println("Browser closed successfully.");
    }
}