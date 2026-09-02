package utils;

import io.qameta.allure.Attachment;

public class APIAllureUtils {

    @Attachment(
            value = "API Request",
            type = "text/plain"
    )
    public static String attachRequest(String request) {

        return request;
    }


    @Attachment(
            value = "API Response",
            type = "text/plain"
    )
    public static String attachResponse(String response) {

        return response;
    }
}