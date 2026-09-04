package apiTests;

import api.APIClient;
import endpoints.UserEndpoints;
import io.restassured.response.Response;
import models.CreateUserRequest;
import models.UpdateUserRequest;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.APIAssertions;

public class APIChainingTest {

    @Test
    public void userCrudChainingTest() {

        // 1. GET all users
        Response getAllResponse =
                APIClient.get(UserEndpoints.GET_ALL_USERS);

        APIAssertions.assertStatusCode(
                getAllResponse,
                200
        );

        // 2. Extract existing user ID
        int userId =
                getAllResponse
                        .jsonPath()
                        .getInt("data[0].id");

        System.out.println(
                "Extracted User ID: " + userId
        );

        Assert.assertTrue(
                userId > 0,
                "User ID should be greater than 0"
        );

        // 3. GET single user using extracted ID
        Response getUserResponse =
                APIClient.get(
                        UserEndpoints.GET_SINGLE_USER,
                        "id",
                        userId
                );

        APIAssertions.assertStatusCode(
                getUserResponse,
                200
        );

        APIAssertions.assertFieldEquals(
                getUserResponse,
                "data.id",
                userId
        );

        String email =
                getUserResponse
                        .jsonPath()
                        .getString("data.email");

        System.out.println(
                "User Email: " + email
        );

        // 4. PUT update using same ID
        CreateUserRequest updateRequest =
                new CreateUserRequest(
                        "Rahul Chained",
                        "Senior SDET"
                );

        Response putResponse =
                APIClient.put(
                        UserEndpoints.UPDATE_USER,
                        "id",
                        userId,
                        updateRequest
                );

        APIAssertions.assertStatusCode(
                putResponse,
                200
        );

        APIAssertions.assertFieldEquals(
                putResponse,
                "name",
                "Rahul Chained"
        );

        APIAssertions.assertFieldEquals(
                putResponse,
                "job",
                "Senior SDET"
        );

        APIAssertions.assertFieldNotNull(
                putResponse,
                "updatedAt"
        );

        // 5. PATCH same user
        UpdateUserRequest patchRequest =
                new UpdateUserRequest(
                        "Automation Architect"
                );

        Response patchResponse =
                APIClient.patch(
                        UserEndpoints.PATCH_USER,
                        "id",
                        userId,
                        patchRequest
                );

        APIAssertions.assertStatusCode(
                patchResponse,
                200
        );

        APIAssertions.assertFieldEquals(
                patchResponse,
                "job",
                "Automation Architect"
        );

        APIAssertions.assertFieldNotNull(
                patchResponse,
                "updatedAt"
        );

        // 6. DELETE same user
        Response deleteResponse =
                APIClient.delete(
                        UserEndpoints.DELETE_USER,
                        "id",
                        userId
                );

        APIAssertions.assertStatusCode(
                deleteResponse,
                204
        );

        Assert.assertTrue(
                deleteResponse
                        .getBody()
                        .asString()
                        .isEmpty(),
                "DELETE response body should be empty"
        );

        System.out.println(
                "User CRUD chaining completed for ID: "
                        + userId
        );
    }
}