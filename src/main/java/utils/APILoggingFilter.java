package utils;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class APILoggingFilter implements Filter {

    private static final Logger logger =
            LogManager.getLogger(APILoggingFilter.class);

    @Override
    public Response filter(
            FilterableRequestSpecification requestSpec,
            FilterableResponseSpecification responseSpec,
            FilterContext filterContext) {

        long startTime =
                System.currentTimeMillis();

        // =========================
        // Request Logging
        // =========================

        logger.info(
                "API Request | Method: "
                        + requestSpec.getMethod()
                        + " | URL: "
                        + requestSpec.getURI()
        );

        logger.info(
                "Request Headers: "
                        + maskSensitiveHeaders(
                        requestSpec.getHeaders()
                )
        );

        if (requestSpec.getBody() != null) {

            logger.info(
                    "Request Body:\n"
                            + requestSpec.getBody()
            );
        }

        // =========================
        // Send Request
        // =========================
        String requestDetails =
                "Method: " + requestSpec.getMethod()
                        + "\nURL: " + requestSpec.getURI()
                        + "\nHeaders: "
                        + maskSensitiveHeaders(requestSpec.getHeaders())
                        + "\nBody: "
                        + (requestSpec.getBody() != null
                        ? requestSpec.getBody()
                        : "No Request Body");

        APIAllureUtils.attachRequest(requestDetails);

        Response response =
                filterContext.next(
                        requestSpec,
                        responseSpec
                );

        long responseTime =
                System.currentTimeMillis()
                        - startTime;
        String responseDetails =
                "Status Code: "
                        + response.getStatusCode()
                        + "\nResponse Time: "
                        + responseTime
                        + " ms"
                        + "\nResponse Body:\n"
                        + (
                        response.getBody() != null
                                ? response.asPrettyString()
                                : "No Response Body"
                );

        APIAllureUtils.attachResponse(responseDetails);

        // =========================
        // Response Logging
        // =========================

        logger.info(
                "API Response | Status: "
                        + response.getStatusCode()
                        + " | Time: "
                        + responseTime
                        + " ms"
        );

        if (response.getBody() != null
                && response.getBody().asString() != null
                && !response.getBody().asString().isEmpty()) {

            logger.info(
                    "Response Body:\n"
                            + response.asPrettyString()
            );
        }

        return response;
    }


    /**
     * Masks sensitive request headers
     */
    private Map<String, String> maskSensitiveHeaders(
            io.restassured.http.Headers headers) {

        Map<String, String> maskedHeaders =
                new HashMap<>();

        headers.forEach(header -> {

            String name =
                    header.getName();

            String value =
                    header.getValue();

            if (name.equalsIgnoreCase("Authorization")
                    || name.equalsIgnoreCase("x-api-key")
                    || name.equalsIgnoreCase("Cookie")) {

                maskedHeaders.put(
                        name,
                        "******"
                );

            } else {

                maskedHeaders.put(
                        name,
                        value
                );
            }
        });

        return maskedHeaders;
    }
}