package ApiEngine;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import api.example.base.Base;
import org.testng.annotations.Test;

public class BookingCollectionAPI extends Base{
    
    public static Response GetBookingIdsColl(){
        Response response =
        given()
            .baseUri(Base.baseLink)
            .basePath("/booking")
            .header("Content-Type", "application/json")
        .when()
            .get();
        
        return response;
    }

    public static Response GetBookingIdsByNameColl(){
        Response response = 
        given()
            .baseUri(Base.baseLink)
            .basePath("/booking")
            .header("Content-Type", "application/json")
            .queryParam("firstname", "Chairun")
            .queryParam("lastname", "Puspitasari")
        .when()
            .get();
        
        return response;
    }

    public static Response GetBookingIdsByDateColl(){
        Response response = 
        given()
            .baseUri(Base.baseLink)
            .basePath("/booking")
            .header("Content-Type", "application/json")
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

    public Response CreateBookingColl (String requestBody){
        Response response = 
        given()
            .baseUri(Base.baseLink)
            .basePath("/booking")
            .header("Content-Type", "application/json")
            .header("Accept", "application/json")
            .body(requestBody)
        .when()
            .post();
        
        return response;
    }

    public Response UpdateBookingColl(String requestBody, String token){
        Response response =
            given()
                .baseUri(Base.baseLink)
                .basePath("/booking/{id}")
                .pathParam("id", 857)
                .body(requestBody)
            .when()
                .put();

        return response;
    }

    public Response UpdateBookingPartialColl(String requestBody, String token){
        Response response =
            given()
                .baseUri(Base.baseLink)
                .basePath("/booking/{id}")
                .pathParam("id", 3)
                .body(requestBody)
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

}
