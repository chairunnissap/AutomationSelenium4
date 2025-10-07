package api.example.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResUpdateBooking_Partial {
    
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
