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
    protected static Page page;

    public static Page getPage() {
        return page;
    }



    @BeforeMethod
    public void setUp() {

        System.out.println("Step 1");
        playwright = Playwright.create();


        browser = BrowserFactory.launchBrowser(
                playwright,
                ConfigReader.getProperty("browser"),
                Boolean.parseBoolean(ConfigReader.getProperty("headless"))
        );


        context = browser.newContext();

        page = context.newPage();

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