package api.example.base;
import api.example.utils.Helper;
import api.example.utils.TokenManager;
import static io.restassured.RestAssured.given;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import io.restassured.RestAssured;

public class Main {
    
    public static String token, baseURI;

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("This is Before Suite");
        token = TokenManager.getToken();
        baseURI = Helper.getKey("baseUri");
    }

    @BeforeMethod
    public void setupRequestSpecification(){
        System.out.println("This is Before Method");
        RestAssured.requestSpecification = given()
                                            .baseUri(baseURI)
                                            .header("Content-Type", "application/json")
                                            .header("Authorization", "Bearer " + token); 
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("This is After Method");
        if (RestAssured.requestSpecification != null) {
            RestAssured.requestSpecification = null;
        }
    }
}