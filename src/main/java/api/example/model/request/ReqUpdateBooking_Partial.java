package api.example.model.request;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ReqUpdateBooking_Partial {

    @JsonProperty("firstname")
    public String firstname;

    @JsonProperty("lastname")
    public String lastname;
}
