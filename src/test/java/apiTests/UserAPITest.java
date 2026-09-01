package apiTests;

import api.APIClient;
import endpoints.UserEndpoints;
import io.restassured.response.Response;
import models.CreateUserRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserAPITest {

    @Test
    public void getAllUsersTest() {

        Response response =
                APIClient.get(UserEndpoints.GET_ALL_USERS);

        System.out.println(response.asPrettyString());

        // Status code
        Assert.assertEquals(
                response.getStatusCode(),
                200,
                "Status code is not 200"
        );

        // Response time
        Assert.assertTrue(
                response.getTime() < 3000,
                "Response took more than 3 seconds"
        );

        // Response body validation
        Assert.assertNotNull(
                response.jsonPath().get("data"),
                "Data is missing from response"
        );

        // Validate first user's ID
        int firstUserId =
                response.jsonPath().getInt("data[0].id");

        Assert.assertTrue(
                firstUserId > 0,
                "Invalid user ID"
        );
    }


    @Test
    public void createUserTest() {

        CreateUserRequest request =
                new CreateUserRequest(
                        "Rahul",
                        "SDET"
                );

        Response response =
                APIClient.post(
                        UserEndpoints.CREATE_USER,
                        request
                );

        System.out.println(
                response.asPrettyString()
        );

        Assert.assertEquals(
                response.getStatusCode(),
                201,
                "Expected status code 201"
        );

        Assert.assertEquals(
                response.jsonPath().getString("name"),
                "Rahul"
        );

        Assert.assertEquals(
                response.jsonPath().getString("job"),
                "SDET"
        );
    }
}