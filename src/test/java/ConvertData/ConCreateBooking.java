package ConvertData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import api.example.model.request.ReqCreateBooking;

public class ConCreateBooking {
    public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
        String requestBody = "{\n" +
                "    \"firstname\" : \"Chairun\",\n" +
                "    \"lastname\" : \"Puspitasari\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2025-09-01\",\n" +
                "        \"checkout\" : \"2025-09-17\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
        
        // System.out.println(requestBody);
        ObjectMapper objectMapper = new ObjectMapper();
        ReqCreateBooking requestCreateBooking = objectMapper.readValue(requestBody, ReqCreateBooking.class);
        System.out.println("-------- Convert Json to Object ---------");
        System.out.println(requestCreateBooking.firstname);
        System.out.println(requestCreateBooking.bookingdates.checkin);
        System.out.println(requestCreateBooking.bookingdates.checkout);
    }
}
