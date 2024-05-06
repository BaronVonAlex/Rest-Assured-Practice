package ge.tbcacad.steps.booker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.json.JSONObject;

import static ge.tbcacad.data.constants.Constants.bookerBaseUrl;

public class BookerSteps {

    public String createAuthToken(String username, String password) {
        JSONObject requestBody = new JSONObject()
                .put("username", username)
                .put("password", password);

        Response response = RestAssured.given()
                .baseUri(bookerBaseUrl)
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .post("/auth");

        String token = response.then()
                .statusCode(200)
                .extract()
                .path("token");

        return token;
    }

    public Response updateBooking(long bookingId, JSONObject requestBody, String baseUrl, String authToken) {
        return RestAssured.given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Basic " + authToken)
                .body(requestBody.toString())
                .when()
                .put("/booking/" + bookingId)
                .then()
                .extract().response();
    }

    public ValidatableResponse logIf201(Response response) {
        return response.then()
                .statusCode(Matchers.is(201))
                .log().all();
    }
}
