package Scenario.Positif;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import ApiEngine.BookingCollectionAPI;
import api.example.model.request.ReqCreateBooking;
import api.example.model.response.ResCreateBooking;
import api.example.utils.Helper;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateBooking {
    public BookingCollectionAPI bookingCollectionAPI = new BookingCollectionAPI();;

    @Test
    public void CreateBookingScen() throws JsonMappingException, JsonProcessingException {
        
        ReqCreateBooking requestCreateBooking = Helper.findByUseCase("CreateBooking.json", "CreateBooking", ReqCreateBooking.class);
        ResCreateBooking expectCreateBooking = Helper.findExpectedByUseCase("CreateBooking.json", "CreateBooking", ResCreateBooking.class);
        Response response = BookingCollectionAPI.CreateBookingColl(requestCreateBooking);

        response.then().statusCode(200).log().all();
        ResCreateBooking resCreateBooking = Helper.convertResponseToObject(response, ResCreateBooking.class);

        int bookingId =  resCreateBooking.bookingid;
        String firstname = resCreateBooking.booking.firstname;
        String lastname = resCreateBooking.booking.lastname;
        int totalprice = resCreateBooking.booking.totalprice;
        boolean depositpaid = resCreateBooking.booking.depositpaid;
        String checkin = resCreateBooking.booking.bookingdates.checkin;
        String checkout = resCreateBooking.booking.bookingdates.checkout;
        String additionalneeds = resCreateBooking.booking.additionalneeds;

        assertThat(bookingId, greaterThan(0));
        assertThat(firstname, equalTo(expectCreateBooking.booking.firstname));
        assertThat(lastname, equalTo(expectCreateBooking.booking.lastname));
        assertThat(totalprice, equalTo(expectCreateBooking.booking.totalprice));
        assertThat(depositpaid, equalTo(expectCreateBooking.booking.depositpaid));
        assertThat(checkin, equalTo(expectCreateBooking.booking.bookingdates.checkin));
        assertThat(checkout, equalTo(expectCreateBooking.booking.bookingdates.checkout));
        assertThat(additionalneeds, equalTo(expectCreateBooking.booking.additionalneeds));

        System.out.println("Booking ID baru: " + bookingId);
    }
}
