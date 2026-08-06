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
    private Locator homeLogo;
    private Locator profileIcon;
    private Locator logoutButton;

    //Products
    private Locator products;
    private Locator ayudJobs;

    //Janexa
    private Locator janexa;

    //career clarity
    private Locator careerClarity;

    public HomePage(Page page) {
        super(page);

        this.page = page;
        this.waitUtils = new WaitUtils(page);

        // Initialize locators
        homeLogo = page.locator("(//img[@alt='Ayud Software'])[1]");
        profileIcon = page.locator("YOUR_PROFILE_LOCATOR");
        logoutButton = page.locator("YOUR_LOGOUT_LOCATOR");
        products = page.locator("//a[text()='Products']");
        ayudJobs = page.locator("a[href='https://www.ayudjobs.com']");
        janexa= page.locator("a[href='https://www.janexa.in']");
        careerClarity = page.locator("a[href='https://book.ayudsoftware.com/']");

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


    //Ayud jobs products
    public void productClick() {
        products.click();
    }
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









    // Get Page Title
    public String getPageTitle() {
        return page.title();
    }

    // Get Current URL
    public String getCurrentURL() {
        return page.url();
    }
}