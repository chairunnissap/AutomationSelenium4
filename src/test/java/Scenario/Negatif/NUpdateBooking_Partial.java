package Scenario.Negatif;
import api.example.base.Base;
import org.testng.annotations.Test;
import ApiEngine.BookingCollectionAPI;
import io.restassured.response.Response;

public class NUpdateBooking_Partial extends Base{
    public BookingCollectionAPI bookingCollectionAPI = new BookingCollectionAPI();;

    @Test
    public void NUpdateBooking_PartialScen() {

        String requestBody = "{\n" +
                "    \"firstname\" : \"Chalay\",\n" +
                "    \"lastname\" : \"Imoet\"\n" +
                "}";

        Response response = bookingCollectionAPI.NUpdateBookingPartialColl(requestBody, token);

        response.then().statusCode(405).log().all();

        System.out.println("Gagal Update Data Partial Baru");
    }
}
