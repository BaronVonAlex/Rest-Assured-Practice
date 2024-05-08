package ge.tbcacad.steps.booker;

import ge.tbcacad.models.booker.request.AuthTokenRequest;
import ge.tbcacad.models.booker.request.BookerRequest;
import ge.tbcacad.models.booker.response.AuthTokenResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static ge.tbcacad.data.constants.Constants.bookerBaseUrl;

public class BookerSteps {

    public String createAuthToken(String username, String password) {
        AuthTokenRequest requestBody = new AuthTokenRequest();
        requestBody.setUsername(username);
        requestBody.setPassword(password);

        Response response = RestAssured.given()
                .baseUri(bookerBaseUrl)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("/auth");

        return response.then()
                .statusCode(200)
                .extract()
                .body()
                .as(AuthTokenResponse.class)
                .getToken();
    }

    public Response updateBooking(long bookingId, BookerRequest requestBody, String baseUrl, String authToken) {
        return RestAssured.given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Basic " + authToken)
                .body(requestBody)
                .when()
                .put("/booking/" + bookingId)
                .then()
                .extract().response();
    }
}
