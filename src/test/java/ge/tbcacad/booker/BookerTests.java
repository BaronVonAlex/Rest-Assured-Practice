package ge.tbcacad.booker;

import ge.tbcacad.data.requestbody.booker.BookerRequestBody;
import ge.tbcacad.models.booker.request.BookerRequest;
import ge.tbcacad.models.booker.response.BookerResponse;
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
        System.out.println(request.then().extract().as(BookerResponse.class).getFirstname());
        BookerResponse bookerResponse = request.as(BookerResponse.class);
        assertThat(bookerResponse.getFirstname(), Matchers.containsString(BOOKER_EXP_NAME));
    }

    @Test(priority = 2)
    public void callBookingUpdate() {
        BookerRequest requestBody = BookerRequestBody.bookerRequestBody();

        Response response = bookerSteps.partialUpdateBooking(bookingId, requestBody, bookerBaseUrl, authToken);

        response.then().log().ifStatusCodeIsEqualTo(201);
    }

    @Test(priority = 3)
    public void deleteBooking() {
        Response response = bookerSteps.deleteBooking(bookingId, bookerBaseUrl, authToken);
        response.then().log().ifStatusCodeIsEqualTo(200);
        BookerResponse bookerResponse = response.as(BookerResponse.class);
        assertThat(bookerResponse.getFirstname(), Matchers.emptyOrNullString());
    }
}
