package apiTests;

import Headers.APIHeaders;
import api.APIClient;
import dataproviders.UserDataProvider;
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
import utils.APIAssertions;

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
        APIAssertions.assertStatusCode(
                response,
                200
        );

        // Response time
        APIAssertions.assertResponseTime(
                response,
                5000
        );

        // Validate user ID
        APIAssertions.assertFieldEquals(
                response,
                "data.id",
                1
        );

        // Validate email
        APIAssertions.assertFieldNotNull(
                response,
                "data.email"
        );

        // Validate first name

        APIAssertions.assertFieldNotNull(
                response,
                "data.first_name"
        );

        // Validate last name
        APIAssertions.assertFieldNotNull(
                response,
                "data.last_name"
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
        APIAssertions.assertStatusCode(
                response,
                201
        );

        // Validate name
        APIAssertions.assertFieldEquals(
                response,
                "name",
                "Rahul"
        );

        // Validate job
        Assert.assertEquals(
                response.jsonPath()
                        .getString("job"),
                "SDET",
                "Incorrect job"
        );

        // Validate generated ID
        APIAssertions.assertFieldNotNull(
                response,
                "id"
        );

        // Validate createdAt
        Assert.assertNotNull(
                response.jsonPath()
                        .getString("createdAt"),
                "createdAt is missing"
        );
    }

    @Test(
            dataProvider = "userData",
            dataProviderClass = UserDataProvider.class
    )
    public void createMultipleUsersTest(String name, String job) {

        CreateUserRequest request =
                new CreateUserRequest(name, job);

        Response response =
                APIClient.post(
                        UserEndpoints.CREATE_USER,
                        request
                );

        System.out.println("Name: " + name);
        System.out.println("Job: " + job);
        System.out.println(response.asPrettyString());

        APIAssertions.assertStatusCode(response, 201);
        APIAssertions.assertFieldEquals(response, "name", name);
        APIAssertions.assertFieldEquals(response, "job", job);
        APIAssertions.assertFieldNotNull(response, "id");
        APIAssertions.assertFieldNotNull(response, "createdAt");
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
        APIAssertions.assertStatusCode(
                response,
                200
        );

        // Validate name
        APIAssertions.assertFieldEquals(
                response,
                "name",
                "Rahul Updated"
        );

        // Validate job

        APIAssertions.assertFieldEquals(
                response,
                "job",
                "Senior SDET"
        );

        // Validate updatedAt

        APIAssertions.assertFieldNotNull(
                response,
                "updatedAt"
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
        APIAssertions.assertStatusCode(
                response,
                200
        );

        // Validate updated job

        APIAssertions.assertFieldEquals(
                response,
                "job",
                "Automation Architect"
        );

        // Validate updatedAt
        APIAssertions.assertFieldNotNull(
                response,
                "updatedAt"
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
        APIAssertions.assertStatusCode(
                response,
                204
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