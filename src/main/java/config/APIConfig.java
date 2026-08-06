package config;

public class APIConfig {

    // Base URL
    private static final String BASE_URL = "https://reqres.in";

    // API Token (will be used later)
    private static final String TOKEN = "";

    // Connection Timeout
    private static final int CONNECTION_TIMEOUT = 30000;

    // Read Timeout
    private static final int READ_TIMEOUT = 30000;

    // Content Type
    private static final String CONTENT_TYPE = "application/json";

    // Accept Type
    private static final String ACCEPT = "application/json";

    // Get Base URL
    public static String getBaseURL() {
        return BASE_URL;
    }

    // Get Token
    public static String getToken() {
        return TOKEN;
    }

    // Get Connection Timeout
    public static int getConnectionTimeout() {
        return CONNECTION_TIMEOUT;
    }

    // Get Read Timeout
    public static int getReadTimeout() {
        return READ_TIMEOUT;
    }

    // Get Content Type
    public static String getContentType() {
        return CONTENT_TYPE;
    }

    // Get Accept Header
    public static String getAccept() {
        return ACCEPT;
    }

    private static final String API_KEY = "";

    public static String getApiKey() {
        return API_KEY;
    }
}