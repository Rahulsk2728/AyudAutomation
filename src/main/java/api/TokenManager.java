package api;

import endpoints.UserEndpoints;
import io.restassured.response.Response;
import models.LoginRequest;

public class TokenManager {

    private static String token;

    public static String getToken() {

        if (token == null || token.isEmpty()) {

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

            if (response.getStatusCode() != 200) {
                throw new RuntimeException(
                        "Login failed. Status code: "
                                + response.getStatusCode()
                );
            }

            token =
                    response.jsonPath()
                            .getString("token");

            if (token == null || token.isEmpty()) {
                throw new RuntimeException(
                        "Token was not returned by login API"
                );
            }
        }

        return token;
    }
}