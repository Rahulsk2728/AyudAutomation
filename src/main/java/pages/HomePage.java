package pages;

import base.BasePage;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.List;

import io.qameta.allure.Step;
import utils.WaitUtils;

public class HomePage extends BasePage {


    private WaitUtils waitUtils;

    // Example Locators
    private final Locator homeLogo;

    //Headers
    private final Locator products;
    private final Locator service;
    private final Locator servicesHeading;

    //Ayudjobs
    private final Locator ayudJobs;

    //Janexa
    private final Locator janexa;

    //Career clarity
    private final Locator careerClarity;

    //Ayud Blog
    private final Locator ayudBlog;

    public HomePage(Page page) {
        super(page);

        this.page = page;
        this.waitUtils = new WaitUtils(page);

        // Initialize locators
        homeLogo = page.locator("(//img[@alt='Ayud Software'])[1]");
        products = page.locator("//a[text()='Products']");
        service = page.locator("//a[text()='Services']");
        ayudJobs = page.locator("a[href='https://www.ayudjobs.com']");
        janexa= page.locator("a[href='https://www.janexa.in']");
        careerClarity = page.locator("a[href='https://book.ayudsoftware.com']");
        ayudBlog = page.locator("a[href='https://blog.ayudjobs.com']");
        servicesHeading = page.locator("//h2[text()='Services built to move a business forward.']");

    }


    // Verify Home Page
    @Step("Wait for Home Page to load")
    public boolean isHomePageDisplayed() {
        return isVisible(homeLogo);
    }

    @Step("Verify Home Page logo is visible")
    public void waitForHomePageToLoad() {
        waitUtils.waitForVisible(homeLogo);
    }

    //Verify deadlinks
    public List<ElementHandle> getAllLinks() {
        return page.querySelectorAll("a");
    }


    //Product
    public void productClick() {
        products.click();
    }

    //Services
    public void serviceClick() {
        service.click();
    }

    //Check service heading
    public boolean isServicesSectionDisplayed() {
        return servicesHeading.isVisible();
    }

    //Ayudjob
     public void clickAyudJobs() {
        ayudJobs.click();
     }

     //Janexa
     public void clickJanexa() {
         janexa.click();
     }

     //Get janexa URL
     public String getJanexaURL() {
         return page.url();
     }

     //Career clarity
    public void clickCareer() {
         careerClarity.click();
    }

    //Get career calirty url
    public String getcareerURL() {
        return page.url();
    }

    //Ayud Blog
    public void clickAyudBlog() {
        ayudBlog.click();
    }



    // Get Page Title
    public String getPageTitle() {
        return page.title();
    }

    // Get Current URL
    public String getCurrentURL() {
        return page.url();
    }
}