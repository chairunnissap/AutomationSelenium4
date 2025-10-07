package ApiEngine;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import api.example.base.Base;

public class BookingCollectionAPI extends Base{
    
    public static Response GetBookingIdsColl(){
        Response response =
            Base.getRequestSpec()
        .when()
            .get();
        
        return response;
    }

    public static Response GetBookingIdsByNameColl(){
        Response response = 
            Base.getRequestSpec()
            .queryParam("firstname", "Chairun")
            .queryParam("lastname", "Puspitasari")
        .when()
            .get();
        
        return response;
    }

    public static Response GetBookingIdsByDateColl(){
        Response response = 
            Base.getRequestSpec()
            .queryParam("checkin", "2015-12-13")
            .queryParam("checkout", "2019-09-18")
        .when()
            .get();

        return response;
    }

    public static Response GetBookingColl(){
        Response response = 
        given()
            .baseUri(Base.baseLink)
            .basePath("/booking/{id}")
            .pathParam("id", 3)
            .header("Accept", "application/json")
        .when()
            .get();

        return response;
    }

    public static <T> Response CreateBookingColl (T payload){
        Response response = 
            Base.getRequestSpec()
            .header("Accept", "application/json")
            .body(payload).log().all()
        .when()
            .post();
        
        return response;
    }

    public static <T> Response NCreateBookingColl (T payload){
        Response response = 
            Base.getRequestSpec()
            .header("Accept", "application/json")
            .body(payload).log().all()
        .when()
            .post();
        
        return response;
    }

    public <T> Response UpdateBookingColl(T payload, String token){
        Response response =
            given()
                .baseUri(Base.baseLink)
                .basePath("/booking/{id}")
                .pathParam("id", 6)
                .body(payload)
            .when()
                .put();

        return response;
    }

    public <T> Response NUpdateBookingColl(T payload, String token){
        Response response =
            given()
                .baseUri(Base.baseLink)
                .basePath("/booking/{id}")
                .pathParam("id", 0)
                .body(payload)
            .when()
                .put();

        return response;
    }

    public <T> Response UpdateBookingPartialColl(T payload, String token){
        Response response =
            given()
                .baseUri(Base.baseLink)
                .basePath("/booking/{id}")
                .pathParam("id", 3)
                .body(payload)
            .when()
                .patch();

        return response;
    }

    public <T> Response NUpdateBookingPartialColl(T payload, String token){
        Response response =
            given()
                .baseUri(Base.baseLink)
                .basePath("/booking/{id}")
                .pathParam("id", 0)
                .body(payload)
            .when()
                .patch();

        return response;
    }

    public Response DeleteBookingColl(String token){
        Response response =
            given()
                .basePath("/booking/{id}")
                .pathParam("id", 2)
            .when()
                .delete()
            .then()
            .log().all()
            .extract().response();

        return response;
    }

    public Response NDeleteBookingColl(String token){
        Response response =
            given()
                .basePath("/booking/{id}")
                .pathParam("id", 0)
            .when()
                .delete()
            .then()
            .log().all()
            .extract().response();

        return response;
    }

}
