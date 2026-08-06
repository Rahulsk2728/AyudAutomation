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

        HomePage homePage = new HomePage(getPage());

        // Scroll to Products section
        homePage.productClick();

        // Capture the new tab
        Page jobsPage = getPage().waitForPopup(() -> {
            homePage.clickAyudJobs();
        });

        jobsPage.waitForLoadState();

        AyudJobsPage ayudJobsPage = new AyudJobsPage(jobsPage);

        Assert.assertTrue(ayudJobsPage.isAyudJobsPageDisplayed());

        Assert.assertEquals(
                ayudJobsPage.getCurrentURL(),
                URLs.AYUD_JOBS);

        Assert.assertEquals(
                ayudJobsPage.getPageTitle(),
                "Ayud jobs | Home");
    }







}
