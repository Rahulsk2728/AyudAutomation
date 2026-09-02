package apiTests;

import Headers.APIHeaders;
import api.APIClient;
import endpoints.UserEndpoints;
import io.restassured.response.Response;
import models.CreateUserRequest;
import models.UpdateUserRequest;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.annotations.DataProvider;

public class UserAPITest {

    // =========================================================
    // GET - Get All Users
    // =========================================================

    @Test
    public void getAllUsersTest() {

        Response response =
                APIClient.get(
                        UserEndpoints.GET_ALL_USERS
                );

        System.out.println(
                response.asPrettyString()
        );

        // Status code
        Assert.assertEquals(
                response.getStatusCode(),
                200,
                "Status code is not 200"
        );

        // Response time
        Assert.assertTrue(
                response.getTime() < 5000,
                "Response took more than 5 seconds"
        );

        // Validate data exists
        Assert.assertNotNull(
                response.jsonPath().get("data"),
                "Data is missing from response"
        );

        // Deserialize response into User POJO
        List<User> users =
                response.jsonPath()
                        .getList("data", User.class);

        // Validate users list
        Assert.assertFalse(
                users.isEmpty(),
                "User list should not be empty"
        );

        // First user
        User firstUser = users.get(0);

        // Validate first user
        Assert.assertTrue(
                firstUser.getId() > 0,
                "Invalid user ID"
        );

        Assert.assertNotNull(
                firstUser.getEmail(),
                "Email is missing"
        );

        Assert.assertNotNull(
                firstUser.getFirst_name(),
                "First name is missing"
        );

        Assert.assertNotNull(
                firstUser.getLast_name(),
                "Last name is missing"
        );

        // Pagination
        int page =
                response.jsonPath()
                        .getInt("page");

        Assert.assertEquals(
                page,
                1,
                "Incorrect page number"
        );

        int total =
                response.jsonPath()
                        .getInt("total");

        Assert.assertTrue(
                total > 0,
                "Total users should be greater than 0"
        );

        int totalPages =
                response.jsonPath()
                        .getInt("total_pages");

        Assert.assertTrue(
                totalPages > 0,
                "Total pages should be greater than 0"
        );

        // JSON Schema validation
        response.then()
                .assertThat()
                .body(
                        matchesJsonSchemaInClasspath(
                                "schemas/users-schema.json"
                        )
                );
    }

    // =========================================================
    // GET - Get Single User
    // =========================================================

    @Test
    public void getSingleUserTest() {

        Response response =
                APIClient.get(
                        UserEndpoints.GET_SINGLE_USER,
                        "id",
                        1
                );

        System.out.println(response.asPrettyString());

        // Status code
        Assert.assertEquals(
                response.getStatusCode(),
                200,
                "Expected status code 200"
        );

        // Response time
        Assert.assertTrue(
                response.getTime() < 5000,
                "Response took more than 5 seconds"
        );

        // Validate user ID
        int userId =
                response.jsonPath()
                        .getInt("data.id");

        Assert.assertEquals(
                userId,
                1,
                "Incorrect user ID"
        );

        // Validate email
        String email =
                response.jsonPath()
                        .getString("data.email");

        Assert.assertNotNull(
                email,
                "Email is missing"
        );

        Assert.assertTrue(
                email.contains("@"),
                "Invalid email format"
        );

        // Validate first name
        String firstName =
                response.jsonPath()
                        .getString("data.first_name");

        Assert.assertNotNull(
                firstName,
                "First name is missing"
        );

        // Validate last name
        String lastName =
                response.jsonPath()
                        .getString("data.last_name");

        Assert.assertNotNull(
                lastName,
                "Last name is missing"
        );
    }

    @Test
    public void getUsersWithHeadersTest() {

        Map<String, String> headers =
                APIHeaders.defaultHeaders();

        Response response =
                APIClient.get(
                        UserEndpoints.GET_ALL_USERS,
                        headers
                );

        Assert.assertEquals(
                response.getStatusCode(),
                200,
                "Expected status code 200"
        );
    }

