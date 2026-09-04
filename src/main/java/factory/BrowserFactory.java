package factory;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

public class BrowserFactory {

    public static Browser launchBrowser(
            Playwright playwright,
            String browserName,
            boolean headless) {

        String docker =
                System.getProperty("docker", "false");

        boolean isDocker =
                Boolean.parseBoolean(docker);

        browserName =
                browserName.trim().toLowerCase();

        System.out.println(
                "Launching browser: "
                        + browserName
                        + " | Docker: "
                        + isDocker
                        + " | Headless: "
                        + headless
        );

        /*
         * Docker execution
         *
         * Playwright Docker image contains
         * Playwright-managed browsers.
         *
         * Do not use Windows-installed Chrome/Edge
         * channels inside Docker.
         */
        if (isDocker) {

            return switch (browserName) {

                case "chrome", "chromium" ->
                        playwright.chromium().launch(
                                new BrowserType.LaunchOptions()
                                        .setHeadless(headless)
                        );

                case "firefox" ->
                        playwright.firefox().launch(
                                new BrowserType.LaunchOptions()
                                        .setHeadless(headless)
                        );

                default ->
                        throw new IllegalArgumentException(
                                "Unsupported Docker browser: "
                                        + browserName
                        );
            };
        }

        /*
         * Local execution
         *
         * Chrome and Edge use their installed
         * browser channels.
         */
        return switch (browserName) {

            case "chrome" ->
                    playwright.chromium().launch(
                            new BrowserType.LaunchOptions()
                                    .setChannel("chrome")
                                    .setHeadless(headless)
                    );

            case "edge" ->
                    playwright.chromium().launch(
                            new BrowserType.LaunchOptions()
                                    .setChannel("msedge")
                                    .setHeadless(headless)
                    );

            case "chromium" ->
                    playwright.chromium().launch(
                            new BrowserType.LaunchOptions()
                                    .setHeadless(headless)
                    );

            case "firefox" ->
                    playwright.firefox().launch(
                            new BrowserType.LaunchOptions()
                                    .setHeadless(headless)
                    );

            default ->
                    throw new IllegalArgumentException(
                            "Unsupported browser: "
                                    + browserName
                    );
        };
    }
}