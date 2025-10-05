package Scenario.Negatif;
import org.testng.annotations.Test;
import ApiEngine.BookingCollectionAPI;
import io.restassured.response.Response;
import api.example.base.Base;

public class NUpdateBooking extends Base {
    public BookingCollectionAPI bookingCollectionAPI = new BookingCollectionAPI();

    @Test
    public void NUpdateBookingScen() {
        String requestBody = "{\n" +
                "    \"firstname\" : \"Chairun\",\n" +
                "    \"lastname\" : \"Puspitasari\",\n" +
                "    \"totalprice\" : 222,\n" +
                "    \"depositpaid\" : 5,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2025-09-02\",\n" +
                "        \"checkout\" : \"2025-09-18\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Gym\"\n" +
                "}";

        Response response = bookingCollectionAPI.UpdateBookingColl(requestBody, token);

        response.then().statusCode(405).log().all();

        System.out.println("Gagal Update Data Baru");
    }
}
