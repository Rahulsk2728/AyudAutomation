package utils;

import io.qameta.allure.Attachment;

public class LogUtils {

    @Attachment(value = "{logName}", type = "text/plain")
    public static String attachLog(String logName, String logContent) {
        return logContent;
    }
}