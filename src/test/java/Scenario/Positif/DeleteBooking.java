package Scenario.Positif;
import org.testng.annotations.Test;
import ApiEngine.BookingCollectionAPI;
import api.example.base.Base;
import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class DeleteBooking extends Base{

    public BookingCollectionAPI bookingCollectionAPI = new BookingCollectionAPI();

    @Test
    public void DeleteBookingScen() {
        Response response = bookingCollectionAPI.DeleteBookingColl(token);

        response.then().statusCode(201).log().all();
        System.out.println("Status code: " + response.getStatusCode());
        System.out.println("Response body: " + response.getBody().asString());

        String responseBody = response.getBody().asString();
        assertThat(responseBody, equalTo("Created"));

        given()
            .baseUri("https://restful-booker.herokuapp.com")
            .basePath("/booking/{id}")
            .pathParam("id", 2)
        .when()
            .get()
        .then()
            .statusCode(404);
    }
}
