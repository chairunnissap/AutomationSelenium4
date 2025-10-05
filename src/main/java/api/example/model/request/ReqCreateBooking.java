package api.example.model.request;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ReqCreateBooking {
    // "{\n" +
    // "    \"firstname\" : \"Chairun\",\n" +
    // "    \"lastname\" : \"Puspitasari\",\n" +
    // "    \"totalprice\" : 111,\n" +
    // "    \"depositpaid\" : true,\n" +
    // "    \"bookingdates\" : {\n" +
    // "        \"checkin\" : \"2025-09-01\",\n" +
    // "        \"checkout\" : \"2025-09-17\"\n" +
    // "    },\n" +
    // "    \"additionalneeds\" : \"Breakfast\"\n" +
    // "}";

    @JsonProperty("firstname")
    public String firstname;

    @JsonProperty("lastname")
    public String lastname;

    @JsonProperty("totalprice")
    public int totalprice;

    @JsonProperty("depositpaid")
    public boolean depositpaid;

    @JsonProperty("bookingdates")
    public BookingDates bookingdates;

    @JsonProperty("additionalneeds")
    public String additionalneeds;

    public static class BookingDates {
        @JsonProperty("checkin")
        public String checkin;

        @JsonProperty("checkout")
        public String checkout;
    }
}
