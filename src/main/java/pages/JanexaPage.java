package pages;

import base.BasePage;
import com.microsoft.playwright.Page;

public class JanexaPage extends BasePage{

    public JanexaPage(Page page) {
        super(page);
    }


    // Get Current URL
    public String getCurrentURL() {
        return page.url();
    }


}
