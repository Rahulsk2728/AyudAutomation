package api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class APIClient {

    /**
     * GET Request - No Path Parameter
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
     * GET Request - With Path Parameter
     */
    public static Response get(
            String endpoint,
            String paramName,
            Object paramValue) {

        return given()
                .spec(BaseAPI.getRequestSpecification())
                .pathParam(paramName, paramValue)
                .when()
                .get(endpoint)
                .then()
                .spec(BaseAPI.getResponseSpecification())
                .extract()
                .response();
    }

    public static Response getWithAuth(String endpoint) {

        return given()
                .spec(BaseAPI.getRequestSpecification())
                .header(
                        "Authorization",
                        "Bearer " + TokenManager.getToken()
                )
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }


    /**
     * POST Request
     */
    public static Response post(
            String endpoint,
            Object body) {

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
     * PUT Request - No Path Parameter
     */
    public static Response put(
            String endpoint,
            Object body) {

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
     * PUT Request - With Path Parameter
     */
    public static Response put(
            String endpoint,
            String paramName,
            Object paramValue,
            Object body) {

        return given()
                .spec(BaseAPI.getRequestSpecification())
                .pathParam(paramName, paramValue)
                .body(body)
                .when()
                .put(endpoint)
                .then()
                .spec(BaseAPI.getResponseSpecification())
                .extract()
                .response();
    }


    /**
     * PATCH Request - No Path Parameter
     */
    public static Response patch(
            String endpoint,
            Object body) {

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
     * PATCH Request - With Path Parameter
     */
    public static Response patch(
            String endpoint,
            String paramName,
            Object paramValue,
            Object body) {

        return given()
                .spec(BaseAPI.getRequestSpecification())
                .pathParam(paramName, paramValue)
                .body(body)
                .when()
                .patch(endpoint)
                .then()
                .spec(BaseAPI.getResponseSpecification())
                .extract()
                .response();
    }


    /**
     * DELETE Request - No Path Parameter
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


    /**
     * DELETE Request - With Path Parameter
     */
    /**
     * DELETE Request - With Path Parameter
     */
    public static Response delete(
            String endpoint,
            String paramName,
            Object paramValue) {

        return given()
                .spec(BaseAPI.getRequestSpecification())
                .pathParam(paramName, paramValue)
                .when()
                .delete(endpoint)
                .then()
                .extract()
                .response();
    }
}