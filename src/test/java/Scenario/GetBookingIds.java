package Scenario;
import java.util.List;
import org.testng.annotations.Test;
import ApiEngine.BookingCollectionAPI;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetBookingIds {

    @Test
    public void GetBookingIdsScen(){
        Response response = BookingCollectionAPI.GetBookingIdsColl();

        response.then().statusCode(200);

        // Ambil list bookingid
        List<Integer> bookingIds = response.jsonPath().getList("bookingid");
        System.out.println("Booking Ids: " + bookingIds);

        // Assertion tambahan
        assertThat(bookingIds, notNullValue());
    }
}
