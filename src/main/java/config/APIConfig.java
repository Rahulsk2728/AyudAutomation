package config;

public class APIConfig {

    private static final String BASE_URL =
            getProperty(
                    "baseUrl",
                    "https://reqres.in"
            );

    private static final String API_EMAIL =
            getProperty(
                    "api.email",
                    ""
            );

    private static final String API_PASSWORD =
            getProperty(
                    "api.password",
                    ""
            );


    /**
     * Get Base URL
     */
    public static String getBaseURL() {
        return BASE_URL;
    }


    /**
     * Get API email
     */
    public static String getEmail() {
        return API_EMAIL;
    }


    /**
     * Get API password
     */
    public static String getPassword() {
        return API_PASSWORD;
    }


    /**
     * Get value from System Property.
     * If not provided, return default value.
     */
    private static String getProperty(
            String propertyName,
            String defaultValue) {

        String value =
                System.getProperty(propertyName);

        if (value == null || value.isEmpty()) {
            return defaultValue;
        }

        return value;
    }
}