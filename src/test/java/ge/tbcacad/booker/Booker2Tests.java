package ge.tbcacad.booker;

import ge.tbcacad.data.dataprovider.Booker2DataProvider;
import ge.tbcacad.data.models.booker.Booker2.requests.BookingRequest;
import ge.tbcacad.data.models.booker.Booker2.responses.BookingResponse;
import ge.tbcacad.steps.booker.Booker2Steps;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static ge.tbcacad.data.constants.Constants.bookerBaseUrl;

public class Booker2Tests {

    protected static Booker2Steps booker2Steps;

    @BeforeTest
    public void setUp() {
        booker2Steps = new Booker2Steps();
        RestAssured.filters(new AllureRestAssured());
    }

    @Test(dataProviderClass = Booker2DataProvider.class, dataProvider = "bookingData",
            description = "Update already existing book on Booker, validate that status code is 200. Deserialize with BookingResponse class and console.log some values.")
    public void testName(BookingRequest bookingRequest) {
        Response response = booker2Steps.updateBooking(123, bookingRequest, bookerBaseUrl);
        booker2Steps.checkStatusCode(response);
        BookingResponse deserializedResponse = response.as(BookingResponse.class);

        System.out.println(deserializedResponse.getFirstName());
        System.out.println(deserializedResponse.getTotalPrice());
    }
}