    @Test
    public void getSingleUserInvalidIdTest() {

        Response response =
                APIClient.get(
                        UserEndpoints.GET_SINGLE_USER,
                        "id",
                        9999
                );

        System.out.println(
                "Status Code: " +
                        response.getStatusCode()
        );

        System.out.println(
                response.asPrettyString()
        );

        Assert.assertEquals(
                response.getStatusCode(),
                404,
                "Expected status code 404"
        );
    }

    // =========================================================
    // POST - Create User
    // =========================================================

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

        // Status code
        Assert.assertEquals(
                response.getStatusCode(),
                201,
                "Expected status code 201"
        );

        // Validate name
        Assert.assertEquals(
                response.jsonPath()
                        .getString("name"),
                "Rahul",
                "Incorrect name"
        );

        // Validate job
        Assert.assertEquals(
                response.jsonPath()
                        .getString("job"),
                "SDET",
                "Incorrect job"
        );

        // Validate generated ID
        Assert.assertNotNull(
                response.jsonPath()
                        .getString("id"),
                "User ID was not generated"
        );

        // Validate createdAt
        Assert.assertNotNull(
                response.jsonPath()
                        .getString("createdAt"),
                "createdAt is missing"
        );
    }


    // =========================================================
    // PUT - Update User
    // =========================================================

    @Test
    public void updateUserTest() {

        CreateUserRequest request =
                new CreateUserRequest(
                        "Rahul Updated",
                        "Senior SDET"
                );

        Response response =
                APIClient.put(
                        UserEndpoints.UPDATE_USER,
                        "id",
                        2,
                        request
                );

        System.out.println(
                response.asPrettyString()
        );

        // Status code
        Assert.assertEquals(
                response.getStatusCode(),
                200,
                "Expected status code 200"
        );

        // Validate name
        Assert.assertEquals(
                response.jsonPath()
                        .getString("name"),
                "Rahul Updated",
                "Incorrect name"
        );

        // Validate job
        Assert.assertEquals(
                response.jsonPath()
                        .getString("job"),
                "Senior SDET",
                "Incorrect job"
        );

        // Validate updatedAt
        Assert.assertNotNull(
                response.jsonPath()
                        .getString("updatedAt"),
                "updatedAt is missing"
        );
    }


    // =========================================================
    // PATCH - Partial Update User
    // =========================================================

    @Test
    public void patchUserTest() {

        UpdateUserRequest request =
                new UpdateUserRequest(
                        "Automation Architect"
                );

        Response response =
                APIClient.patch(
                        UserEndpoints.PATCH_USER,
                        "id",
                        2,
                        request
                );

        System.out.println(
                response.asPrettyString()
        );

        // Status code
        Assert.assertEquals(
                response.getStatusCode(),
                200,
                "Expected status code 200"
        );

        // Validate updated job
        Assert.assertEquals(
                response.jsonPath()
                        .getString("job"),
                "Automation Architect",
                "Incorrect job"
        );

        // Validate updatedAt
        Assert.assertNotNull(
                response.jsonPath()
                        .getString("updatedAt"),
                "updatedAt is missing"
        );
    }


    // =========================================================
    // DELETE - Delete User
    // =========================================================

    @Test
    public void deleteUserTest() {

        Response response =
                APIClient.delete(
                        UserEndpoints.DELETE_USER,
                        "id",
                        2
                );

        System.out.println(
                "Status Code: "
                        + response.getStatusCode()
        );

        // Status code
        Assert.assertEquals(
                response.getStatusCode(),
                204,
                "Expected status code 204"
        );

        // Response body should be empty
        Assert.assertTrue(
                response.getBody()
                        .asString()
                        .isEmpty(),
                "Response body should be empty"
        );
    }
}