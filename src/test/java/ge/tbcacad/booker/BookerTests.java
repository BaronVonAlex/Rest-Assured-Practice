package ge.tbcacad.booker;

import ge.tbcacad.data.requestbody.booker.BookerRequestBody;
import ge.tbcacad.models.booker.request.BookerRequest;
import ge.tbcacad.steps.booker.BookerSteps;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static ge.tbcacad.data.constants.Constants.*;

public class BookerTests {

    protected static BookerSteps bookerSteps;
    private static String authToken;

    @BeforeTest
    public void setUp() {
        bookerSteps = new BookerSteps();
        authToken = bookerSteps.createAuthToken(username, password);
    }

    @Test
    public void callBookingUpdate() {
        BookerRequest requestBody = BookerRequestBody.bookerRequestBody();

        Response response = bookerSteps.updateBooking(bookingId, requestBody, bookerBaseUrl, authToken);

        response.then().log().ifStatusCodeIsEqualTo(201);
    }
}
