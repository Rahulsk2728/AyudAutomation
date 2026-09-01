package apiTests;

import api.APIClient;
import api.TokenManager;
import endpoints.UserEndpoints;
import io.restassured.response.Response;
import models.LoginRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthAPITest {

    @Test
    public void loginTest() {

        LoginRequest request =
                new LoginRequest(
                        "YOUR_VALID_TEST_EMAIL",
                        "YOUR_VALID_TEST_PASSWORD"
                );

        Response response =
                APIClient.post(
                        UserEndpoints.LOGIN,
                        request
                );

        System.out.println(response.asPrettyString());

        Assert.assertEquals(
                response.getStatusCode(),
                200,
                "Login failed"
        );

        String token =
                response.jsonPath()
                        .getString("token");

        Assert.assertNotNull(
                token,
                "Token is missing"
        );

        Assert.assertFalse(
                token.isEmpty(),
                "Token is empty"
        );

        System.out.println("Token: " + token);
    }

    @Test
    public void loginWithInvalidPasswordTest() {

        LoginRequest request =
                new LoginRequest(
                        "YOUR_VALID_TEST_EMAIL",
                        "wrongpassword"
                );

        Response response =
                APIClient.post(
                        UserEndpoints.LOGIN,
                        request
                );

        System.out.println(response.asPrettyString());

        // Status code
        Assert.assertEquals(
                response.getStatusCode(),
                400,
                "Expected status code 400 for invalid login"
        );

        // Error message
        String error =
                response.jsonPath()
                        .getString("error");

        Assert.assertNotNull(
                error,
                "Error message is missing"
        );

        Assert.assertFalse(
                error.isEmpty(),
                "Error message is empty"
        );

        System.out.println("Error: " + error);
    }

    @Test
    public void loginWithoutPasswordTest() {

        LoginRequest request =
                new LoginRequest(
                        "YOUR_VALID_TEST_EMAIL",
                        ""
                );

        Response response =
                APIClient.post(
                        UserEndpoints.LOGIN,
                        request
                );

        System.out.println(response.asPrettyString());

        Assert.assertEquals(
                response.getStatusCode(),
                400,
                "Expected status code 400"
        );

        Assert.assertNotNull(
                response.jsonPath().getString("error"),
                "Error message is missing"
        );
    }

    @Test
    public void loginWithoutEmailTest() {

        LoginRequest request =
                new LoginRequest(
                        "",
                        "YOUR_VALID_TEST_PASSWORD"
                );

        Response response =
                APIClient.post(
                        UserEndpoints.LOGIN,
                        request
                );

        System.out.println(response.asPrettyString());

        Assert.assertEquals(
                response.getStatusCode(),
                400,
                "Expected status code 400"
        );

        Assert.assertNotNull(
                response.jsonPath().getString("error"),
                "Error message is missing"
        );
    }

    @Test
    public void authenticatedRequestTest() {

        String token = TokenManager.getToken();

        Assert.assertNotNull(
                token,
                "Token should not be null"
        );

        Assert.assertFalse(
                token.isEmpty(),
                "Token should not be empty"
        );

        Response response =
                APIClient.getWithAuth(
                        UserEndpoints.GET_ALL_USERS
                );

        System.out.println(
                response.asPrettyString()
        );

        Assert.assertEquals(
                response.getStatusCode(),
                200,
                "Authenticated GET failed"
        );
    }
}