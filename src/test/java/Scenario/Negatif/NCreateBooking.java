package Scenario.Negatif;
import org.testng.annotations.Test;
import ApiEngine.BookingCollectionAPI;
import io.restassured.response.Response;

public class NCreateBooking {
    public BookingCollectionAPI bookingCollectionAPI = new BookingCollectionAPI();;

    @Test
    public void NCreateBookingScen() {
        String requestBody = "{\n" +
                "    \"firstname\" : \"\",\n" +
                "    \"lastname\" : \"Puspitasari\",\n" +
                "    \"totalprice\" : a,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2025-09-01\",\n" +
                "        \"checkout\" : \"2025-09-17\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        Response response = bookingCollectionAPI.CreateBookingColl(requestBody);

        response.then().statusCode(400).log().all();

        System.out.println("Gagal Membuat Booking ID baru");
    }
}
