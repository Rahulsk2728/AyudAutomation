package pages;

import base.BasePage;
import com.microsoft.playwright.Page;

public class CareerClarityPage extends BasePage{


    public CareerClarityPage(Page page) {
        super(page);
    }

    // Get Current URL
    public String getCurrentURL() {
        return page.url();
    }
}
