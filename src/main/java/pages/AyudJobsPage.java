package pages;

import base.BasePage;
import com.microsoft.playwright.Page;

public class AyudJobsPage extends BasePage {

    public AyudJobsPage(Page page) {
        super(page);
    }

    public String getCurrentURL() {
        return page.url();
    }

    public String getPageTitle() {
        return page.title();
    }

    public boolean isAyudJobsPageDisplayed() {
        return page.getByText("Job Fairs").isVisible();
    }
}