package Scenario.Negatif;
import org.testng.annotations.Test;
import ApiEngine.BookingCollectionAPI;
import api.example.base.Base;
import io.restassured.response.Response;

public class NDeleteBooking extends Base{

    public BookingCollectionAPI bookingCollectionAPI = new BookingCollectionAPI();

    // @Test(dependsOnMethods = {"CreateBooking"})
    @Test
    public void NDeleteBookingScen() {
        Response response = bookingCollectionAPI.NDeleteBookingColl(token);

        response.then().statusCode(405).log().all();
        System.out.println("Status code: " + response.getStatusCode());
        System.out.println("Response body: " + response.getBody().asString());
        System.out.println("Gagal Delete Booking");

    }
}
