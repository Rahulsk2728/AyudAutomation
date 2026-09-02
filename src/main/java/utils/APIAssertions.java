package utils;

import io.restassured.response.Response;
import org.testng.Assert;

public class APIAssertions {

    /**
     * Validate HTTP status code
     */
    public static void assertStatusCode(
            Response response,
            int expectedStatusCode) {

        Assert.assertEquals(
                response.getStatusCode(),
                expectedStatusCode,
                "Unexpected status code"
        );
    }


    /**
     * Validate response time
     */
    public static void assertResponseTime(
            Response response,
            long maxTimeInMilliseconds) {

        Assert.assertTrue(
                response.getTime() < maxTimeInMilliseconds,
                "Response took more than "
                        + maxTimeInMilliseconds
                        + " ms"
        );
    }


    /**
     * Validate that a response field is not null
     */
    public static void assertFieldNotNull(
            Response response,
            String jsonPath) {

        Assert.assertNotNull(
                response.jsonPath().get(jsonPath),
                "Field is missing: " + jsonPath
        );
    }


    /**
     * Validate a response field value
     */
    public static void assertFieldEquals(
            Response response,
            String jsonPath,
            Object expectedValue) {

        Assert.assertEquals(
                response.jsonPath().get(jsonPath),
                expectedValue,
                "Incorrect value for field: "
                        + jsonPath
        );
    }
}