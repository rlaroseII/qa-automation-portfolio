import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class OwnerApiTest {

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://10.0.0.249:9966/petclinic/api";
    }



    @Test
    public void createOwnerReturns201() {
        String body = """
            {
                "firstName": "Test",
                "lastName": "User",
                "address": "123 Main St",
                "city": "Testville",
                "telephone": "5555555555"
            }
            """;


        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/owners")
                .then()
                .statusCode(201);

    }

    @Test
    public void createOwnerReturnsCorrectLastName() {
        String body = """
        {
            "firstName": "Test",
            "lastName": "User",
            "address": "123 Main St",
            "city": "Testville",
            "telephone": "5555555555"
        }
        """;

        Response response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/owners")
                .then()
                .statusCode(201)
                .extract()
                .response();

        assertEquals("User", response.jsonPath().getString("lastName"));
    }

    @Test
    public void createOwnerWithMissingFieldReturns400() {
        String body = """
        {
            "firstName": "Test"
        }
        """;

        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/owners")
                .then()
                .statusCode(400);
    }
    @Test
    public void deleteOwnerReturns204() {
        String body = """
        {
            "firstName": "Delete",
            "lastName": "Me",
            "address": "999 Gone St",
            "city": "Nowhere",
            "telephone": "0000000000"
        }
        """;

        Response response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/owners")
                .then()
                .statusCode(201)
                .extract()
                .response();

        int id = response.jsonPath().getInt("id");

        given()
                .when()
                .delete("/owners/" + id)
                .then()
                .statusCode(204);
    }

    @Test
    public void updateOwnerReturns204() {
        String createBody = """
        {
            "firstName": "Original",
            "lastName": "Name",
            "address": "789 Before St",
            "city": "Oldtown",
            "telephone": "2222222222"
        }
        """;

        Response createResponse = given()
                .contentType(ContentType.JSON)
                .body(createBody)
                .when()
                .post("/owners")
                .then()
                .statusCode(201)
                .extract()
                .response();

        int id = createResponse.jsonPath().getInt("id");

        String updateBody = """
        {
            "firstName": "Updated",
            "lastName": "Name",
            "address": "789 After St",
            "city": "Newtown",
            "telephone": "2222222222"
        }
        """;

        given()
                .contentType(ContentType.JSON)
                .body(updateBody)
                .when()
                .put("/owners/" + id)
                .then()
                .statusCode(204);
    }
}