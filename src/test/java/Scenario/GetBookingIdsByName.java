package Scenario;
import java.util.List;
import org.testng.annotations.Test;
import ApiEngine.BookingCollectionAPI;
import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetBookingIdsByName {
    @Test
    public void GetBookingIdsByNameScen() {
        Response response = BookingCollectionAPI.GetBookingIdsByNameColl();
            
        response.then()
            .statusCode(200);

        // Ambil list booking id berdasarkan filter nama
        List<Integer> bookingIds = response.jsonPath().getList("bookingid");
        System.out.println("Booking Ids untuk Chairun Puspitasari: " + bookingIds);
        assertThat(bookingIds, is(notNullValue()));
    }
}
