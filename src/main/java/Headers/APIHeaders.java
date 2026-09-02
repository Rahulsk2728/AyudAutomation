package Headers;

import java.util.HashMap;
import java.util.Map;

public class APIHeaders {

    /**
     * Default headers for API requests
     */
    public static Map<String, String> defaultHeaders() {

        Map<String, String> headers = new HashMap<>();

        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");

        return headers;
    }


    /**
     * Headers for authenticated requests
     */
    public static Map<String, String> authenticatedHeaders(
            String token) {

        Map<String, String> headers =
                defaultHeaders();

        headers.put(
                "Authorization",
                "Bearer " + token
        );

        return headers;
    }
}