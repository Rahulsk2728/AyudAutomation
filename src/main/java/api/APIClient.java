package api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class APIClient {

    /**
     * GET Request
     */
    public static Response get(String endpoint) {

        return given()
                .spec(BaseAPI.getRequestSpecification())
                .when()
                .get(endpoint)
                .then()
                .spec(BaseAPI.getResponseSpecification())
                .extract()
                .response();
    }

    /**
     * POST Request
     */
    public static Response post(String endpoint, Object body) {

        return given()
                .spec(BaseAPI.getRequestSpecification())
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .spec(BaseAPI.getResponseSpecification())
                .extract()
                .response();
    }

    /**
     * PUT Request
     */
    public static Response put(String endpoint, Object body) {

        return given()
                .spec(BaseAPI.getRequestSpecification())
                .body(body)
                .when()
                .put(endpoint)
                .then()
                .spec(BaseAPI.getResponseSpecification())
                .extract()
                .response();
    }

    /**
     * PATCH Request
     */
    public static Response patch(String endpoint, Object body) {

        return given()
                .spec(BaseAPI.getRequestSpecification())
                .body(body)
                .when()
                .patch(endpoint)
                .then()
                .spec(BaseAPI.getResponseSpecification())
                .extract()
                .response();
    }

    /**
     * DELETE Request
     */
    public static Response delete(String endpoint) {

        return given()
                .spec(BaseAPI.getRequestSpecification())
                .when()
                .delete(endpoint)
                .then()
                .spec(BaseAPI.getResponseSpecification())
                .extract()
                .response();
    }

}