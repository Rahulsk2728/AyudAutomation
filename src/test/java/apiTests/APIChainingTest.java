package apiTests;

import api.APIClient;
import endpoints.UserEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class APIChainingTest {

    @Test
    public void getUserAndUseIdTest() {

        // 1. Get all users

        Response getAllResponse =
                APIClient.get(
                        UserEndpoints.GET_ALL_USERS
                );

        System.out.println(
                "Get All Users Response:"
        );

        System.out.println(
                getAllResponse.asPrettyString()
        );

        // 2. Validate response

        Assert.assertEquals(
                getAllResponse.getStatusCode(),
                200,
                "Expected status code 200"
        );

        // 3. Extract first user's ID

        int userId =
                getAllResponse.jsonPath()
                        .getInt("data[0].id");

        Assert.assertTrue(
                userId > 0,
                "User ID should be greater than 0"
        );

        System.out.println(
                "Extracted User ID: " + userId
        );

        // 4. Use extracted ID in GET Single User

        Response getUserResponse =
                APIClient.get(
                        UserEndpoints.GET_SINGLE_USER,
                        "id",
                        userId
                );

        System.out.println(
                "Get Single User Response:"
        );

        System.out.println(
                getUserResponse.asPrettyString()
        );

        // 5. Validate second API response

        Assert.assertEquals(
                getUserResponse.getStatusCode(),
                200,
                "Expected status code 200"
        );

        // 6. Validate that the same ID was returned

        int returnedUserId =
                getUserResponse.jsonPath()
                        .getInt("data.id");

        Assert.assertEquals(
                returnedUserId,
                userId,
                "User ID does not match"
        );
    }
}