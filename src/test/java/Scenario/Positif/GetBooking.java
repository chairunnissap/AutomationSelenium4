package Scenario.Positif;
import org.testng.annotations.Test;
import ApiEngine.BookingCollectionAPI;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetBooking {
    @Test
    public void GetBookingScen() {
        Response response = BookingCollectionAPI.GetBookingColl();
        
        response.then().statusCode(200);

        String firstname = response.jsonPath().getString("firstname");
        String lastname = response.jsonPath().getString("lastname");
        System.out.println("Booking id untuk nama : " + firstname +" "+ lastname);
        assertThat(firstname, not(emptyOrNullString()));
        assertThat(lastname, not(emptyOrNullString()));
    }
}
