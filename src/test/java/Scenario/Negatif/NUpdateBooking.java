package Scenario.Negatif;
import org.testng.annotations.Test;
import ApiEngine.BookingCollectionAPI;
import io.restassured.response.Response;
import api.example.base.Base;
import api.example.model.request.ReqNUpdateBooking;
import api.example.utils.Helper;

public class NUpdateBooking extends Base {
    public BookingCollectionAPI bookingCollectionAPI = new BookingCollectionAPI();

    @Test
    public void NUpdateBookingScen() {
        
        ReqNUpdateBooking requestNUpdateBooking = Helper.findByUseCase("NUpdateBooking.json", "NUpdateBooking1", ReqNUpdateBooking.class);
        Response response = bookingCollectionAPI.NUpdateBookingColl(requestNUpdateBooking, token);

        response.then().statusCode(405).log().all();

        System.out.println("Gagal Update Data Baru");
    }
}
