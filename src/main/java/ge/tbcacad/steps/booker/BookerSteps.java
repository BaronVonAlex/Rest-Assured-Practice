package ge.tbcacad.steps.booker;

import ge.tbcacad.data.models.booker.Booker.request.AuthTokenRequest;
import ge.tbcacad.data.models.booker.Booker.request.BookerRequest;
import ge.tbcacad.data.models.booker.Booker.response.AuthTokenResponse;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static ge.tbcacad.data.constants.Constants.bookerBaseUrl;

public class BookerSteps {
    @Step("Take Username and Password, after which create POST Request to booker and retrieve Auth. Token.")
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

    @Step("Make PATCH (Partial Upadte) Request and retrieve response body.")
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

    @Step("Create POST Request, make Booking and request body and return Response.")
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

    @Step("Crate DELETE request and delete existing booking with provided ID.")
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
