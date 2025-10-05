package api.example.base;
import api.example.utils.Helper;
import api.example.utils.TokenManager;
import static io.restassured.RestAssured.given;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import io.restassured.RestAssured;

public class Base {
    
    public static String token, baseLink;

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("This is Before Suite");
        token = TokenManager.getToken();
        baseLink = Helper.getKey("BASE_URI");
        // System.out.println("Base URI: " + baseLink);
        // System.out.println("Generate Token "+token);
    }

    @BeforeMethod
    public void setupRequestSpecification(){
        System.out.println("This is Before Method");
        RestAssured.requestSpecification = given()
                                            .baseUri(baseLink)
                                            .header("Content-Type", "application/json")
                                            .header("Accept", "application/json")
                                            .header("Cookie", "token="+ token)
                                            .header("Authorization", "Basic " + token);
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("This is After Method");
        if (RestAssured.requestSpecification != null) {
            RestAssured.requestSpecification = null;
        }
    }
}