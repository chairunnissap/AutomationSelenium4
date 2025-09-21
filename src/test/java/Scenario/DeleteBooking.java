package Scenario;
import org.testng.annotations.Test;
import ApiEngine.BookingCollectionAPI;
import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class DeleteBooking {

    public String token;
    public BookingCollectionAPI bookingCollectionAPI = new BookingCollectionAPI();

    @Test(dependsOnMethods = {"CreateBooking"})
    public void DeleteBookingScen() {
        Response response = bookingCollectionAPI.DeleteBookingColl(token);

        response.then().statusCode(201).log().all();

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
