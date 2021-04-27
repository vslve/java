import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

public class Source {
    private static String getEndPoint(String url, String... params) {
        StringBuilder endPoint = new StringBuilder(url);
        if (params.length == 0) {
            return endPoint.toString();
        }
        endPoint.append("?");
        for (int i = 0; i < params.length; ++i) {
            if (i - 1 % 2 == 1) {
               endPoint.append("&");
            }
            endPoint.append(params[i]);
            if (i % 2 == 0) {
                endPoint.append("=");
            }
        }
        return endPoint.toString();
    }

    /**
     * @param url url without parameters
     * @param params pairs param, value represents url parameters
     *
     * Example:
     *
     *         to get response body from
     *               https://jsonplaceholder.typicode.com/posts?userId=5&title=post-title
     *         pass to function
     *
     *               url = https://jsonplaceholder.typicode.com/posts
     *               params = userId, 5, title, post-title
     */
    public static JSONArray getResponseBody(String url, String... params) throws IOException, JSONException {
        assert params.length % 2 == 0;

        HttpUriRequest request = new HttpGet(getEndPoint(url, params));
        HttpResponse response = HttpClientBuilder.create().build().execute( request );
        String jsonFromResponse = EntityUtils.toString(response.getEntity());
        return new JSONArray(jsonFromResponse);
    }
}
