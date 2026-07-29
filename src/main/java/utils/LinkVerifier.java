package utils;

import java.net.HttpURLConnection;
import java.net.URL;

public class LinkVerifier {
    public static int getStatusCode(String url) throws Exception {

        HttpURLConnection connection =
                (HttpURLConnection) new URL(url).openConnection();

        connection.setRequestMethod("GET");
        connection.connect();

        return connection.getResponseCode();
    }
}
