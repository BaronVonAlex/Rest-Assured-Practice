package ge.tbcacad.booker;

import ge.tbcacad.data.requestbody.booker.BookerRequestBody;
import ge.tbcacad.steps.booker.BookerSteps;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static ge.tbcacad.data.constants.Constants.*;

public class BookerTests {

    protected static BookerSteps bookerSteps;

    @BeforeTest
    public void setUp() {
        bookerSteps = new BookerSteps();
    }

    @Test
    public void callBookingUpdate() {
        String authToken = bookerSteps.createAuthToken(username, password);

        JSONObject requestBody = BookerRequestBody.bookerRequestBody();

        Response response = bookerSteps.updateBooking(bookingId, requestBody, bookerBaseUrl, authToken);

        bookerSteps.logIf201(response);
    }
}
