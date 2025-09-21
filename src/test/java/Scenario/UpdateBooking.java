package Scenario;
import org.testng.annotations.Test;
import ApiEngine.BookingCollectionAPI;
import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import api.example.base.Base;

public class UpdateBooking extends Base {
    public String token;
    public BookingCollectionAPI bookingCollectionAPI = new BookingCollectionAPI();

    @Test
    public void UpdateBookingScen() {
        String requestBody = "{\n" +
                "    \"firstname\" : \"Chairun\",\n" +
                "    \"lastname\" : \"Puspitasari\",\n" +
                "    \"totalprice\" : 222,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2025-09-02\",\n" +
                "        \"checkout\" : \"2025-09-18\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Gym\"\n" +
                "}";

        Response response = bookingCollectionAPI.UpdateBookingColl(requestBody, token);

        response.then().statusCode(200).log().all();

        String firstname = response.jsonPath().getString("firstname");
        String lastname = response.jsonPath().getString("lastname");
        int totalprice = response.jsonPath().getInt("totalprice");
        boolean depositpaid = response.jsonPath().getBoolean("depositpaid");
        String checkin = response.jsonPath().getString("bookingdates.checkin");
        String checkout = response.jsonPath().getString("bookingdates.checkout");
        String additionalneeds = response.jsonPath().getString("additionalneeds");

        assertThat(firstname, equalTo("Chairun"));
        assertThat(lastname, equalTo("Puspitasari"));
        assertThat(totalprice, equalTo(222));
        assertThat(depositpaid, equalTo(true));
        assertThat(checkin, equalTo("2025-09-02"));
        assertThat(checkout, equalTo("2025-09-18"));
        assertThat(additionalneeds, equalTo("Gym"));
        System.out.println("Update data baru: " + firstname + " " + lastname);
    }
}
