package pages;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.List;

public class HomePage {

    private Page page;

    // Example Locators
    private Locator homeLogo;
    private Locator profileIcon;
    private Locator logoutButton;

    public HomePage(Page page) {
        this.page = page;

        // Replace these with actual locators from ayud.com
        homeLogo = page.locator("(//img[@alt='Ayud Software'])[1]");

    }

    // Verify Home Page
    public boolean isHomePageDisplayed() {
        return homeLogo.isVisible();
    }

    //Verify deadlinks
    public List<ElementHandle> getAllLinks() {
        return page.querySelectorAll("a");
    }

    // Click Profile
    public void clickProfile() {
        profileIcon.click();
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