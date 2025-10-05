package api.example.utils;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

public class TokenManager {

    public static String token;

    public static String getToken() {
        
        if (token == null) {
            token = generate_token();
        }
        return token;
        
    }

    public static String generate_token(){
        
        String requestBody = "{\n" + //
                        "  \"username\": \"admin\",\n" + //
                        "  \"password\": \"password123\"\n" + //
                        "}";
        Response response = 
                given()
                    .baseUri("https://restful-booker.herokuapp.com")
                    .basePath("/auth")
                    .header("Content-Type", "application/json")
                    .body(requestBody)
                .when()
                    .post();
        token = response.jsonPath().getString("token");

        return token;
    }

}
