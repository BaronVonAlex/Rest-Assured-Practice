package ge.tbcacad.data.requestbody.booker;

import ge.tbcacad.models.booker.request.BookerRequest;
import ge.tbcacad.models.booker.request.Bookingdates;

public class BookerRequestBody {
    public static BookerRequest bookerRequestBody() {

        BookerRequest requestBody = new BookerRequest();

        requestBody.setFirstname("John");
        requestBody.setLastname("Doe");
        requestBody.setTotalprice(200);
        requestBody.setDepositpaid(true);
        requestBody.setAdditionalneeds("Breakfast");

        Bookingdates bookingdates = new Bookingdates();

        bookingdates.setCheckin("2024-05-08");
        bookingdates.setCheckout("2024-05-10");
        requestBody.setBookingdates(bookingdates);
        return requestBody;
    }

    public static BookerRequest createBooker() {

        BookerRequest requestBody = new BookerRequest();

        requestBody.setFirstname("Sally");
        requestBody.setLastname("Car");
        requestBody.setTotalprice(123123);
        requestBody.setDepositpaid(false);
        requestBody.setAdditionalneeds("Something");

        Bookingdates bookingdates = new Bookingdates();

        bookingdates.setCheckin("2021-05-08");
        bookingdates.setCheckout("2023-05-10");
        requestBody.setBookingdates(bookingdates);
        return requestBody;
    }
}
