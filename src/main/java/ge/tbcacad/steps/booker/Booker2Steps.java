package ge.tbcacad.steps.booker;

import ge.tbcacad.data.models.booker.Booker2.requests.BookingRequest;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

public class Booker2Steps {

    @Step("Create helper Method to easily make use DataProvider")
    public BookingRequest createBookingRequest(String firstName, String lastName, int totalPrice, boolean depositPaid,
                                               String checkinDate, String checkoutDate, String additionalNeeds,
                                               String passportNo) {
        return BookingRequest.builder()
                .firstName(firstName)
                .lastName(lastName)
                .totalPrice(totalPrice)
                .depositPaid(depositPaid)
                .checkin(checkinDate)
                .checkout(checkoutDate)
                .additionalNeeds(additionalNeeds)
                .passportNo(passportNo)
                .build();
    }

    @Step("Make put update Request")
    public Response updateBooking(int bookingId, BookingRequest requestBody, String baseUrl) {
        return RestAssured.given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .accept("application/json")
                .body(requestBody)
                .when()
                .put("/booking/" + bookingId)
                .then()
                .extract()
                .response();
    }

    @Step("Take Response and make Hamcrest Validation if status code is appropriate. (200)")
    public void checkStatusCode(Response response) {
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
