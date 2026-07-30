package utils;

import com.microsoft.playwright.Page;
import io.qameta.allure.Attachment;

public class AllureUtils {

    @Attachment(value = "Failure Screenshot", type = "image/png")

    public static byte[] attachScreenshot(Page page) {

        return page.screenshot();

    }

}