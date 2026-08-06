package apiTests;

import api.APIClient;
import endpoints.UserEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserAPITest {

    @Test
    public void getAllUsersTest() {

        Response response = APIClient.get(UserEndpoints.GET_ALL_USERS);

        System.out.println(response.asPrettyString());

        Assert.assertEquals(response.getStatusCode(), 200);

    }
}