package ge.tbcacad.data.requestbody.booker;

import ge.tbcacad.models.booker.request.BookerRequest;
import ge.tbcacad.models.booker.request.Bookingdates;

public class BookerRequestBody {
//    public static JSONObject bookerRequestBody() {
//        Faker faker = new Faker();
//
//        return new JSONObject()
//                .put("firstname", "Alex")
//                .put("lastname", "Baron")
//                .put("totalprice", faker.number().randomNumber())
//                .put("depositpaid", true)
//                .put("bookingdates", new JSONObject()
//                        .put("checkin", "2024-03-24")
//                        .put("checkout", "2024-04-23")
//                )
//                .put("additionalneeds", "Pole Position");
//    }

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
}
