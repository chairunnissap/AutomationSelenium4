package api.example.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResCreateBooking {

    // {
    //     "bookingid": 4070,
    //     "booking": {
    //         "firstname": "Chairun",
    //         "lastname": "Puspitasari",
    //         "totalprice": 111,
    //         "depositpaid": true,
    //         "bookingdates": {
    //             "checkin": "2025-09-01",
    //             "checkout": "2025-09-17"
    //         },
    //         "additionalneeds": "Breakfast"
    //     }
    // }
    
    @JsonProperty("bookingid")
    public int bookingid;

    @JsonProperty("booking")
    public Booking booking;

    public static class Booking {
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
    }

    public static class BookingDates {
        @JsonProperty("checkin")
        public String checkin;

        @JsonProperty("checkout")
        public String checkout;
    }
}
