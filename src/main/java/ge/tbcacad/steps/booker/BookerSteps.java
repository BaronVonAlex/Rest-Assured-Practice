package ge.tbcacad.steps.booker;

import ge.tbcacad.models.booker.request.AuthTokenRequest;
import ge.tbcacad.models.booker.request.BookerRequest;
import ge.tbcacad.models.booker.response.Booking;
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
                .as(Booking.AuthTokenResponse.class)
                .getToken();
    }

    public Response partialUpdateBooking(int bookingId, BookerRequest requestBody, String baseUrl, String authToken) {
        return RestAssured.given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Authorization", "Basic " + authToken)
                .body(requestBody)
                .when()
                .patch("/booking/" + bookingId)
                .then()
                .extract()
                .response();
    }

    public Response createBooking(BookerRequest requestBody, String baseUrl, String authToken) {
        return RestAssured.given()
                .baseUri(baseUrl)
                .contentType("application/json")
                .header("Authorization", "Basic " + authToken)
                .body(requestBody)
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                .extract().response();
    }

    public Response deleteBooking(long bookingId, String baseUrl, String authToken) {
        return RestAssured.given()
                .baseUri(baseUrl)
                .basePath("/booking/" + bookingId)
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic " + authToken)
                .when()
                .delete()
                .then()
                .statusCode(200)
                .extract().response();
    }
}
