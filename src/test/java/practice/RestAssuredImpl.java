package practice;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestAssuredImpl {
    
    String idObject;

    @Test
    public void testGetAllObjects() {
    RestAssured
        .given()
            .baseUri("https://api.restful-api.dev/objects")
            .header("Content-Type", "application/json")
        .when()
            .get()
        .then()
            .statusCode(200)
            .log().all();
    }

    @Test
    public void sendObjects() {
        String requestBody = "{\n" +
                "   \"name\": \"Apple MacBook Pro 16\",\n" +
                "   \"data\": {\n" +
                "      \"year\": 2019,\n" +
                "      \"price\": 1849.99,\n" +
                "      \"CPU model\": \"Intel Core i9\",\n" +
                "      \"Hard disk size\": \"1 TB\"\n" +
                "   }\n" +
                "}";

        RestAssured
            .given()
                .baseUri("https://api.restful-api.dev/objects")
                .header("Content-Type", "application/json")
                .body(requestBody)
            .when()
                .post()
            .then()
                .statusCode(200)
                .log().all();
    }

    public String sendObjectsReturnId() {
        String requestBody = "{\n" +
                "   \"name\": \"Apple MacBook Pro 16\",\n" +
                "   \"data\": {\n" +
                "      \"year\": 2019,\n" +
                "      \"price\": 1849.99,\n" +
                "      \"CPU model\": \"Intel Core i9\",\n" +
                "      \"Hard disk size\": \"1 TB\"\n" +
                "   }\n" +
                "}";

        Response response = RestAssured
            .given()
                .baseUri("https://api.restful-api.dev/objects")
                .header("Content-Type", "application/json")
                .body(requestBody)
            .when()
                .post();

        this.idObject = response.jsonPath().getString("id");
        return this.idObject;
    }

    @Test
    public void deleteObjectsById() {
        if (this.idObject == null) {
            this.idObject = this.sendObjectsReturnId();
        }

        String baseUri = "https://api.restful-api.dev/objects/" + this.idObject;
        System.out.println("baseUri : " + baseUri);

        RestAssured
            .given()
                .baseUri(baseUri)
                .header("Content-Type", "application/json")
            .when()
                .delete()
            .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void testGetObjectsById() {
        String objectId = "ff8081819782e69e01993928a9343657";

        RestAssured
            .given()
                .baseUri("https://api.restful-api.dev")
                .basePath("/objects/{id}")
                .header("Content-Type", "application/json")
                .pathParam("id", objectId)
            .when()
                .get()
            .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void testUpdateObjectsById() {
        String objectId = "ff8081819782e69e01993928a9343657";

        String requestBody = "{\n" +
                "   \"name\": \"Apple MacBook Pro 16 Updated\",\n" +
                "   \"data\": {\n" +
                "      \"year\": 2020,\n" +
                "      \"price\": 2000.00,\n" +
                "      \"CPU model\": \"Intel Core i9\",\n" +
                "      \"Hard disk size\": \"2 TB\"\n" +
                "   }\n" +
                "}";

        RestAssured
            .given()
                .baseUri("https://api.restful-api.dev")
                .basePath("/objects/{id}")
                .header("Content-Type", "application/json")
                .pathParam("id", objectId)
                .body(requestBody)
            .when()
                .put()
            .then()
                .statusCode(200)
                .log().all();
    }

}
