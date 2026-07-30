package utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class WaitUtils {

    private Page page;

    public WaitUtils(Page page) {
        this.page = page;
    }

    public void waitForVisible(Locator locator) {
        locator.waitFor();
    }
    public void waitForHidden(Locator locator) {
        locator.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.HIDDEN));
    }

    public void waitForClickable(Locator locator) {
        locator.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE));
    }
    public void waitForLoadState() {

        page.waitForLoadState();

    }
}
