package Scenario.Positif;
import org.testng.annotations.Test;
import ApiEngine.BookingCollectionAPI;
import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import api.example.base.Base;
import api.example.model.request.ReqUpdateBooking;
import api.example.model.response.ResUpdateBooking;
import api.example.utils.Helper;

public class UpdateBooking extends Base {
    public BookingCollectionAPI bookingCollectionAPI = new BookingCollectionAPI();

    @Test
    public void UpdateBookingScen() {
        ReqUpdateBooking requestUpdateBooking = Helper.findByUseCase("UpdateBooking.json", "UpdateBooking1", ReqUpdateBooking.class);
        ResUpdateBooking expectUpdateBooking = Helper.findExpectedByUseCase("UpdateBooking.json", "UpdateBooking1", ResUpdateBooking.class);
        Response response = bookingCollectionAPI.UpdateBookingColl(requestUpdateBooking, token);

        response.then().statusCode(200).log().all();
        ResUpdateBooking resUpdateBooking = Helper.convertResponseToObject(response, ResUpdateBooking.class);

        String firstname = resUpdateBooking.firstname;
        String lastname = resUpdateBooking.lastname;
        int totalprice = resUpdateBooking.totalprice;
        boolean depositpaid = resUpdateBooking.depositpaid;
        String checkin = resUpdateBooking.bookingdates.checkin;
        String checkout = resUpdateBooking.bookingdates.checkout;
        String additionalneeds = resUpdateBooking.additionalneeds;

        assertThat(firstname, equalTo(expectUpdateBooking.firstname));
        assertThat(lastname, equalTo(expectUpdateBooking.lastname));
        assertThat(totalprice, equalTo(expectUpdateBooking.totalprice));
        assertThat(depositpaid, equalTo(expectUpdateBooking.depositpaid));
        assertThat(checkin, equalTo(expectUpdateBooking.bookingdates.checkin));
        assertThat(checkout, equalTo(expectUpdateBooking.bookingdates.checkout));
        assertThat(additionalneeds, equalTo(expectUpdateBooking.additionalneeds));
        System.out.println("Update data baru: " + firstname + " " + lastname);
    }
}
