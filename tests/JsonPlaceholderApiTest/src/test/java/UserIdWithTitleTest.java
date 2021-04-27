import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class UserIdWithTitleTest {
    final String baseUrl  = "https://jsonplaceholder.typicode.com/posts";
    final String userIdParam = "userId";
    final String titleParam = "title";

    final List<Integer> correctUserIds = List.of(1, 10);
    final Map<Integer, List<String>> correctSpecificUserTitles = Map.ofEntries(
            Map.entry(1, List.of("qui est esse", "magnam facilis autem")),
            Map.entry(10, List.of("laboriosam dolor voluptates", "beatae soluta recusandae"))
    );

    @Test
    public void test() throws JSONException, IOException {
        for (int userId : correctUserIds) {
            for (String title : correctSpecificUserTitles.get(userId)) {
                JSONArray responseBody = Source.getResponseBody(
                        baseUrl,
                        userIdParam,
                        String.valueOf(userId),
                        titleParam,
                        title.replace(" ", "%20")
                );
                testPostsCount(responseBody, 1);

                JSONObject post = responseBody.getJSONObject(0);
                testPostUserId(post, userId);
                testPostTitle(post, title);
            }
        }
    }

    public void testPostsCount(JSONArray responseBody, int expectedCount) {
        Assertions.assertEquals(expectedCount, responseBody.length());
    }

    public void  testPostUserId(JSONObject post, int expectedUserId) throws JSONException {
        Assertions.assertEquals(expectedUserId, post.getInt("userId"));
    }

    public void testPostTitle(JSONObject post, String expectedTitle) throws JSONException {
        Assertions.assertEquals(expectedTitle, post.getString("title"));
    }
}
