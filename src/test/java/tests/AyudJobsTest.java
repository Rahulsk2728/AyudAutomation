package tests;

import base.BaseTest;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import constants.URLs;
import dataproviders.LoginDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AyudJobsPage;
import pages.HomePage;
import utils.LinkVerifier;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AyudJobsTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(HomeTest.class);

    @Test(priority = 1)
    public void verifyAyudJobsNavigation() {

        logger.info("========== Starting Ayud Jobs Navigation Test ==========");

        HomePage homePage = new HomePage(getPage());

        logger.info("Clicking on Products section.");

        // Scroll to Products section
        homePage.productClick();

        logger.info("Clicking on Ayud Jobs card and waiting for new tab.");

        // Capture the new tab
        Page jobsPage = getPage().waitForPopup(() -> {
            homePage.clickAyudJobs();
        });

        jobsPage.waitForLoadState();

        AyudJobsPage ayudJobsPage = new AyudJobsPage(jobsPage);
        logger.info("Verifying Ayud Jobs page is displayed.");

        Assert.assertTrue(ayudJobsPage.isAyudJobsPageDisplayed());

        Assert.assertEquals(
                ayudJobsPage.getCurrentURL(),
                URLs.AYUD_JOBS,"Page title doesnt match");
        logger.info("Verified URL: {}", ayudJobsPage.getCurrentURL());

        logger.info("Verifying Ayud Jobs page title.");

        Assert.assertEquals(
                ayudJobsPage.getPageTitle(),
                "Ayud jobs | Home");
    }







}
