package factory;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

public class BrowserFactory {

    public static Browser launchBrowser(Playwright playwright, String browserName, boolean headless) {

        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                .setHeadless(headless);

        switch (browserName.trim().toLowerCase()) {

            case "chrome":
                return playwright.chromium().launch(
                        options.setChannel("chrome")
                );

            case "edge":
                return playwright.chromium().launch(
                        options.setChannel("msedge")
                );

            case "chromium":
                return playwright.chromium().launch(options);

            case "firefox":
                return playwright.firefox().launch(options);

            default:
                throw new IllegalArgumentException(
                        "Unsupported browser: " + browserName
                );
        }
    }
}