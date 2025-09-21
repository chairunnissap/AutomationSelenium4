package Scenario;
import java.util.List;
import org.testng.annotations.Test;
import ApiEngine.BookingCollectionAPI;
import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetBookingIdsByDate {
    @Test
    public void GetBookingIdsByDateScen() {
        Response response = BookingCollectionAPI.GetBookingIdsByDateColl();

        response.then()
            .statusCode(200);
            
        // Ambil list booking id berdasarkan filter tanggal
        List<Integer> bookingIds = response.jsonPath().getList("bookingid");
        System.out.println("Booking Ids by tanggal : " + bookingIds);
        assertThat(bookingIds, is(notNullValue()));
    }
}
