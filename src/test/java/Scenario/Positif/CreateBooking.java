package Scenario.Positif;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ApiEngine.BookingCollectionAPI;
import api.example.model.request.ReqCreateBooking;
import api.example.model.response.ResCreateBooking;
import api.example.utils.Helper;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateBooking {
    public BookingCollectionAPI bookingCollectionAPI = new BookingCollectionAPI();;
    private static ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void CreateBookingScen() throws JsonMappingException, JsonProcessingException {
        
        ReqCreateBooking requestCreateBooking = Helper.findByUseCase("CreateBooking.json", "CreateBooking", ReqCreateBooking.class);
        Response response = BookingCollectionAPI.CreateBookingColl(requestCreateBooking);

        response.then().statusCode(200).log().all();
        
        ResCreateBooking responseCreateBooking = objectMapper.readValue(response.asString(), ResCreateBooking.class);
        ResCreateBooking responseObj = response.as(ResCreateBooking.class);
        ResCreateBooking resCreateBooking = Helper.convertResponseToObject(response, ResCreateBooking.class);

        // Ambil data dari response dari convert
        int bookingId =  responseCreateBooking.bookingid;//response.jsonPath().getInt("bookingid");
        String firstname = resCreateBooking.booking.firstname;//response.jsonPath().getString("booking.firstname");
        String lastname = responseObj.booking.lastname; //response.jsonPath().getString("booking.lastname");
        int totalprice = responseObj.booking.totalprice; //response.jsonPath().getInt("booking.totalprice");
        boolean depositpaid = responseObj.booking.depositpaid;//response.jsonPath().getBoolean("booking.depositpaid");
        String checkin = responseObj.booking.bookingdates.checkin;//response.jsonPath().getString("booking.bookingdates.checkin");
        String checkout = responseObj.booking.bookingdates.checkout;//response.jsonPath().getString("booking.bookingdates.checkout");
        String additionalneeds = responseObj.booking.additionalneeds;//response.jsonPath().getString("booking.additionalneeds");

        assertThat(bookingId, greaterThan(0));  // bookingId harus > 0
        assertThat(firstname, equalTo("Chairun"));
        assertThat(lastname, equalTo("Puspitasari"));
        assertThat(totalprice, equalTo(111));
        assertThat(depositpaid, equalTo(true));
        assertThat(checkin, equalTo("2025-09-01"));
        assertThat(checkout, equalTo("2025-09-17"));
        assertThat(additionalneeds, equalTo("Breakfast"));

        System.out.println("Booking ID baru: " + bookingId);
    }
}
