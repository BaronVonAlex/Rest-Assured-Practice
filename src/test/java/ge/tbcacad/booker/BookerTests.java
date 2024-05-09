package ge.tbcacad.booker;

import ge.tbcacad.data.requestbody.booker.BookerRequestBody;
import ge.tbcacad.models.booker.request.BookerRequest;
import ge.tbcacad.models.booker.response.BookerResponseOne;
import ge.tbcacad.models.booker.response.Booking;
import ge.tbcacad.steps.booker.BookerSteps;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static ge.tbcacad.data.constants.Constants.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class BookerTests {

    protected static BookerSteps bookerSteps;
    private static String authToken;

    @BeforeTest
    public void setUp() {
        bookerSteps = new BookerSteps();
        authToken = bookerSteps.createAuthToken(username, password);
    }

    @Test(priority = 1)
    public void createBooking() {
        Response request = bookerSteps.createBooking(BookerRequestBody.createBooker(), bookerBaseUrl, authToken);

        BookerResponseOne bookerResponse = request.as(BookerResponseOne.class);

        Booking booking = bookerResponse.getBooking();

        assertThat(booking.getFirstname(), Matchers.containsString(BOOKER_EXP_NAME));
    }

    @Test(priority = 2)
    public void callBookingUpdate() {
        BookerRequest requestBody = BookerRequestBody.bookerRequestBody();

        Response response = bookerSteps.partialUpdateBooking(bookingId, requestBody, bookerBaseUrl, authToken);

        response.then().log().ifStatusCodeIsEqualTo(201);
    }

    @Test(priority = 3)
    public void deleteBooking() {
        System.out.println(authToken);
        Response response = bookerSteps.deleteBooking(bookingId, bookerBaseUrl, authToken);
        response.then().log().ifStatusCodeIsEqualTo(200);
        BookerResponseOne bookerResponse = response.as(BookerResponseOne.class);
        Booking booking = bookerResponse.getBooking();
        assertThat(booking.getFirstname(), Matchers.emptyOrNullString());
    }
}
