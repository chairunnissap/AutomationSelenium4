package Scenario.Negatif;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

import org.testng.annotations.Test;
import ApiEngine.BookingCollectionAPI;
import api.example.model.request.ReqNCreateBooking;
import api.example.model.response.ResCreateBooking;
import api.example.model.response.ResNCreateBooking;
import api.example.utils.Helper;
import io.restassured.response.Response;

public class NCreateBooking {
    public BookingCollectionAPI bookingCollectionAPI = new BookingCollectionAPI();;

    @Test
    public void NCreateBookingScen() {
        ReqNCreateBooking requestNCreateBooking = Helper.findByUseCase("NCreateBooking.json", "NCreateBooking2", ReqNCreateBooking.class);
        ResNCreateBooking expectNCreateBooking = Helper.findExpectedByUseCase("NCreateBooking.json", "NCreateBooking2", ResNCreateBooking.class);
        Response response = BookingCollectionAPI.NCreateBookingColl(requestNCreateBooking);

        response.then().statusCode(200).log().all();
        ResNCreateBooking resNCreateBooking = Helper.convertResponseToObject(response, ResNCreateBooking.class);

        String firstname = resNCreateBooking.booking.firstname;
        assertThat(firstname, not(equalTo(expectNCreateBooking.booking.firstname)));

        System.out.println("Gagal Membuat Booking ID baru");
    }
}
