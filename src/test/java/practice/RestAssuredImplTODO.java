package practice;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

public class RestAssuredImplTODO {

    String token;

    @BeforeClass
    public void generate_token(){
        System.out.println("Generate Token");
        String requestBody = "{\n" + //
                        "  \"username\": \"admin\",\n" + //
                        "  \"password\": \"password123\"\n" + //
                        "}";
        Response response = 
                given()
                    .baseUri("https://restful-booker.herokuapp.com")
                    .basePath("/auth")
                    .header("Content-Type", "application/json")
                    .body(requestBody)
                .when()
                    .post();
        token = response.jsonPath().getString("token");
    }


    @Test
    public void GetBookingIds(){
        Response response =
        given()
            .baseUri("https://restful-booker.herokuapp.com")
            .basePath("/booking")
            .header("Content-Type", "application/json")
        .when()
            .get();

        response.then().statusCode(200);

        // Ambil list bookingid
        List<Integer> bookingIds = response.jsonPath().getList("bookingid");
        System.out.println("Booking Ids: " + bookingIds);

        // Assertion tambahan
        assertThat(bookingIds, notNullValue());
    }

    @Test
    public void GetBookingIdsByName() {
        Response response =
            given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking")
                .header("Content-Type", "application/json")
                .queryParam("firstname", "Chairun")
                .queryParam("lastname", "Puspitasari")
            .when()
                .get();

        response.then()
            .statusCode(200);

        // Ambil list booking id berdasarkan filter nama
        List<Integer> bookingIds = response.jsonPath().getList("bookingid");
        System.out.println("Booking Ids untuk Chairun Puspitasari: " + bookingIds);
        assertThat(bookingIds, is(notNullValue()));
    }

    @Test
    public void GetBookingIdsByDate() {
        Response response =
            given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking")
                .header("Content-Type", "application/json")
                .queryParam("checkin", "2015-12-13")
                .queryParam("checkout", "2019-09-18")
            .when()
                .get();

        response.then()
            .statusCode(200);
            

        // Ambil list booking id berdasarkan filter tanggal
        List<Integer> bookingIds = response.jsonPath().getList("bookingid");
        System.out.println("Booking Ids by tanggal : " + bookingIds);
        assertThat(bookingIds, is(notNullValue()));
    }

    @Test
    public void GetBooking() {
        Response response =
        given()
            .baseUri("https://restful-booker.herokuapp.com")
            .basePath("/booking/{id}")
            .pathParam("id", 3)
            .header("Accept", "application/json")
        .when()
            .get();
        
            response.then().statusCode(200);

        String firstname = response.jsonPath().getString("firstname");
        String lastname = response.jsonPath().getString("lastname");
        System.out.println("Booking id untuk nama : " + firstname +" "+ lastname);
        assertThat(firstname, not(emptyOrNullString()));
        assertThat(lastname, not(emptyOrNullString()));
    }

    @Test
    public void CreateBooking() {
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

        Response response =
            given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(requestBody)
            .when()
                .post();

         response.then().statusCode(200).log().all();

        // Ambil data dari response
        int bookingId = response.jsonPath().getInt("bookingid");
        String firstname = response.jsonPath().getString("booking.firstname");
        String lastname = response.jsonPath().getString("booking.lastname");
        int totalprice = response.jsonPath().getInt("booking.totalprice");
        boolean depositpaid = response.jsonPath().getBoolean("booking.depositpaid");
        String checkin = response.jsonPath().getString("booking.bookingdates.checkin");
        String checkout = response.jsonPath().getString("booking.bookingdates.checkout");
        String additionalneeds = response.jsonPath().getString("booking.additionalneeds");

        assertThat(bookingId, greaterThan(0));  // bookingId harus > 0
        assertThat(firstname, equalTo("Chairun"));
        assertThat(lastname, equalTo("Puspitasari"));
        assertThat(totalprice, equalTo(111));
        assertThat(depositpaid, equalTo(true));
        assertThat(checkin, equalTo("2025-09-01"));
        assertThat(checkout, equalTo("2025-09-17"));
        assertThat(additionalneeds, equalTo("Breakfast"));

        System.out.println("Booking ID baru: " + bookingId);
    }

    @Test
    public void UpdateBooking() {
        String requestBody = "{\n" +
                "    \"firstname\" : \"Chairun\",\n" +
                "    \"lastname\" : \"Puspitasari\",\n" +
                "    \"totalprice\" : 222,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2025-09-02\",\n" +
                "        \"checkout\" : \"2025-09-18\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Gym\"\n" +
                "}";

        Response response =
            given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking/{id}")
                .pathParam("id", 2)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", "token="+ token)
                .header("Authorization", "Basic " + token)
                .body(requestBody)
            .when()
                .put();

        response.then().statusCode(200).log().all();

        String firstname = response.jsonPath().getString("firstname");
        String lastname = response.jsonPath().getString("lastname");
        int totalprice = response.jsonPath().getInt("totalprice");
        boolean depositpaid = response.jsonPath().getBoolean("depositpaid");
        String checkin = response.jsonPath().getString("bookingdates.checkin");
        String checkout = response.jsonPath().getString("bookingdates.checkout");
        String additionalneeds = response.jsonPath().getString("additionalneeds");

        assertThat(firstname, equalTo("Chairun"));
        assertThat(lastname, equalTo("Puspitasari"));
        assertThat(totalprice, equalTo(222));
        assertThat(depositpaid, equalTo(true));
        assertThat(checkin, equalTo("2025-09-02"));
        assertThat(checkout, equalTo("2025-09-18"));
        assertThat(additionalneeds, equalTo("Gym"));
        System.out.println("Update data baru: " + firstname + " " + lastname);
    }

    @Test
    public void UpdateBooking_Partial() {
        String requestBody = "{\n" +
                "    \"firstname\" : \"Chalay\",\n" +
                "    \"lastname\" : \"Imoet\"\n" +
                "}";

        Response response =
            given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking/{id}")
                .pathParam("id", 2)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", "token="+ token)
                .header("Authorization", "Basic " + token)
                .body(requestBody)
            .when()
                .patch();

        response.then().statusCode(200).log().all();

        String firstname = response.jsonPath().getString("firstname");
        String lastname = response.jsonPath().getString("lastname");

        assertThat(firstname, equalTo("Chalay"));
        assertThat(lastname, equalTo("Imoet"));
    }

    @Test(dependsOnMethods = {"CreateBooking"})
    public void DeleteBooking() {
        Response response =
            given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking/{id}")
                .pathParam("id", 2)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", "token="+ token)
                .header("Authorization", "Basic " + token)
            .when()
                .delete();

        response.then().statusCode(201).log().all();

        String responseBody = response.getBody().asString();
        assertThat(responseBody, equalTo("Created"));

        given()
            .baseUri("https://restful-booker.herokuapp.com")
            .basePath("/booking/{id}")
            .pathParam("id", 1)
        .when()
            .get()
        .then()
            .statusCode(404);
    }

}
