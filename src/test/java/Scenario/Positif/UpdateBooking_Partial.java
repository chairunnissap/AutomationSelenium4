package Scenario.Positif;
import api.example.base.Base;
import api.example.model.request.ReqUpdateBooking_Partial;
import api.example.model.response.ResUpdateBooking_Partial;
import api.example.utils.Helper;
import org.testng.annotations.Test;
import ApiEngine.BookingCollectionAPI;
import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UpdateBooking_Partial extends Base{
    public BookingCollectionAPI bookingCollectionAPI = new BookingCollectionAPI();;

    @Test
    public void UpdateBooking_PartialScen() {

        ReqUpdateBooking_Partial requestUpdateBooking_Partial = Helper.findByUseCase("UpdateBooking_Partial.json", "UpdateBookingPartial1", ReqUpdateBooking_Partial.class);
        ResUpdateBooking_Partial expectUpdateBooking_Partial = Helper.findExpectedByUseCase("UpdateBooking_Partial.json", "UpdateBookingPartial1", ResUpdateBooking_Partial.class);
        Response response = bookingCollectionAPI.UpdateBookingPartialColl(requestUpdateBooking_Partial, token);

        response.then().statusCode(200).log().all();

        String firstname = response.jsonPath().getString("firstname");
        String lastname = response.jsonPath().getString("lastname");

        assertThat(firstname, equalTo(expectUpdateBooking_Partial.firstname));
        assertThat(lastname, equalTo(expectUpdateBooking_Partial.lastname));
    }
}
