package tests;

import base.BaseTest;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import constants.URLs;
import dataproviders.LoginDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CareerClarityPage;
import pages.HomePage;
import pages.JanexaPage;
import utils.LinkVerifier;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HomeTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(HomeTest.class);

    @Test(priority = 1)
    public void verifyHomePageTitle() {

        HomePage homePage = new HomePage(getPage());

        String actualTitle = homePage.getPageTitle();

        logger.info("Page Title: " + actualTitle);

        Assert.assertFalse(actualTitle.isEmpty(), "Home page title is empty");
    }

    @Test(priority = 2)
    public void verifyCurrentURL() {

        HomePage homePage = new HomePage(getPage());

        String currentUrl = homePage.getCurrentURL();

        logger.info("Current URL: " + currentUrl);

        Assert.assertTrue(currentUrl.contains("ayud"),
                "Current URL does not contain 'ayud'");
    }

    @Test(priority = 3)
    public void verifyHomePageDisplayed() {

        HomePage homePage = new HomePage(getPage());

        Assert.assertTrue(homePage.isHomePageDisplayed(),
                "Home page is not displayed");
    }

    @Test(priority = 4)
    public void verifyDeadLinks() throws Exception {

        HomePage homePage = new HomePage(getPage());

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


    @Test(priority = 5)
    public void verifyJanexaPage() {

        HomePage homePage = new HomePage(getPage());

        homePage.productClick();


        // Capture the new tab
        Page janexaPage = getPage().waitForPopup(() -> {
            homePage.clickJanexa();
        });

        logger.info("Verified janexa page" );

        JanexaPage janexa = new JanexaPage(janexaPage);

        Assert.assertEquals(janexa.getCurrentURL(), URLs.JANEXA);
    }

    @Test(priority = 6)
    public void verifyCareerclaritypage() {

        HomePage homePage = new HomePage(getPage());

        homePage.productClick();

        // Capture the new tab
        Page careerPage = getPage().waitForPopup(() -> {
            homePage.clickCareer();
        });

        logger.info("Verified career clarity page" );

        CareerClarityPage career = new CareerClarityPage(careerPage);

        Assert.assertEquals(career.getCurrentURL(), URLs.CAREER_CLARITY);
    }

    @Test(priority = 7)
    public void verifyAyudBlog() {

        HomePage homePage = new HomePage(getPage());

        homePage.productClick();

        // Capture the new tab
        Page careerPage = getPage().waitForPopup(() -> {
            homePage.clickAyudBlog();
        });

        logger.info("Verified Ayud blog page" );

        CareerClarityPage career = new CareerClarityPage(careerPage);

        Assert.assertEquals(career.getCurrentURL(), URLs.BLOG);
    }

    @Test(priority = 7)
    public void verifyServices() {

        HomePage homePage = new HomePage(getPage());

        homePage.serviceClick();

        logger.info("Verified Services page" );

        Assert.assertEquals(homePage.isServicesSectionDisplayed(),"Services section is not displayed");
    }

    @Test(priority = 8)
    public void verifyWork() {

        HomePage homePage = new HomePage(getPage());

        homePage.clickWork();

        logger.info("Verified Workpage" );

        Assert.assertEquals(homePage.isWorkSectionDisplayed(),"work section is not displayed");
    }

    @Test(priority = 9)
    public void verifyAdvisory() {

        HomePage homePage = new HomePage(getPage());

        homePage.clickAdvisory();

        logger.info("Verified Advisory" );

        Assert.assertEquals(homePage.isAdvisorySectionDisplayed(),"advisory section is not displayed");
    }











}
