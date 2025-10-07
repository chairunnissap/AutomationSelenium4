package Scenario.Negatif;
import api.example.base.Base;
import api.example.model.request.ReqNUpdateBooking_Partial;
import api.example.utils.Helper;

import org.testng.annotations.Test;
import ApiEngine.BookingCollectionAPI;
import io.restassured.response.Response;

public class NUpdateBooking_Partial extends Base{
    public BookingCollectionAPI bookingCollectionAPI = new BookingCollectionAPI();;

    @Test
    public void NUpdateBooking_PartialScen() {

        ReqNUpdateBooking_Partial requestNUpdateBooking_Partial = Helper.findByUseCase("NUpdateBooking_Partial.json", "NUpdateBookingPartial1", ReqNUpdateBooking_Partial.class);
        Response response = bookingCollectionAPI.NUpdateBookingPartialColl(requestNUpdateBooking_Partial, token);

        response.then().statusCode(405).log().all();

        System.out.println("Gagal Update Data Partial Baru");
    }
}
