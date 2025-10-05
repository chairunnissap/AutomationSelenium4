package Scenario.Negatif;
import org.testng.annotations.Test;
import ApiEngine.BookingCollectionAPI;
import api.example.model.request.ReqCreateBooking;
import api.example.utils.Helper;
import io.restassured.response.Response;

public class NCreateBooking {
    public BookingCollectionAPI bookingCollectionAPI = new BookingCollectionAPI();;

    @Test
    public void NCreateBookingScen() {
        ReqCreateBooking requestCreateBooking = Helper.findByUseCase("CreateBooking.json", "CreateBooking2", ReqCreateBooking.class);
        Response response = BookingCollectionAPI.CreateBookingColl(requestCreateBooking);

        response.then().statusCode(400).log().all();

        System.out.println("Gagal Membuat Booking ID baru");
    }
}
