package utils;

import com.microsoft.playwright.Page;

import java.io.File;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    public static String captureScreenshot(Page page, String testName) {

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(new Date());

        String path = System.getProperty("user.dir")
                + File.separator
                + "screenshots"
                + File.separator
                + testName
                + "_"
                + timestamp
                + ".png";

        File folder = new File("screenshots");

        if (!folder.exists()) {
            folder.mkdirs();
        }

        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get(path))
                .setFullPage(true));

        return path;
    }
}