import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import model.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;


public class StoreApiTest {
    private final Order ORDER = new Order(
            Integer.parseInt(System.getProperty("orderId")),
            Integer.parseInt(System.getProperty("petId")),
            Integer.parseInt(System.getProperty("quantity")),
            System.getProperty("shipDate"),
            System.getProperty("status"),
            Boolean.getBoolean(System.getProperty("complete"))
    );

    @BeforeAll
    public static void prepare() throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("test.properties"));

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://petstore.swagger.io/v2/store")
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    @Test
    public void createOrder() {
        Order response = given()
                .body(ORDER)
                .when()
                .post("/order")
                .then()
                .statusCode(200)
                .extract().body().as(Order.class);

        Assertions.assertEquals(response, ORDER);
    }

    @Test
    public void getOrder() {
        postOrder();

        Order response = given()
                .pathParam("orderId", System.getProperty("orderId"))
                .when()
                .get("/order/{orderId}")
                .then()
                .statusCode(200)
                .extract().body().as(Order.class);

        Assertions.assertEquals(response, ORDER);
    }

    @Test
    public void deleteOrder() {
        postOrder();

        given()
                .pathParam("orderId", System.getProperty("orderId"))
                .when()
                .delete("/order/{orderId}")
                .then()
                .statusCode(200);

        given()
                .pathParam("orderId", System.getProperty("orderId"))
                .when()
                .delete("/order/{orderId}")
                .then()
                .statusCode(404);
    }

    @Test
    public void getInventory() {
        Map response =
                when()
                        .get("/inventory")
                        .then()
                        .extract().body().as(Map.class);

        Assertions.assertTrue(response.containsKey("sold"));
        Assertions.assertTrue(response.containsKey("Sold"));
        Assertions.assertTrue(response.containsKey("string"));
        Assertions.assertTrue(response.containsKey("pending"));
        Assertions.assertTrue(response.containsKey("available"));
        Assertions.assertTrue(response.containsKey("avalible"));
    }

    private void postOrder() {
        given().body(ORDER).post("/order");
    }
}
