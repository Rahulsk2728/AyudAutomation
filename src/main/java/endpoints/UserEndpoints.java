package endpoints;

public class UserEndpoints {

    // Users
    public static final String CREATE_USER = "/api/users";

    public static final String GET_ALL_USERS = "/api/users";

    public static final String GET_SINGLE_USER =
            "/api/users/{id}";

    public static final String UPDATE_USER =
            "/api/users/{id}";

    public static final String PATCH_USER =
            "/api/users/{id}";

    public static final String DELETE_USER =
            "/api/users/{id}";


    // Authentication
    public static final String REGISTER =
            "/api/register";

    public static final String LOGIN =
            "/api/login";


    // Resources
    public static final String GET_ALL_RESOURCES =
            "/api/unknown";

    public static final String GET_SINGLE_RESOURCE =
            "/api/unknown/{id}";
}