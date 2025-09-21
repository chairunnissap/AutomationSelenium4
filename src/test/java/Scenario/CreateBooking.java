package Scenario;
import org.testng.annotations.Test;
import ApiEngine.BookingCollectionAPI;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateBooking {
    public BookingCollectionAPI bookingCollectionAPI = new BookingCollectionAPI();;

    @Test
    public void CreateBookingScen() {
        String requestBody = "{\n" +
                "    \"firstname\" : \"Chairun\",\n" +
                "    \"lastname\" : \"Puspitasari\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2025-09-01\",\n" +
                "        \"checkout\" : \"2025-09-17\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        Response response = bookingCollectionAPI.CreateBookingColl(requestBody);

        response.then().statusCode(200).log().all();

        // Ambil data dari response
        int bookingId = response.jsonPath().getInt("bookingid");
        String firstname = response.jsonPath().getString("booking.firstname");
        String lastname = response.jsonPath().getString("booking.lastname");
        int totalprice = response.jsonPath().getInt("booking.totalprice");
        boolean depositpaid = response.jsonPath().getBoolean("booking.depositpaid");
        String checkin = response.jsonPath().getString("booking.bookingdates.checkin");
        String checkout = response.jsonPath().getString("booking.bookingdates.checkout");
        String additionalneeds = response.jsonPath().getString("booking.additionalneeds");

        assertThat(bookingId, greaterThan(0));  // bookingId harus > 0
        assertThat(firstname, equalTo("Chairun"));
        assertThat(lastname, equalTo("Puspitasari"));
        assertThat(totalprice, equalTo(111));
        assertThat(depositpaid, equalTo(true));
        assertThat(checkin, equalTo("2025-09-01"));
        assertThat(checkout, equalTo("2025-09-17"));
        assertThat(additionalneeds, equalTo("Breakfast"));

        System.out.println("Booking ID baru: " + bookingId);
    }
}
