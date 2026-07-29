package tests;

import base.BaseTest;
import com.microsoft.playwright.ElementHandle;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.LinkVerifier;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HomeTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(HomeTest.class);

    @Test(priority = 1)
    public void verifyHomePageTitle() {

        HomePage homePage = new HomePage(page);

        String actualTitle = homePage.getPageTitle();

        logger.info("Page Title: " + actualTitle);

        Assert.assertFalse(actualTitle.isEmpty(), "Home page title is empty");
    }

    @Test(priority = 2)
    public void verifyCurrentURL() {

        HomePage homePage = new HomePage(page);

        String currentUrl = homePage.getCurrentURL();

        logger.info("Current URL: " + currentUrl);

        Assert.assertTrue(currentUrl.contains("ayud"),
                "Current URL does not contain 'ayud'");
    }

    @Test(priority = 3)
    public void verifyHomePageDisplayed() {

        HomePage homePage = new HomePage(page);

        Assert.assertTrue(homePage.isHomePageDisplayed(),
                "Home page is not displayed");
    }

    @Test(priority = 3)
    public void verifyDeadLinks() throws Exception {

        HomePage homePage = new HomePage(page);

        List<ElementHandle> links = homePage.getAllLinks();
        List<String> brokenLinks = new ArrayList<>();

        for (ElementHandle link : links) {

            String href = link.getAttribute("href");

            // Skip invalid links
            if (href == null || href.isEmpty()
                    || href.startsWith("javascript:")
                    || href.startsWith("mailto:")
                    || !href.startsWith("http")) {
                continue;
            }

            int status = LinkVerifier.getStatusCode(href);

            logger.info(href + " --> " + status);

            if (status >= 400) {
                brokenLinks.add(href + " --> " + status);
            }
        }

        Assert.assertTrue(brokenLinks.isEmpty(),
                "Broken links found:" + brokenLinks);
        logger.info("Total links found: " + links.size());
        logger.info("Broken links count: " + brokenLinks.size());

        if (brokenLinks.isEmpty()) {
            logger.info("✅ No broken links found.");
        } else {
            logger.info("❌ Broken links:");
            brokenLinks.forEach(System.out::println);
        }
      }
    }
