package Scenario;
import api.example.base.Base;
import org.testng.annotations.Test;

import ApiEngine.BookingCollectionAPI;
import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UpdateBooking_Partial extends Base{
    public BookingCollectionAPI bookingCollectionAPI = new BookingCollectionAPI();;

    @Test
    public void UpdateBooking_PartialScen() {

        String requestBody = "{\n" +
                "    \"firstname\" : \"Chalay\",\n" +
                "    \"lastname\" : \"Imoet\"\n" +
                "}";

        Response response = bookingCollectionAPI.UpdateBookingPartialColl(requestBody, token);

        response.then().statusCode(200).log().all();

        String firstname = response.jsonPath().getString("firstname");
        String lastname = response.jsonPath().getString("lastname");

        assertThat(firstname, equalTo("Chalay"));
        assertThat(lastname, equalTo("Imoet"));
    }
}
