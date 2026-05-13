import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.util.List;

public class VetApiTest {

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://localhost:9966/petclinic/api";
    }

    @Test
    public void getVetsReturns200() {
        given()
                .when()
                .get("/vets")
                .then()
                .statusCode(200);
    }

    @Test
    public void getVetsReturnsNonEmptyList() {
        Response response = given()
                .when()
                .get("/vets")
                .then()
                .statusCode(200)
                .extract()
                .response();

        List<?> vets = response.jsonPath().getList("$");
        assertFalse(vets.isEmpty());
    }
}
