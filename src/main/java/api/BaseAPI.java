package api;

import config.APIConfig;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseAPI {

    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;

    /**
     * Returns common Request Specification
     */
    public static RequestSpecification getRequestSpecification() {

        if (requestSpec == null) {

            requestSpec = new RequestSpecBuilder()
                    .setBaseUri(APIConfig.getBaseURL())
                    .setContentType(ContentType.JSON)
                    .addHeader("Accept", "application/json")
                    .build();
        }

        return requestSpec;
    }

    /**
     * Returns common Response Specification
     */
    public static ResponseSpecification getResponseSpecification() {

        if (responseSpec == null) {

            responseSpec = new ResponseSpecBuilder()
                    .expectContentType(ContentType.JSON)
                    .build();
        }

        return responseSpec;
    }

}